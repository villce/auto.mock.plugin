package com.esatmoney.mock.data.model;

import java.util.List;

/**
 * @Description: 类信息，作为参数进行流转，单个class生成时，方法参数的传递
 * @ProjectName: mock-data
 * @Package: com.esatmoney.mock.data.model
 * @Author: lcz
 * @Date: 2021/7/1 下午5:49
 * @Version: 1.0
 */
public class JavaClassModel {
    /**
     * 该类的的属性变量名称
     */
    private String name;
    /**
     * 类型 - 全限定名
     */
    private String fullyType;
    /**
     * 类型 - 非全限定名
     */
    private String type;
    /**
     * 父类类型
     */
    private String parentFullyTypeType;

    /**
     * 类中方法
     */
    private List<JavaMethodModel> javaMethodModelList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullyType() {
        return fullyType;
    }

    public void setFullyType(String fullyType) {
        this.fullyType = fullyType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentFullyTypeType() {
        return parentFullyTypeType;
    }

    public void setParentFullyTypeType(String parentFullyTypeType) {
        this.parentFullyTypeType = parentFullyTypeType;
    }

    public List<JavaMethodModel> getJavaMethodModelList() {
        return javaMethodModelList;
    }

    public void setJavaMethodModelList(List<JavaMethodModel> javaMethodModelList) {
        this.javaMethodModelList = javaMethodModelList;
    }
}
