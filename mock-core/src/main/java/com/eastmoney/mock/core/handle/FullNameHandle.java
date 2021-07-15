package com.eastmoney.mock.core.handle;

import com.esatmoney.mock.data.base.BaseCanUserType;
import com.esatmoney.mock.data.dto.JavaImplementsDTO;
import com.eastmoney.mock.core.constant.InitConstant;

import java.util.*;

/**
 * @Description: description
 * @ProjectName: mock-core
 * @Package: com.eastmoney.mock.core.handle
 * @Author: lcz
 * @Date: 2021/7/2 下午3:36
 * @Version: 1.0
 */
public class FullNameHandle {

    /**
     * 处理全称限定名称 - 简称
     *
     * @param baseCanUserType          被处理的类，设置是否能够使用简称
     * @param implementsJavaPackageMap 需要导入的包 如果有多个，全部使用全限定名，在该map中的，表示没有简称相同的类
     *                                 key - 变量名-简称
     *                                 value - 全限定名称
     */
    public static void addQualifiedNameToImplementsPackageMap(BaseCanUserType baseCanUserType, Map<String, Set<String>> implementsJavaPackageMap) {
        // 处理全限定名称
        String type = baseCanUserType.getType();
        if (implementsJavaPackageMap.containsKey(type)) {
            Set<String> stringSet = implementsJavaPackageMap.get(type);
            if (stringSet.size() == 1) {
                for (String fType : stringSet) {
                    if (fType.endsWith(baseCanUserType.getFullyType())) {
                        baseCanUserType.setCanUserType(true);
                    }
                }
            }
            stringSet.add(baseCanUserType.getFullyType());
        } else {
            Set<String> stringSet = new HashSet<>();
            if (InitConstant.SPECIAL_VALUE_IMPORT.containsKey(baseCanUserType.getFullyType())) {
                // 当为特殊对象时，需导入指定包
                stringSet.add(InitConstant.SPECIAL_VALUE_IMPORT.get(baseCanUserType.getFullyType()));
            } else {
                stringSet.add(baseCanUserType.getFullyType());
            }
            implementsJavaPackageMap.put(baseCanUserType.getType(), stringSet);
            baseCanUserType.setCanUserType(true);
        }
        // 接口，处理实现类的全限定名称
        if (baseCanUserType.getInter()) {
            String subType = baseCanUserType.getSubClassType();
            if (implementsJavaPackageMap.containsKey(subType)) {
                Set<String> stringSet = implementsJavaPackageMap.get(subType);
                if (stringSet.size() == 1) {
                    for (String fType : stringSet) {
                        if (fType.equals(baseCanUserType.getSubClassFullyType())) {
                            baseCanUserType.setSubClassCanUserType(true);
                        }
                    }
                }
                stringSet.add(baseCanUserType.getSubClassFullyType());
            } else {
                Set<String> stringSet = new HashSet<>();
                stringSet.add(baseCanUserType.getSubClassFullyType());
                implementsJavaPackageMap.put(baseCanUserType.getSubClassType(), stringSet);
                baseCanUserType.setSubClassCanUserType(true);
            }
        }
    }

    /**
     * 处理导入的包，排除不需要导入的包
     *
     * @param implementsJavaPackageMap 需要导入的包 如果有多个，全部使用全限定名，在该map中的，表示没有简称相同的类
     *                                 key - 变量名-简称
     *                                 value - 全限定名称
     * @return
     */
    public static List<JavaImplementsDTO> handleImplements(Map<String, Set<String>> implementsJavaPackageMap) {
        List<JavaImplementsDTO> javaImplementsDTOList = new ArrayList<>();
        // 全称限定名称
        for (String key : implementsJavaPackageMap.keySet()) {
            Set<String> types = implementsJavaPackageMap.get(key);
//            if (types.size() == 1) {
                // 获取导入
                for (String type : types) {
                    // 只有一个 - 排除基础类型，lang包下的类型
                    if (InitConstant.EXCLUDE_IMPORT_TYPE.contains(type)) {
                        continue;
                    }
                    JavaImplementsDTO javaImplementsDTO = new JavaImplementsDTO();
                    javaImplementsDTO.setType(type);
                    javaImplementsDTOList.add(javaImplementsDTO);
                }
//            }
        }
        return javaImplementsDTOList;
    }
}
