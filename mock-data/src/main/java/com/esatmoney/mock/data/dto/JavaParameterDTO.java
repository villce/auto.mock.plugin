package com.esatmoney.mock.data.dto;

import com.esatmoney.mock.data.base.BaseCanUserType;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: description
 * @ProjectName: mock-data
 * @Package: com.esatmoney.mock.data.dto
 * @Author: lcz
 * @Date: 2021/7/2 上午10:03
 * @Version: 1.0
 */
public class JavaParameterDTO extends BaseCanUserType {
    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数名称 - 首字母大写
     * 便于setter方法设置
     */
    private String upName;

    /**
     * 是否自定义类型(自定义类型-无法直接赋值基础类型的值)
     */
    private boolean customType;
    /**
     * 是否是特殊对象，需要用指定方法赋值
     */
    private boolean special;
    /**
     * 默认值
     */
    private String value;
    /**
     * 如果默认值为NULL
     * 参数中的参数
     */
    private List<JavaParameterDTO> javaParameterDTOList = new ArrayList<>();

    /**
     * 当前属性的一个排序
     */
    private Integer order;

    /**
     * 参数所在的类全限定名称
     */
    private String classfullyType;
    /**
     * 参数所在的方法全限定名称
     */
    private String methodfullyType;
    /**
     * 参数所在的包的全限定名称
     */
    private String packageName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpName() {
        return upName;
    }

    public void setUpName(String upName) {
        this.upName = upName;
    }

    public Boolean getCustomType() {
        return customType;
    }

    public void setCustomType(Boolean customType) {
        this.customType = customType;
    }

    public Boolean getSpecial() {
        return special;
    }

    public void setSpecial(Boolean special) {
        this.special = special;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<JavaParameterDTO> getJavaParameterDTOList() {
        return javaParameterDTOList;
    }

    public void setJavaParameterDTOList(List<JavaParameterDTO> javaParameterDTOList) {
        this.javaParameterDTOList = javaParameterDTOList;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getClassfullyType() {
        return classfullyType;
    }

    public void setClassfullyType(String classfullyType) {
        this.classfullyType = classfullyType;
    }

    public String getMethodfullyType() {
        return methodfullyType;
    }

    public void setMethodfullyType(String methodfullyType) {
        this.methodfullyType = methodfullyType;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
