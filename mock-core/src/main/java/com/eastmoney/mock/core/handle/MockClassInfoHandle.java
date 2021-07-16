package com.eastmoney.mock.core.handle;

import com.eastmoney.emis.utils.log.utils.LoggerUtil;
import com.eastmoney.mock.core.constant.CommonConstant;
import com.esatmoney.mock.data.info.JavaClassInfo;
import com.esatmoney.mock.data.model.JavaClassModel;
import com.esatmoney.mock.data.model.JavaMethodModel;
import com.esatmoney.mock.data.model.JavaParameterModel;
import com.eastmoney.mock.core.constant.InitConstant;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import com.esatmoney.mock.data.dto.JavaMockClassInfoDTO;

import java.util.*;

/**
 * @Description: description
 * @ProjectName: mock-core
 * @Package: com.eastmoney.mock.core.handle
 * @Author: lcz
 * @Date: 2021/7/2 上午11:10
 * @Version: 1.0
 */
public class MockClassInfoHandle {

    /**
     * 获取mock的信息
     * 遍历类中属性，以及属性名称，设置到需要mock的类信息
     *
     * @param javaClass     测试类信息
     * @param javaClassInfo 处理当前测试类时存储的类信息
     * @return
     */
    public static List<JavaMockClassInfoDTO> getMockClass(JavaClass javaClass, JavaClassInfo javaClassInfo) {
        // 需要mock的类
        List<JavaMockClassInfoDTO> javaMockClassInfoDTOList = new ArrayList<>();
        // mock的类方法信息
        Map<String, JavaClassModel> javaClassModelMap = javaClassInfo.getJavaClassModelMap();
        // 导入的Java类
        Map<String, Set<String>> implementsJavaPackageMap = javaClassInfo.getImplementsJavaPackageMap();
        List<JavaField> javaFieldList = javaClass.getFields();
        // 遍历待测试类的属性
        for (JavaField javaField : javaFieldList) {
            JavaMockClassInfoDTO javaMockClassInfoDTO = new JavaMockClassInfoDTO();
            if (javaField.isStatic()) {
                // 跳过静态方法
                continue;
            }
            if (javaField.isFinal()) {
                // 跳过final方法
                continue;
            }
            // 类型全限定名
            String fullyQualifiedName = javaField.getType().getFullyQualifiedName();
            String type = InitConstant.getAbbreviation(fullyQualifiedName);
            JavaClassModel javaClassModel = new JavaClassModel();
            // 属性名称
            javaClassModel.setName(javaField.getName());
            javaClassModel.setFullyType(fullyQualifiedName);
            javaClassModel.setType(type);
            if (implementsJavaPackageMap.containsKey(type)) {
                Set<String> stringSet = implementsJavaPackageMap.get(type);
                stringSet.add(fullyQualifiedName);
                implementsJavaPackageMap.put(type, stringSet);
            } else {
                Set<String> stringSet = new HashSet<>();
                stringSet.add(fullyQualifiedName);
                implementsJavaPackageMap.put(type, stringSet);
            }
            // 设置属性名称和全限定名称
            Map<String, String> fullyTypeNameMap = javaClassInfo.getFieldFullyTypeNameMap();
            if (!fullyTypeNameMap.containsKey(javaClassModel.getName())) {
                fullyTypeNameMap.put(javaClassModel.getName(), javaClassModel.getFullyType());
            }
            List<JavaMethodModel> javaMethodModelList = new ArrayList<>();
            JavaClass superClass = javaField.getType().getSuperJavaClass();
            // 获取该类中的方法
            JavaClass fieldClass = CommonConstant.javaProjectBuilder.getClassByName(fullyQualifiedName);
            if ("org.springframework.data.redis.core.StringRedisTemplate".equals(fieldClass.getName())) {
                javaClassInfo.setStringRedis(true);
                javaClassInfo.setRedisShortName(javaField.getName());
            }
            List<JavaMethod> fieldMethodList = fieldClass.getMethods();
            for (JavaMethod javaMethod : fieldMethodList) {
                JavaMethodModel javaMethodModel = getJavaMethodModel(javaMethod, javaField.getName(), javaField.getType()
                        .getFullyQualifiedName(), superClass);
                String key = javaClassModel.getName() + "." + javaMethodModel.getName();
                Map<String, String> mockFullyTypeNameMap = javaClassInfo.getMockFullyTypeNameMap();
                if (!mockFullyTypeNameMap.containsKey(key)) {
                    mockFullyTypeNameMap.put(key, javaClassModel.getFullyType());
                }
                javaMethodModelList.add(javaMethodModel);
            }
            javaClassModel.setJavaMethodModelList(javaMethodModelList);
            if (!javaClassModelMap.containsKey(javaClassModel.getFullyType())) {
                javaClassModelMap.put(javaClassModel.getFullyType(), javaClassModel);
            }
            // 该类是接口
            if (javaField.getType().isInterface()) {
                // 获取该接口的父接口
                List<JavaClass> javaClassList = javaField.getType().getInterfaces();
                if (javaClassList != null) {
                    for (JavaClass aClass : javaClassList) {
                        superClass = aClass;
                        // 第三方包，可以通过插件导入包解决解析源码问题
                        LoggerUtil.info(MockClassInfoHandle.class, "获取的父类接口：" + superClass.getSource());
                        handlerSuperClass(javaClassInfo, javaField, javaMockClassInfoDTO, superClass, javaClassModel);
                    }
                }
            } else {
                // todo 获取父类 - 暂时只支持两层 - 暂时也不支持其他jar包中的类，引入包即可
                if (superClass != null) {
                    LoggerUtil.info(MockClassInfoHandle.class, "获取的父类：" + superClass.getSource());
                    handlerSuperClass(javaClassInfo, javaField, javaMockClassInfoDTO, superClass, javaClassModel);
                } else {
                    LoggerUtil.info(MockClassInfoHandle.class, "无法解析到父类：" + javaField.getType());
                }
            }
            javaMockClassInfoDTO.setName(javaField.getName());
            javaMockClassInfoDTO.setType(type);
            javaMockClassInfoDTO.setFullyType(fullyQualifiedName);
            javaMockClassInfoDTOList.add(javaMockClassInfoDTO);
        }
        // 处理父类的方法，进行mock父类的方法
        if (false) {
            mockThisOtherMethod(javaClass, javaClassInfo, javaClassModelMap);
        }
        // 属性的相关信息
        LoggerUtil.info(MockClassInfoHandle.class, "本类属性相关信息，类：" + javaClass.getName());
        return javaMockClassInfoDTOList;
    }

    /**
     * mock父类的方法，mock父类和当前测试类非本测试方法的方法
     *
     * @param javaClass         测试类信息
     * @param javaGenInfoModel     存储类信息
     * @param javaClassModelMap 类信息存储，key - 类的全限定名称，value - 类信息
     */
    private static void mockThisOtherMethod(JavaClass javaClass, JavaClassInfo javaGenInfoModel, Map<String, JavaClassModel> javaClassModelMap) {
        JavaClass superJavaClass = javaClass.getSuperJavaClass();
        JavaClassModel superJavaClassModel = new JavaClassModel();
        superJavaClassModel.setName(javaGenInfoModel.getModelNameLowerCamel());
        superJavaClassModel.setType(InitConstant.getAbbreviation(superJavaClass.getFullyQualifiedName()));
        superJavaClassModel.setFullyType(superJavaClass.getFullyQualifiedName());
        List<JavaMethodModel> javaMethodModelList = new ArrayList<>();
        // 遍历父类的方法
        for (JavaMethod method : superJavaClass.getMethods()) {
            JavaMethodModel javaMethodModel = getJavaMethodModel(method, javaGenInfoModel.getModelNameLowerCamel(),
                    javaClass.getFullyQualifiedName(), superJavaClass);
            String key = "this." + javaMethodModel.getName();
            Map<String, String> mockFullyTypeNameMap = javaGenInfoModel.getMockFullyTypeNameMap();
            if (!mockFullyTypeNameMap.containsKey(key)) {
                mockFullyTypeNameMap.put(key, superJavaClassModel.getFullyType());
            }
            javaMethodModelList.add(javaMethodModel);
        }
        superJavaClassModel.setJavaMethodModelList(javaMethodModelList);
        if (!javaClassModelMap.containsKey(superJavaClassModel.getFullyType())) {
            javaClassModelMap.put(superJavaClassModel.getFullyType(), superJavaClassModel);
        }
    }

    /**
     * 存储父类的信息
     *
     * @param javaGenInfoModel     存储的类信息
     * @param javaField            类属性信息
     * @param javaMockClassInfoDTO
     * @param superClass
     * @param javaClassModel
     */
    private static void handlerSuperClass(JavaClassInfo javaGenInfoModel, JavaField javaField, JavaMockClassInfoDTO javaMockClassInfoDTO,
                                           JavaClass superClass, JavaClassModel javaClassModel) {
        // 父类的方法
        Map<String, JavaClassModel> javaClassModelMap = javaGenInfoModel.getJavaClassModelMap();
        List<JavaMethod> superJavaMethod = superClass.getMethods();
        JavaClassModel superJavaClassModel = new JavaClassModel();
        superJavaClassModel.setName(superClass.getName());
        superJavaClassModel.setType(InitConstant.getAbbreviation(superClass.getFullyQualifiedName()));
        superJavaClassModel.setFullyType(superClass.getFullyQualifiedName());
        List<JavaMethodModel> javaMethodModelList = new ArrayList<>();
        for (JavaMethod javaMethod : superJavaMethod) {
            JavaMethodModel javaMethodModel = getJavaMethodModel(javaMethod, javaField.getName(), javaField.getType().getFullyQualifiedName(), superClass);
            String key = javaClassModel.getName() + "." + javaMethodModel.getName();
            Map<String, String> mockFullyTypeNameMap = javaGenInfoModel.getMockFullyTypeNameMap();
            if (!mockFullyTypeNameMap.containsKey(key)) {
                mockFullyTypeNameMap.put(key, superJavaClassModel.getFullyType());
            }
            javaMethodModelList.add(javaMethodModel);
        }
        superJavaClassModel.setJavaMethodModelList(javaMethodModelList);
        if (!javaClassModelMap.containsKey(superJavaClassModel.getFullyType())) {
            javaClassModelMap.put(superJavaClassModel.getFullyType(), superJavaClassModel);
        }
        // todo 暂时只处理一个接口，如果是接口，暂时设置最后一个接口的全限定名称 - 后面需要将接口和类进行区分
        javaMockClassInfoDTO.setParentClassFullyType(superClass.getFullyQualifiedName());
    }

    /**
     * 获取方法的信息，参数、方法返回值、参数类型
     *
     * @param javaMethod 方法
     * @param fieldName  调用该方法的变量名
     * @param classType  该方法的类的类型
     * @param superClass 父类
     * @return
     */
    private static JavaMethodModel getJavaMethodModel(JavaMethod javaMethod, String fieldName, String classType, JavaClass superClass) {
        JavaMethodModel javaMethodModel = new JavaMethodModel();
        javaMethodModel.setFieldName(fieldName);
        javaMethodModel.setClassType(classType);
        javaMethodModel.setName(javaMethod.getName());
        // 方法参数
        List<JavaParameter> javaParameterList = javaMethod.getParameters();
        List<JavaParameterModel> javaParameteModelList = new ArrayList<>();
        // 方法参数遍历
        Integer order = -1;
        for (JavaParameter javaParameter : javaParameterList) {
            JavaParameterModel javaParameteModel = new JavaParameterModel();
            // 全限定类型的名称
            String typeS = javaParameter.getType().getFullyQualifiedName();
            String pType = InitConstant.getAbbreviation(typeS);
            javaParameteModel.setName(javaParameter.getName());
            javaParameteModel.setUpName(javaParameter.getName().substring(0, 1).toUpperCase() + javaParameter.getName().substring(1));
            javaParameteModel.setType(pType);
            javaParameteModel.setFullyType(typeS);
            javaParameteModel.setKeyName("");
            // todo 设置值
            javaParameteModel.setCustomType(true);
            javaParameteModel.setValue("");
            javaParameteModel.setOrder(order);
            order++;
            javaParameteModelList.add(javaParameteModel);
        }
        javaMethodModel.setJavaParameteModelList(javaParameteModelList);
        String rTypeStr = javaMethod.getReturnType().getFullyQualifiedName();
        javaMethodModel.setReturnFullyType(rTypeStr);
        String rType = InitConstant.getAbbreviation(rTypeStr);
        javaMethodModel.setReturnType(rType);
        if (superClass != null) {
            // 设置父类类型
            javaMethodModel.setParentClassFullyType(superClass.getFullyQualifiedName());
        }
        return javaMethodModel;
    }
}
