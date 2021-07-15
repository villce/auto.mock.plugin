package com.eastmoney.mock.core.utils;

import com.eastmoney.emis.utils.log.utils.LoggerUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 包处理工具
 * @ProjectName: mock-core
 * @Package: com.eastmoney.mock.core.utils
 * @Author: lcz
 * @Date: 2021/7/1 下午5:16
 * @Version: 1.0
 */
public class PackageUtils {

    /**
     * 获取包下所有类
     *
     * @param basedir      运行项目的根路径
     * @param packageName  包名
     * @param childPackage 是否遍历子包
     * @return
     */
    public static List<String> getClassName(String basedir, String packageName, boolean childPackage) {
        String packagePath = basedir + "/src/main/java/" + packageName.replace(".", "/");
        return getClassNameByFile(packagePath, childPackage);
    }

    /**
     * 从项目文件获取包下所有类
     *
     * @param filePath     文件路径 - 文件绝对路径
     * @param childPackage 是否遍历子包
     * @return
     */
    private static List<String> getClassNameByFile(String filePath, boolean childPackage) {
        LoggerUtil.info(PackageUtils.class, "从项目文件获取某包下所有类filePath：" + filePath);
        List<String> myClassName = new ArrayList<String>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        if (childFiles == null) {
            LoggerUtil.error(PackageUtils.class, "文件下的类为空");
            return myClassName;
        }
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                if (childPackage) {
                    myClassName.addAll(getClassNameByFile(childFile.getPath(), childPackage));
                }
            } else {
                String childFilePath = childFile.getPath();
                if (childFilePath.endsWith(".java")) {
                    myClassName.add(childFilePath);
                }
            }
        }
        return myClassName;
    }
}
