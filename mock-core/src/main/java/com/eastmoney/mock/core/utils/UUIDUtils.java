package com.eastmoney.mock.core.utils;

import java.util.UUID;

/**
 * @Description: description
 * @ProjectName: com.eastmoney.emis.mock
 * @Package: com.eastmoney.mock.core.utils
 * @Author: lcz
 * @Date: 2021/7/14 下午5:00
 * @Version: 1.0
 */
public class UUIDUtils {
    /**
     * 生成10位UUID
     *
     * @return 生成10位UUID
     */
    public static String getID() {
        UUID uuid = UUID.randomUUID();
        // 改变uuid的生成规则
        return HashUtils.convertToHashStr(uuid.getMostSignificantBits(), 5)
                + HashUtils.convertToHashStr(uuid.getLeastSignificantBits(), 5);
    }
}
