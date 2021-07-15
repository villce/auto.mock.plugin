package com.esatmoney.mock.data.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: description
 * @ProjectName: mock-data
 * @Package: com.eastmoney.mock.data.dto
 * @Author: lcz
 * @Date: 2021/7/2 上午10:03
 * @Version: 1.0
 */
public class BaseCanUserType {
    /**
     * 参数类型 - 简称 ,如果有多个简称，则会使用全称
     */
    private String type;

    /**
     * 全限定名称，类型
     */
    private String fullyType;

    /**
     * 是否能够使用简称
     */
    private Boolean canUserType = false;

    /**
     * 是否是接口
     */
    private Boolean inter = false;
    /**
     * 接口的实现类类型 - 简称
     */
    private List<String> subClassTypeList = new ArrayList<>();

    /**
     * 子实现类的简称
     */
    private String subClassType;
    /**
     * 子实现类的全限定名称
     */
    private String subClassFullyType;
    /**
     * 子类是否能够使用简称
     */
    private Boolean subClassCanUserType = false;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullyType() {
        return fullyType;
    }

    public void setFullyType(String fullyType) {
        this.fullyType = fullyType;
    }

    public Boolean getCanUserType() {
        return canUserType;
    }

    public void setCanUserType(Boolean canUserType) {
        this.canUserType = canUserType;
    }

    public Boolean getInter() {
        return inter;
    }

    public void setInter(Boolean inter) {
        this.inter = inter;
    }

    public List<String> getSubClassTypeList() {
        return subClassTypeList;
    }

    public void setSubClassTypeList(List<String> subClassTypeList) {
        this.subClassTypeList = subClassTypeList;
    }

    public String getSubClassType() {
        return subClassType;
    }

    public void setSubClassType(String subClassType) {
        this.subClassType = subClassType;
    }

    public String getSubClassFullyType() {
        return subClassFullyType;
    }

    public void setSubClassFullyType(String subClassFullyType) {
        this.subClassFullyType = subClassFullyType;
    }

    public Boolean getSubClassCanUserType() {
        return subClassCanUserType;
    }

    public void setSubClassCanUserType(Boolean subClassCanUserType) {
        this.subClassCanUserType = subClassCanUserType;
    }
}
