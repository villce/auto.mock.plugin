package com.eastmoney.mock.core.impl;

import com.alibaba.fastjson.JSON;
import com.eastmoney.emis.utils.log.utils.LoggerUtil;
import com.eastmoney.mock.core.constant.CommonConstant;
import com.esatmoney.mock.data.dto.*;
import com.eastmoney.mock.core.handle.FullNameHandle;
import com.eastmoney.mock.core.utils.StringUtil;
import com.google.common.collect.Lists;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaPackage;
import com.eastmoney.mock.core.handle.MockClassInfoHandle;
import com.esatmoney.mock.data.info.JavaClassInfo;

import java.io.IOException;
import java.util.*;

/**
 * @Description: description
 * @ProjectName: mock-core
 * @Package: com.eastmoney.mock.core.impl
 * @Author: lcz
 * @Date: 2021/7/2 上午10:16
 * @Version: 1.0
 */
public class BuildClassImpl {

    /**
     * 生成测试类
     *
     * @param javaClassInfo java类信息
     * @return
     */
    public static JavaClassDTO build(JavaClassInfo javaClassInfo) throws IOException {
        // 模版类信息
        JavaClassDTO javaClassDTO = new JavaClassDTO();
        // 构建信息
        javaClassDTO.setProjectName(CommonConstant.CONFIG_ENTITY.getProject());
        javaClassDTO.setDate(CommonConstant.DATE);
        javaClassDTO.setAuthor(CommonConstant.CONFIG_ENTITY.getAuthor());
        javaClassDTO.setModelNameUpperCamel(javaClassInfo.getTypeName());
        javaClassDTO.setModelNameLowerCamel(StringUtil.strConvertLowerCamel(javaClassInfo.getTypeName()));
        javaClassDTO.setModelNameUpperCamelTestClass(javaClassInfo.getTypeName() + CommonConstant.TEST_CLASS_SUFFIX);
        javaClassDTO.setModelNameLowerCamelTestClass(StringUtil.strConvertLowerCamel(javaClassInfo.getTypeName() + CommonConstant.TEST_CLASS_SUFFIX));
        // 通过QDox 获取Java类
        JavaClass javaClass = CommonConstant.javaProjectBuilder.getClassByName(javaClassInfo.getFullyTypeName());
        LoggerUtil.info(BuildClassImpl.class, "当前正在构建类：" + javaClass);
        // 是否跳过类的测试生成
        if (defensiveProgramming(javaClassInfo.getFullyTypeName(), javaClass)) {
            return null;
        }
        // 设置首字母小写的类名 被测试的类名
        javaClassInfo.setModelNameLowerCamel(javaClassDTO.getModelNameLowerCamel());
        // 设置mock的信息
        List<JavaMockClassInfoDTO> javaMockClassInfoDTOList = MockClassInfoHandle.getMockClass(javaClass, javaClassInfo);
        // 需要引入的mock类
        javaClassDTO.setJavaMockClassInfoDTOList(javaMockClassInfoDTOList);
        // 设置方法、属性
        List<JavaMethodDTO> javaMethodDTOList = BuildClassMethodImpl.build(javaClass, javaClassInfo);
        javaClassDTO.setJavaMethodDTOList(javaMethodDTOList);
        // 获取导入的包 - 处理导入的包
        Map<String, Set<String>> implementsJavaPackageMap = javaClassInfo.getImplementsJavaPackageMap();
//        List<JavaImplementsDTO> javaImplementsDTOList = FullNameHandle.handleImplements(implementsJavaPackageMap);
        List<JavaImplementsDTO> javaImplementsDTOList = Lists.newLinkedList();
        javaClass.getSource().getImports().forEach(importStr -> {
            JavaImplementsDTO javaImplementsDTO = new JavaImplementsDTO();
            javaImplementsDTO.setType(importStr);
            javaImplementsDTOList.add(javaImplementsDTO);
        });
        javaClassDTO.setJavaImplementsDTOList(javaImplementsDTOList);
        // 处理测试类的参数，需要导入的包
        // 遍历方法
//        for (JavaMethodDTO javaMethodDTO : javaMethodDTOList) {
//            // 遍历参数
//            List<JavaParameterDTO> javaParameterDTOList = javaMethodDTO.getJavaParameterDTOList();
//            for (JavaParameterDTO javaParameterDTO : javaParameterDTOList) {
//                String type = javaParameterDTO.getType();
//                if (implementsJavaPackageMap.containsKey(type)) {
//                    // 包含该类型
//                    Set<String> types = implementsJavaPackageMap.get(type);
//                    // 有多个简称，使用全名
//                    if (types.size() > 1) {
//                        // 设置全名
//                        javaParameterDTO.setType(javaParameterDTO.getFullyType());
//                    }
//                }
//            }
//        }
        // 设置包名
        JavaPackage pkg = javaClass.getPackage();
        javaClassDTO.setPackageName(pkg.getName());
        LoggerUtil.info(BuildClassImpl.class, "构建的类信息：" + JSON.toJSONString(javaClassDTO));
        return javaClassDTO;
    }

    /**
     * 是否跳过类的测试生成
     *
     * @param fullyTypeName 类的全限定名
     * @param javaClass     类信息
     * @return
     */
    private static boolean defensiveProgramming(String fullyTypeName, JavaClass javaClass) {
        if (javaClass == null) {
            LoggerUtil.error(BuildClassImpl.class, "未查询到该类，请确保项目包中有该类，类名：" + fullyTypeName);
            return true;
        }
        if (javaClass.isInterface()) {
            LoggerUtil.info(BuildClassImpl.class, "跳过接口，" + javaClass);
            return true;
        }
        if (javaClass.isEnum()) {
            LoggerUtil.info(BuildClassImpl.class, "跳过枚举，" + javaClass);
            return true;
        }
        if (javaClass.isAbstract()) {
            LoggerUtil.info(BuildClassImpl.class, "跳过抽象类，" + javaClass);
            return true;
        }
        if (javaClass.isPrivate()) {
            LoggerUtil.info(BuildClassImpl.class, "跳过私有类，" + javaClass);
            return true;
        }
        return false;
    }
}
