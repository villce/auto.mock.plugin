package com.eastmoney.mock.core.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * @Description: description
 * @ProjectName: mock-core
 * @Package: com.eastmoney.mock.core.utils
 * @Author: lcz
 * @Date: 2021/7/1 下午4:59
 * @Version: 1.0
 */
public class StringUtil extends StringUtils {
    /**
     * 随机字符的范围
     * 数字以及大小写字母
     */
    private static char[] strDigits = {
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
     * 首字母转小写
     *
     * @param name 字符串值，必须字母开头
     * @return
     */
    public static String strConvertLowerCamel(String name) {
        if (StringUtils.isBlank(name)) {
            return "";
        }
        String at = String.valueOf(name.charAt(0)).toLowerCase();
        return at + name.substring(1);
    }

    /**
     * 获取随机字符串
     *
     * @param count 字符串长度
     * @return
     */
    public static String getRandomString(int count) {
        return RandomStringUtils.random(count, strDigits);
    }

    /**
     * 获取随机字符
     *
     * @return
     */
    public static char getRandomChar() {
        int rand = RandomUtils.nextInt(0, strDigits.length - 1);
        return strDigits[rand];
    }

    /**
     * 首字母转大写
     *
     * @param name 字符串值，必须字母开头
     * @return
     */
    public static String strConvertUpperCamel(String name) {
        if (StringUtils.isBlank(name)) {
            return "";
        }
        String at = String.valueOf(name.charAt(0)).toUpperCase();
        return at + name.substring(1);
    }

    /**
     * 添加前后分隔符
     *
     * @param configPath 路径
     * @return
     */
    public static String addSeparator(String configPath) {
        // 区分系统
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("windows")) {
            // 只有在非windows才添加前路径分隔符
            if (!configPath.startsWith(File.separator)) {
                configPath = File.separator + configPath;
            }
        }
        if (!configPath.endsWith(File.separator)) {
            configPath = configPath + File.separator;
        }
        return configPath;
    }
}
