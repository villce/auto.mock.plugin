package com.esatmoney.mock.data.info;

import com.eastmoney.emis.utils.log.utils.LoggerUtil;
import com.esatmoney.mock.data.model.JavaClassModel;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 在方法中，作为参数传递给后面的类进行记录传值的类
 * @ProjectName: mock-data
 * @Package: com.esatmoney.mock.data.info
 * @Author: lcz
 * @Date: 2021/7/1 下午5:47
 * @Version: 1.0
 */
public class JavaClassInfo {

    /**
     * 当前被测试类的包名
     */
    private String packageName;
    /**
     * 被测试类的绝对路径
     */
    private String absolutePath;
    /**
     * 被测试类的绝对路径
     */
    private String testAbsolutePath;
    /**
     * 被测试类的全限定名称
     */
    private String fullyTypeName;
    /**
     * 被测试类的类名
     */
    private String typeName;
    /**
     * 当前 类属性名称
     * 被测试的类名 - 首字母小写
     */
    private String modelNameLowerCamel;
    /**
     * 类信息存储
     * key - 类的全限定名称
     * value - 类信息
     */
    private Map<String, JavaClassModel> javaClassModelMap = new HashMap<>();

    /**
     * key - 属性变量名称
     * value - 属性类型的全限定名称
     */
    private Map<String, String> fieldFullyTypeNameMap = new HashMap<>();

    /**
     * key - 类型简称
     * value - 类型的全限定名称
     */
    private Map<String, String> fullyTypeNameMap = new HashMap<>();

    /**
     * mock的类信息
     * key - 属性变量名称 + "." + 方法名称（暂时不支持重名方法）
     * value - 属性类型的全限定名称
     */
    private Map<String, String> mockFullyTypeNameMap = new HashMap<>();

    /**
     * 需要导入的包  如果有多个，全部使用全限定名，在该map中的，表示没有简称相同的类
     * key - 变量名-简称
     * value - 全限定名称
     */
    private Map<String, Set<String>> implementsJavaPackageMap = new HashMap<>();

    /**
     * 测试方法名称出现的次数，如果有多个重名方法，方法后面接上数字
     */
    private Map<String, Integer> methodMap = new HashMap<>();

    public String getPackageName() {
        if (StringUtils.isBlank(packageName)) {
            LoggerUtil.error(JavaClassInfo.class, "packageName 未进行设置值，请设置值后再进行获取");
        }
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAbsolutePath() {
        if (StringUtils.isBlank(absolutePath)) {
            LoggerUtil.error(JavaClassInfo.class, "absolutePath 未进行设置值，请设置值后再进行获取");
        }
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        if (StringUtils.isBlank(typeName)) {
            // 判断系统
            String osName = System.getProperty("os.name");
            if (osName == null) {
                setTypeName(absolutePath.substring(absolutePath.lastIndexOf("/") + 1, absolutePath.lastIndexOf(".")));
            } else {
                if (osName.contains("Windows")) {
                    setTypeName(absolutePath.substring(absolutePath.lastIndexOf("\\") + 1, absolutePath.lastIndexOf(".")));
                } else {
                    setTypeName(absolutePath.substring(absolutePath.lastIndexOf("/") + 1, absolutePath.lastIndexOf(".")));
                }
            }
        }
        this.absolutePath = absolutePath;
    }

    public String getTestAbsolutePath() {
        if (StringUtils.isBlank(testAbsolutePath)) {
            LoggerUtil.error(JavaClassInfo.class, "testAbsolutePath 未进行设置值，请设置值后再进行获取");
        }
        return testAbsolutePath;
    }

    public void setTestAbsolutePath(String testAbsolutePath) {
        this.testAbsolutePath = testAbsolutePath;
    }

    public String getFullyTypeName() {
        if (StringUtils.isNoneBlank(fullyTypeName)) {
            return fullyTypeName;
        }
        if (StringUtils.isNoneBlank(packageName) && StringUtils.isNoneBlank(typeName)) {
            fullyTypeName = packageName + "." + typeName;
        }
        if (StringUtils.isBlank(fullyTypeName)) {
            LoggerUtil.error(JavaClassInfo.class, "fullyTypeName 未进行设置值，请设置值后再进行获取");
        }
        return fullyTypeName;
    }

    public void setFullyTypeName(String fullyTypeName) {
        if (StringUtils.isBlank(typeName)) {
            setTypeName(fullyTypeName.substring(fullyTypeName.lastIndexOf("/") + 1, fullyTypeName.lastIndexOf(".")));
        }
        this.fullyTypeName = fullyTypeName;
    }

    public String getTypeName() {
        if (StringUtils.isBlank(typeName)) {
            LoggerUtil.error(JavaClassInfo.class, "typeName 未进行设置值，请设置值后再进行获取");
        }
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getModelNameLowerCamel() {
        return modelNameLowerCamel;
    }

    public void setModelNameLowerCamel(String modelNameLowerCamel) {
        this.modelNameLowerCamel = modelNameLowerCamel;
    }

    public Map<String, JavaClassModel> getJavaClassModelMap() {
        return javaClassModelMap;
    }

    public void setJavaClassModelMap(Map<String, JavaClassModel> javaClassModelMap) {
        this.javaClassModelMap = javaClassModelMap;
    }

    public Map<String, String> getFieldFullyTypeNameMap() {
        return fieldFullyTypeNameMap;
    }

    public void setFieldFullyTypeNameMap(Map<String, String> fieldFullyTypeNameMap) {
        this.fieldFullyTypeNameMap = fieldFullyTypeNameMap;
    }

    public Map<String, String> getFullyTypeNameMap() {
        return fullyTypeNameMap;
    }

    public void setFullyTypeNameMap(Map<String, String> fullyTypeNameMap) {
        this.fullyTypeNameMap = fullyTypeNameMap;
    }

    public Map<String, String> getMockFullyTypeNameMap() {
        return mockFullyTypeNameMap;
    }

    public void setMockFullyTypeNameMap(Map<String, String> mockFullyTypeNameMap) {
        this.mockFullyTypeNameMap = mockFullyTypeNameMap;
    }

    public Map<String, Set<String>> getImplementsJavaPackageMap() {
        return implementsJavaPackageMap;
    }

    public void setImplementsJavaPackageMap(Map<String, Set<String>> implementsJavaPackageMap) {
        this.implementsJavaPackageMap = implementsJavaPackageMap;
    }

    public Map<String, Integer> getMethodMap() {
        return methodMap;
    }

    public void setMethodMap(Map<String, Integer> methodMap) {
        this.methodMap = methodMap;
    }
}
