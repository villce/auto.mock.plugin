package com.esatmoney.mock.data.json;

import java.util.List;

/**
 * @Description: 读取json配置类的值
 * @ProjectName: mock-data
 * @Package: com.esatmoney.mock.data.json
 * @Author: lcz
 * @Date: 2021/7/7 下午3:58
 * @Version: 1.0
 */
public class JsonConfig {
    /**
     *是否开启json配置-默认false
     */
    private Boolean isOpen;

    /**
     * 值的具体配置
     */
    private List<JsonConfigList> list;

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public List<JsonConfigList> getList() {
        return list;
    }

    public void setList(List<JsonConfigList> list) {
        this.list = list;
    }
}
