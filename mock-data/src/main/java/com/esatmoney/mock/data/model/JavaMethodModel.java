package com.esatmoney.mock.data.model;

import java.util.List;

/**
 * @Description: java方法属性
 * @ProjectName: mock-data
 * @Package: com.esatmoney.mock.data.model
 * @Author: lcz
 * @Date: 2021/7/1 下午5:50
 * @Version: 1.0
 */
public class JavaMethodModel {
    /**
     * 父类类型 - 全限定名
     */
    private String parentClassFullyType;
    /**
     * 调用该方法的属性变量名称
     */
    private String fieldName;
    /**
     * 类的类型 - 全限定类型
     */
    private String classType;

    /**
     * 方法名称
     */
    private String name;
    /**
     * 方法参数
     */
    private List<JavaParameterModel> javaParameteModelList;
    /**
     * 方法返回参数类型 - 全限定 名称
     */
    private String returnFullyType;
    /**
     * 方法返回参数类型 名称
     */
    private String returnType;

    public String getParentClassFullyType() {
        return parentClassFullyType;
    }

    public void setParentClassFullyType(String parentClassFullyType) {
        this.parentClassFullyType = parentClassFullyType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JavaParameterModel> getJavaParameteModelList() {
        return javaParameteModelList;
    }

    public void setJavaParameteModelList(List<JavaParameterModel> javaParameteModelList) {
        this.javaParameteModelList = javaParameteModelList;
    }

    public String getReturnFullyType() {
        return returnFullyType;
    }

    public void setReturnFullyType(String returnFullyType) {
        this.returnFullyType = returnFullyType;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
