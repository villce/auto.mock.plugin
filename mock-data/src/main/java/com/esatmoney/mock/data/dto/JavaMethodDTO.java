package com.esatmoney.mock.data.dto;

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
public class JavaMethodDTO {
    /**
     * 是否是私有方法
     */
    private Boolean isPrivate;
    /**
     * 被测试的方法名称
     */
    private String methodName;
    /**
     * 测试方法名称
     */
    private String methodTestName;
    /**
     * 被测试的方法返回类型
     */
    private String returnType;
    /**
     * 被测试的方法返回类型 - 全限定 名称
     */
    private String returnFullyType;
    /**
     * 方法参数
     */
    private List<JavaParameterDTO> javaParameterDTOList = new ArrayList<>();
    /**
     * 方法异常
     */
    private List<JavaMethodExceptionsDTO> javaMethodExceptionsDTOList = new ArrayList<>();

    /**
     * 需要被mock的方法
     */
    private List<JavaMockMethodInfoDTO> javaMockMethodInfoDTOList = new ArrayList<>();

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodTestName() {
        return methodTestName;
    }

    public void setMethodTestName(String methodTestName) {
        this.methodTestName = methodTestName;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnFullyType() {
        return returnFullyType;
    }

    public void setReturnFullyType(String returnFullyType) {
        this.returnFullyType = returnFullyType;
    }

    public List<JavaParameterDTO> getJavaParameterDTOList() {
        return javaParameterDTOList;
    }

    public void setJavaParameterDTOList(List<JavaParameterDTO> javaParameterDTOList) {
        this.javaParameterDTOList = javaParameterDTOList;
    }

    public List<JavaMethodExceptionsDTO> getJavaMethodExceptionsDTOList() {
        return javaMethodExceptionsDTOList;
    }

    public void setJavaMethodExceptionsDTOList(List<JavaMethodExceptionsDTO> javaMethodExceptionsDTOList) {
        this.javaMethodExceptionsDTOList = javaMethodExceptionsDTOList;
    }

    public List<JavaMockMethodInfoDTO> getJavaMockMethodInfoDTOList() {
        return javaMockMethodInfoDTOList;
    }

    public void setJavaMockMethodInfoDTOList(List<JavaMockMethodInfoDTO> javaMockMethodInfoDTOList) {
        this.javaMockMethodInfoDTOList = javaMockMethodInfoDTOList;
    }
}
