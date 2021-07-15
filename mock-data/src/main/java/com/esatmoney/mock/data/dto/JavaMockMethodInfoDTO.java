package com.esatmoney.mock.data.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: description
 * @ProjectName: mock-data
 * @Package: com.esatmoney.mock.data.dto
 * @Author: lcz
 * @Date: 2021/7/2 上午10:06
 * @Version: 1.0
 */
public class JavaMockMethodInfoDTO {
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
    private List<JavaParameterDTO> javaParameterDTOList = new ArrayList<>();

    /**
     * 方法返回参数类型 - 全限定名称
     */
    private String returnFullyType;
    /**
     * 方法返回参数类型 名称
     */
    private String returnType;

    /**
     * 是否存在多个 方法，方法名一直且参数数量一致
     */
    private Boolean haveMoreMethod;

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

    public List<JavaParameterDTO> getJavaParameterDTOList() {
        return javaParameterDTOList;
    }

    public void setJavaParameterDTOList(List<JavaParameterDTO> javaParameterDTOList) {
        this.javaParameterDTOList = javaParameterDTOList;
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

    public Boolean getHaveMoreMethod() {
        return haveMoreMethod;
    }

    public void setHaveMoreMethod(Boolean haveMoreMethod) {
        this.haveMoreMethod = haveMoreMethod;
    }
}
