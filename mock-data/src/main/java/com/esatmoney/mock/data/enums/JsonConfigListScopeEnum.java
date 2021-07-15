package com.esatmoney.mock.data.enums;

/**
 * @Description: description
 * @ProjectName: mock-data
 * @Package: com.esatmoney.mock.data.enums
 * @Author: lcz
 * @Date: 2021/7/2 下午3:36
 * @Version: 1.0
 */
public enum JsonConfigListScopeEnum {
    /**
     * 作用域：全局（global）、包（package）、类（class）、方法（method）
     */
    GLOBAL("全局","global"),
    PACKAGE("包","package"),
    CLASS("类","class"),
    METHOD("方法","method"),
    ;

    private String name;
    private String value;

    JsonConfigListScopeEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for property <tt>value</tt>.
     *
     * @return property value of value
     */
    public String getValue() {
        return value;
    }

    public static JsonConfigListScopeEnum getByValue(String value) {
        JsonConfigListScopeEnum[] valueList = values();
        for (JsonConfigListScopeEnum v : valueList) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        return null;
    }
}
