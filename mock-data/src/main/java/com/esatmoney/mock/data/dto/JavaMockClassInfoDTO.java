package com.esatmoney.mock.data.dto;

import com.esatmoney.mock.data.base.BaseCanUserType;

/**
 * @Description: description
 * @ProjectName: mock-data
 * @Package: com.esatmoney.mock.data.dto
 * @Author: lcz
 * @Date: 2021/7/2 上午10:08
 * @Version: 1.0
 */
public class JavaMockClassInfoDTO extends BaseCanUserType {
    /**
     * 属性名称
     */
    private String name;
    /**
     * 父类类型
     */
    private String parentClassFullyType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentClassFullyType() {
        return parentClassFullyType;
    }

    public void setParentClassFullyType(String parentClassFullyType) {
        this.parentClassFullyType = parentClassFullyType;
    }
}
