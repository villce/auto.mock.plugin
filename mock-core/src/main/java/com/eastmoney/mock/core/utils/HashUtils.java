package com.eastmoney.mock.core.utils;

/**
 * @Description: description
 * @ProjectName: com.eastmoney.emis.mock
 * @Package: com.eastmoney.mock.core.utils
 * @Author: lcz
 * @Date: 2021/7/14 下午5:00
 * @Version: 1.0
 */
public class HashUtils {
    private static char[] charDigits = {
            '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * 将 hash 转换为  len位数
     *
     * @param hid hash字符串
     * @param len 转换后的长度
     * @return 转换后的字符串
     */
    public static String convertToHashStr(long hid, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = charDigits[(int) ((hid & 0xff) % charDigits.length)];
            sb.append(c);
            hid = hid >> 6;
        }
        return sb.toString();
    }
}
