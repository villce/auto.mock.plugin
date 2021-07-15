package com.esatmoney.mock.data.model;

/**
 * @Description: java参数信息
 * @ProjectName: mock-data
 * @Package: com.esatmoney.mock.data.model
 * @Author: lcz
 * @Date: 2021/7/1 下午5:50
 * @Version: 1.0
 */
public class JavaParameterModel {
    /**
     * 参数名称
     */
    private String name;
    /**
     * 参数名称 - 首字母大写
     */
    private String upName;
    /**
     * 参数类型
     */
    private String type;
    /**
     * 参数类型 - 全限定 名称
     */
    private String fullyType;
    /**
     * 是否自定义类型
     */
    private Boolean customType;

    /**
     * 唯一标识
     */
    private String keyName;

    /**
     * 默认值
     */
    private String value;

    /**
     * 参数的一个顺序
     */
    private Integer order;

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

    public Boolean getCustomType() {
        return customType;
    }

    public void setCustomType(Boolean customType) {
        this.customType = customType;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
