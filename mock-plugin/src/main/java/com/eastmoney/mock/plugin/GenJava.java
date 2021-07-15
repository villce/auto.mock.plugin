package com.eastmoney.mock.plugin;

import com.eastmoney.emis.utils.log.utils.LoggerUtil;
import com.eastmoney.mock.core.constant.CommonConstant;
import com.eastmoney.mock.core.utils.UUIDUtils;
import com.esatmoney.mock.data.dto.JavaClassDTO;
import com.eastmoney.mock.core.impl.BuildClassImpl;
import com.esatmoney.mock.data.info.JavaClassInfo;
import com.eastmoney.mock.core.utils.StringUtil;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.*;

/**
 * @Description: description
 * @ProjectName: mock-plugin
 * @Package: com.eastmoney.mock.plugin
 * @Author: lcz
 * @Date: 2021/7/1 下午7:48
 * @Version: 1.0
 */
public class GenJava {

    /**
     * 开始生成测试类入口
     *
     * @param javaClassInfo 参数
     */
    public static void genTest(JavaClassInfo javaClassInfo) {
        try {
            // 设置配置文件 freemarker
            Configuration cfg = getConfiguration();
            // 已经生成的测试类的方法名称
            Set<String> testMethodNameSet = new HashSet<>();
            // 文件是否存在，false-不存在
            boolean fileIsExists = false;
            File testFile = new File(javaClassInfo.getTestAbsolutePath());
            // 已经存在文件
            if (testFile.exists()) {
                LoggerUtil.info(GenJava.class, testFile + "已经存在，进行类方法追加生成");
                // 获取当前测试的类信息
                String testClassName = javaClassInfo.getPackageName() + "." + javaClassInfo.getTypeName() + CommonConstant.TEST_CLASS_SUFFIX;
                JavaClass testJavaClass = CommonConstant.javaProjectBuilder.getClassByName(testClassName);
                // 遍历方法
                List<JavaMethod> javaMethodList = testJavaClass.getMethods();
                for (JavaMethod javaMethod : javaMethodList) {
                    testMethodNameSet.add(javaMethod.getName());
                    LoggerUtil.info(GenJava.class, "获取测试类的方法名称：" + javaMethod.getName());
                }
                LoggerUtil.info(GenJava.class, "获取已经生成的类方法名称为：" + javaMethodList + "，测试类：" + testClassName);
                fileIsExists = true;
            } else {
                if (!testFile.getParentFile().exists() && !testFile.getParentFile().mkdirs()) {
                    LoggerUtil.error(GenJava.class, testFile.getParentFile() + "文件的路径不存在，本次生成失败");
                    return;
                }
            }
            // 构建参数，核心
            JavaClassDTO javaClassDTO = BuildClassImpl.build(javaClassInfo);
            // 获取类中方法
            if (javaClassDTO == null) {
                return;
            }
            Map<String, Object> data = new HashMap<>(2);
            data.put("javaClassDTO", javaClassDTO);
            // 获取mock的类
            if (!fileIsExists) {
                // 文件不存在，进行初始化生成
                cfg.getTemplate(CommonConstant.CONFIG_ENTITY.getConfigFileName()).process(data, new FileWriter(testFile));
                LoggerUtil.info(GenJava.class, testFile + "生成成功");
            } else {
                // 文件已经存在，进行追加方法
                File newFile = fileIsExists(javaClassInfo.getTypeName(), cfg, testMethodNameSet, testFile, javaClassDTO,
                        data, javaClassInfo);
                if (newFile == null) {
                    LoggerUtil.error(GenJava.class, javaClassInfo.getFullyTypeName() + "追加方法失败");
                    return;
                }
                LoggerUtil.info(GenJava.class, testFile + "，追加方法成功，生成的临时文件：" + newFile);
            }
        } catch (Exception e) {
            LoggerUtil.error(GenJava.class, "生成失败，出现异常：" + e.getMessage());
        }
    }

    /**
     * 生成freemarker引擎配置
     *
     * @return
     */
    private static Configuration getConfiguration() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        // 模版文件
        File file = new File(CommonConstant.CONFIG_ENTITY.getBasedir().getPath() + CommonConstant.CONFIG_ENTITY.getConfigPath());
        cfg.setDirectoryForTemplateLoading(file);
        // 设置文件编码
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    /**
     * 文件存在时，进行追加生成的方法
     * 生成一个临时文件，将临时文件中多出的方法写入到原来的文件中
     *
     * @param className         类名
     * @param configuration     Template模板生成文件
     * @param testMethodNameSet 已有测试方法的名称
     * @param file              原有已生成的文件
     * @param javaClassDTO      生成的临时文件信息
     * @param data              模板的数据
     * @param javaClassInfo     类通用信息
     * @return 临时生成的文件
     * @throws TemplateException 模版异常
     * @throws IOException       流异常
     */
    private static File fileIsExists(String className, Configuration configuration, Set<String> testMethodNameSet, File file,
                                     JavaClassDTO javaClassDTO, Map<String, Object> data, JavaClassInfo javaClassInfo) throws IOException, TemplateException {
        // 测试类已经存在了
        String testJavaName;
        String randId = UUIDUtils.getID();
        testJavaName = CommonConstant.CONFIG_ENTITY.getBasedir() + CommonConstant.JAVA_TEST_SRC +
                javaClassInfo.getPackageName().replace(".", "/") + "/" + className +
                randId + CommonConstant.TEST_CLASS_SUFFIX + ".java";
        javaClassDTO.setModelNameUpperCamelTestClass(className + randId + CommonConstant.TEST_CLASS_SUFFIX);
        javaClassDTO.setModelNameLowerCamelTestClass(StringUtil.strConvertLowerCamel(className + randId + CommonConstant.TEST_CLASS_SUFFIX));
        File newFile = new File(testJavaName);
        if (!newFile.getParentFile().exists() && !newFile.getParentFile().mkdirs()) {
            LoggerUtil.error(GenJava.class, newFile.getParentFile() + "生成失败，请检查是否有权限");
            return null;
        }
        configuration.getTemplate(CommonConstant.CONFIG_ENTITY.getConfigFileName()).process(data, new FileWriter(newFile));
        // 读取类的方法
        String newClassName = javaClassInfo.getPackageName() + "." + className + randId + CommonConstant.TEST_CLASS_SUFFIX;
        LoggerUtil.info(GenJava.class, "获取临时生成的类名：" + newClassName);
        // 读取包下所有的测试java类文件
        CommonConstant.javaProjectBuilder.addSourceTree(newFile);
        JavaClass testJavaClass =  CommonConstant.javaProjectBuilder.getClassByName(newClassName);
        List<JavaMethod> javaMethodList = testJavaClass.getMethods();
        LoggerUtil.info(GenJava.class, "获取的方法名称：" + javaMethodList);
        for (JavaMethod javaMethod : javaMethodList) {
            if (!testMethodNameSet.contains(javaMethod.getName())) {
                // 新增的方法 - 测试方法的源码
                String code = javaMethod.getSourceCode();
                LoggerUtil.debug(GenJava.class, "获取追加的方法源码为：" + code);
                // 原来的文件进行追加方法
                String methodStr = "\n    @Test\n" +
                        "    public void " + javaMethod.getName() + "(){\n" +
                        code + "\n" +
                        "    }\n}";
                // 文件追加
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);
                String lineContent = null;
                List<String> oldFileStr = new ArrayList<>();
                while ((lineContent = br.readLine()) != null) {
                    oldFileStr.add(lineContent);
                }
                br.close();
                fileReader.close();
                // 文件覆盖
                for (int i = oldFileStr.size() - 1; i >= 0; i--) {
                    String line = oldFileStr.get(i).trim();
                    if (StringUtil.isEmpty(line)) {
                        continue;
                    }
                    if ("}".equals(line)) {
                        // 删除该行
                        oldFileStr.remove(i);
                        break;
                    }
                    if (line.endsWith("}")) {
                        // 删除该行最后一个}字符
                        oldFileStr.set(i, oldFileStr.get(i).substring(0, oldFileStr.get(i).lastIndexOf("}")));
                        break;
                    }
                }
                StringBuilder fileStr = new StringBuilder();
                // 覆盖保存文件
                for (String line : oldFileStr) {
                    fileStr.append(line).append("\n");
                }
                fileStr.append(methodStr);
                // 写入文件
                PrintStream ps = new PrintStream(new FileOutputStream(file));
                // 往文件里写入字符串
                ps.println(fileStr);
                ps.close();
            }
            LoggerUtil.info(GenJava.class, "获取临时测试类的方法名称：" + javaMethod.getName());
        }
        // 删除文件
        if (!newFile.delete()) {
            LoggerUtil.error(GenJava.class, "删除临时文件失败，请检查是否有权限。文件：" + newFile);
        }
        return newFile;
    }
}
