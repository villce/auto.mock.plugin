package com.esatmoney.mock.data.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 类DTO（DTO是给模版类中使用的参数）
 * @ProjectName: mock-data
 * @Package: com.esatmoney.mock.data.dto
 * @Author: lcz
 * @Date: 2021/7/2 上午10:02
 * @Version: 1.0
 */
public class JavaClassDTO {
    /**
     * 被测试的类名
     */
    private String modelNameUpperCamel;

    /**
     * 被测试的类名 - 首字母小写
     */
    private String modelNameLowerCamel;

    /**
     * 测试类名
     */
    private String modelNameUpperCamelTestClass;

    /**
     * 测试类名 - 首字母小写
     */
    private String modelNameLowerCamelTestClass;
    /**
     * 项目名
     */
    private String projectName;
    /**
     * 当前日期
     */
    private String date;

    /**
     * 作者
     */
    private String author;
    /**
     * 类方法
     */
    private List<JavaMethodDTO> javaMethodDTOList = new ArrayList<>();
    /**
     * 需要导入的包 - 简称相同的类，不会被导入
     */
    private List<JavaImplementsDTO> javaImplementsDTOList = new ArrayList<>();

    /**
     * 被测试类包名
     */
    private String packageName;
    /**
     * 需要引入的mcok类
     */
    private List<JavaMockClassInfoDTO> javaMockClassInfoDTOList = new ArrayList<>();

    public String getModelNameUpperCamel() {
        return modelNameUpperCamel;
    }

    public void setModelNameUpperCamel(String modelNameUpperCamel) {
        this.modelNameUpperCamel = modelNameUpperCamel;
    }

    public String getModelNameLowerCamel() {
        return modelNameLowerCamel;
    }

    public void setModelNameLowerCamel(String modelNameLowerCamel) {
        this.modelNameLowerCamel = modelNameLowerCamel;
    }

    public String getModelNameUpperCamelTestClass() {
        return modelNameUpperCamelTestClass;
    }

    public void setModelNameUpperCamelTestClass(String modelNameUpperCamelTestClass) {
        this.modelNameUpperCamelTestClass = modelNameUpperCamelTestClass;
    }

    public String getModelNameLowerCamelTestClass() {
        return modelNameLowerCamelTestClass;
    }

    public void setModelNameLowerCamelTestClass(String modelNameLowerCamelTestClass) {
        this.modelNameLowerCamelTestClass = modelNameLowerCamelTestClass;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<JavaMethodDTO> getJavaMethodDTOList() {
        return javaMethodDTOList;
    }

    public void setJavaMethodDTOList(List<JavaMethodDTO> javaMethodDTOList) {
        this.javaMethodDTOList = javaMethodDTOList;
    }

    public List<JavaImplementsDTO> getJavaImplementsDTOList() {
        return javaImplementsDTOList;
    }

    public void setJavaImplementsDTOList(List<JavaImplementsDTO> javaImplementsDTOList) {
        this.javaImplementsDTOList = javaImplementsDTOList;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<JavaMockClassInfoDTO> getJavaMockClassInfoDTOList() {
        return javaMockClassInfoDTOList;
    }

    public void setJavaMockClassInfoDTOList(List<JavaMockClassInfoDTO> javaMockClassInfoDTOList) {
        this.javaMockClassInfoDTOList = javaMockClassInfoDTOList;
    }
}
