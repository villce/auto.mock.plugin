package com.eastmoney.mock.core.impl;

import com.eastmoney.emis.utils.log.utils.LoggerUtil;
import com.eastmoney.mock.core.constant.CommonConstant;
import com.esatmoney.mock.data.dto.JavaMethodDTO;
import com.esatmoney.mock.data.dto.JavaMockMethodInfoDTO;
import com.esatmoney.mock.data.dto.JavaParameterDTO;
import com.esatmoney.mock.data.model.JavaClassModel;
import com.esatmoney.mock.data.model.JavaMethodModel;
import com.esatmoney.mock.data.model.JavaParameterModel;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.esatmoney.mock.data.info.JavaClassInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: description
 * @ProjectName: mock-core
 * @Package: com.eastmoney.mock.core.impl
 * @Author: lcz
 * @Date: 2021/7/2 下午2:45
 * @Version: 1.0
 */
public class BuildMockClassMethodImpl {

    /**
     * 构建mock的类方法信息
     *
     * @param javaGenInfoModel 存储的类信息
     * @param javaMethod       方法信息
     * @param javaMethodDTO    模版的方法信息
     */
    public static void buildMock(JavaClassInfo javaGenInfoModel, JavaMethod javaMethod, JavaMethodDTO javaMethodDTO) {
        // mock方法模拟
        List<JavaMockMethodInfoDTO> javaMockMethodInfoDTOList = new ArrayList<>();
        // 获取方法的源码
        String methodCode = javaMethod.getSourceCode();
        Map<String, String> mockFullyTypeNameMap = javaGenInfoModel.getMockFullyTypeNameMap();
        // 判断方法中是否有需要mock的方法，需要有属性名+方法名称
        for (String name : mockFullyTypeNameMap.keySet()) {
            // name - 属性名称
            String pattern = name + "\\([\\S ]+\\);";
            // 正则匹配
            Pattern p = Pattern.compile(pattern);
            // 获取matcher对象
            Matcher m = p.matcher(methodCode);
            while (m.find()) {
                saveMockMethodInfoDTO(javaGenInfoModel, javaMockMethodInfoDTOList, methodCode, name, m);
            }
        }
        javaMethodDTO.setJavaMockMethodInfoDTOList(javaMockMethodInfoDTOList);
    }

    /**
     * 保存方法中对应使用的mock方法的一些信息
     *
     * @param javaGenInfoModel          存储的类信息
     * @param javaMockMethodInfoDTOList mock方法集合
     * @param methodCode                方法的源码
     * @param name                      属性变量名称 + "." + 方法名称
     * @param m                         正则匹配
     */
    private static void saveMockMethodInfoDTO(JavaClassInfo javaGenInfoModel, List<JavaMockMethodInfoDTO> javaMockMethodInfoDTOList,
                                              String methodCode, String name, Matcher m) {
        Map<String, String> mockFullyTypeNameMap = javaGenInfoModel.getMockFullyTypeNameMap();
        // 全限定名称
        String fullType = mockFullyTypeNameMap.get(name);
        String str = methodCode.substring(m.start(), m.end());
        JavaMockMethodInfoDTO javaMockMethodInfoDTO = new JavaMockMethodInfoDTO();
        JavaClassModel javaClassModel = javaGenInfoModel.getJavaClassModelMap().get(fullType);
        if (javaClassModel == null) {
            LoggerUtil.warn(BuildMockClassMethodImpl.class, "获取的mock类数据为NULL，mockJavaClassModelMap="
                    + javaGenInfoModel + "，name=" + name + "，方法源码=" + methodCode);
            return;
        }
        javaMockMethodInfoDTO.setClassType(javaClassModel.getType());
        // 获取变量名称
        String methodName = null;
        try {
            String fieldName = name.split("\\.")[0];
            LoggerUtil.info(BuildMockClassMethodImpl.class, "获取的变量名称为：" + fieldName + "，全部名称为"
                    + name.split("\\.")[0] + "." + name.split("\\.")[1] + "，匹配的数据为：" + str);
            if ("this".equals(fieldName)) {
                // 当前测试类 属性
                fieldName = javaGenInfoModel.getModelNameLowerCamel();
                LoggerUtil.info(BuildMockClassMethodImpl.class, "获取的变量名称为：" + fieldName + "，进行设置属性变量：" + fieldName);
            }
            javaMockMethodInfoDTO.setFieldName(fieldName);
            // todo 方法名称 - 这里实际还需要区分参数类型和参数个数，否则无法匹配到唯一的方法，目前不支持重名方法！
            // todo 在后面有临时解决一下，进行注释该种方法
            // 获取方法名称
            methodName = name.split("\\.")[1];
        } catch (Exception e) {
            LoggerUtil.error(BuildMockClassMethodImpl.class, "获取变量名称异常，变量名.方法名：" + name + "，全限定名称为：" + fullType);
            throw new RuntimeException(e);
        }
        LoggerUtil.info(BuildMockClassMethodImpl.class, "获取到Mock的方法：" + str + "，javaMockMethodInfoDTO=" + javaMockMethodInfoDTO);
        javaMockMethodInfoDTO.setName(methodName);
        int num = str.split(",").length;
        // 判断是否为空参方法
        if (str.contains(javaMockMethodInfoDTO.getName() + "();")) {
            num = 0;
        }
        JavaMethodModel javaMethodModel = null;
        List<JavaMethodModel> javaMethodModelList = javaClassModel.getJavaMethodModelList();
        for (JavaMethodModel methodModel : javaMethodModelList) {
            if (methodModel.getName().equals(methodName)) {
                javaMethodModel = methodModel;
                break;
            }
        }
        if (javaMethodModel == null) {
            javaMethodModel = getJavaMethodModelByParent(javaGenInfoModel, name, methodName, javaMethodModel);
            if (javaMethodModel == null) {
                // todo 手动拼接 - 未获取到时，通过正则判断
                javaMethodModel = new JavaMethodModel();
                javaMethodModel.setParentClassFullyType("");
                javaMethodModel.setFieldName("");
                javaMethodModel.setClassType("");
                javaMethodModel.setName("");
                List<JavaParameterModel> javaParameterModelList = new ArrayList<>();
                for (int i = 0; i < num; i++) {
                    JavaParameterModel javaParameterModel = new JavaParameterModel();
                    javaParameterModelList.add(javaParameterModel);
                }
                javaMethodModel.setJavaParameteModelList(javaParameterModelList);
                javaMethodModel.setReturnFullyType("");
                javaMethodModel.setReturnType("");
            }
        }
        // 设置参数数量
        javaMockMethodInfoDTO.setParentClassFullyType(javaMethodModel.getParentClassFullyType());
        List<JavaParameterModel> javaParameterModelList = javaMethodModel.getJavaParameteModelList();
        List<JavaParameterDTO> javaParameterDTOList = new ArrayList<>();
        if (javaParameterModelList != null) {
            // todo 暂时没有实际用起来，mock方法的参数
            for (JavaParameterModel javaParameterModel : javaParameterModelList) {
                JavaParameterDTO javaParameterDTO = new JavaParameterDTO();
                javaParameterDTO.setName(javaParameterModel.getName());
                javaParameterDTO.setUpName(javaParameterModel.getUpName());
                javaParameterDTO.setType(javaParameterModel.getType());
                javaParameterDTO.setFullyType(javaParameterModel.getFullyType());
                javaParameterDTO.setCustomType(javaParameterModel.getCustomType());
                javaParameterDTO.setValue(javaParameterModel.getValue());
                javaParameterDTOList.add(javaParameterDTO);
            }
        }
        javaMockMethodInfoDTO.setJavaParameterDTOList(javaParameterDTOList);
        // 存储方法名和参数的数量 - 暂时解决重载方法的临时方案 - 参数也一致的情况，进行屏蔽 start
        Map<String, Integer> javaMethodModelMap = new HashMap<>();
        for (JavaMethodModel methodModel : javaMethodModelList) {
            try {
                // 方法名是否存在了，方法参数数量
                Integer p = methodModel.getJavaParameteModelList().size();
                String key = methodModel.getName() + p;
                if (javaMethodModelMap.containsKey(key)) {
                    Integer size = javaMethodModelMap.get(key);
                    javaMethodModelMap.put(key, ++size);
                } else {
                    javaMethodModelMap.put(key, 1);
                }
                // 获取父类是否包含一样的方法
                String pType = methodModel.getParentClassFullyType();
                LoggerUtil.info(BuildMockClassMethodImpl.class, "methodModel=" + methodModel + ",pType=" + pType);
                JavaClass javaClass = CommonConstant.javaProjectBuilder.getClassByName(pType);
                // 获取方法
                List<JavaMethod> methods = javaClass.getMethods();
                for (JavaMethod method : methods) {
                    // 父类中的方法
                    if (method.getName().equals(methodModel.getName()) && method.getParameters().size()== methodModel.getJavaParameteModelList().size()) {
                        Integer size = javaMethodModelMap.get(key);
                        javaMethodModelMap.put(key, ++size);
                    }
                }
            } catch (Exception e) {
                LoggerUtil.error(BuildMockClassMethodImpl.class, e.getMessage());
            }
        }
        // mock方法名称 - methodName
        String key = javaMockMethodInfoDTO.getName() + javaMockMethodInfoDTO.getJavaParameterDTOList().size();
        Integer size = javaMethodModelMap.getOrDefault(key, 0);
        if (size > 1) {
            javaMockMethodInfoDTO.setHaveMoreMethod(true);
        } else {
            javaMockMethodInfoDTO.setHaveMoreMethod(false);
        }
        // end
        javaMockMethodInfoDTO.setReturnFullyType(javaMethodModel.getReturnFullyType());
        javaMockMethodInfoDTO.setReturnType(javaMethodModel.getReturnType());
        LoggerUtil.info(BuildMockClassMethodImpl.class, "mock方法的属性：" + javaMockMethodInfoDTO);
        javaMockMethodInfoDTOList.add(javaMockMethodInfoDTO);
    }

    /**
     * 通过父类进行获取方法的属性
     *
     * @param javaGenInfoModel 存储的类信息
     * @param name             属性变量名称 + "." + 方法名称
     * @param methodName       方法名称
     * @param javaMethodModel  方法信息
     * @return
     */
    private static JavaMethodModel getJavaMethodModelByParent(JavaClassInfo javaGenInfoModel, String name,
                                                              String methodName, JavaMethodModel javaMethodModel) {
        // 通过父类再进行获取
        JavaClass javaClass = CommonConstant.javaProjectBuilder.getClassByName(name);
        if (javaClass == null) {
            LoggerUtil.warn(BuildMockClassMethodImpl.class, "没有找到该类，类名：" + name + "，javaClass=null");
            return null;
        }
        JavaClass superJavaClass = javaClass.getSuperJavaClass();
        if (superJavaClass == null) {
            LoggerUtil.warn(BuildMockClassMethodImpl.class, "没有找到该类的父类，类名：" + name
                    + "，superJavaClass=null，javaClass=" + javaClass);
            return null;
        }
        JavaClassModel javaClassModel = javaGenInfoModel.getJavaClassModelMap().get(superJavaClass.getFullyQualifiedName());
        if (javaClassModel == null) {
            LoggerUtil.warn(BuildMockClassMethodImpl.class, "没有找到该父类的JavaClassModel, superJavaClass：" + superJavaClass
                    + "，javaClass：" + javaClass + "，javaGenInfoModel=" + javaGenInfoModel);
            return null;
        }
        for (JavaMethodModel methodModel : javaClassModel.getJavaMethodModelList()) {
            // 获取到对应的方法
            if (methodModel.getName().equals(methodName)) {
                return javaMethodModel;
            }
        }
        LoggerUtil.warn(BuildMockClassMethodImpl.class, "在类中没有找到该方法，方法名：" + methodName + "，类名：" + name +
                "，javaGenInfoModel=" + javaGenInfoModel);
        return null;
    }
}
