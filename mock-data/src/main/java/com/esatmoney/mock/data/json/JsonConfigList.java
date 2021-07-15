package com.esatmoney.mock.data.json;

import com.esatmoney.mock.data.enums.JsonConfigListScopeEnum;

/**
 * @Description: description
 * @ProjectName: mock-data
 * @Package: com.esatmoney.mock.data.json
 * @Author: lcz
 * @Date: 2021/7/7 下午3:58
 * @Version: 1.0
 */
public class JsonConfigList {
    /**
     * 作用域：全局（global）、包（package）、类（class）、方法（method） - 默认全局
     */
    private String scope = JsonConfigListScopeEnum.GLOBAL.getValue();

    /**
     * 作用域的值，global则无需配置该值，package则为包名，class则为类名，method则为方法名
     */
    private String scopeValue;

    /**
     * 类型的全限定名称
     */
    private String fullyType;

    /**
     * 类型的值
     */
    private String value;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getScopeValue() {
        return scopeValue;
    }

    public void setScopeValue(String scopeValue) {
        this.scopeValue = scopeValue;
    }

    public String getFullyType() {
        return fullyType;
    }

    public void setFullyType(String fullyType) {
        this.fullyType = fullyType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
