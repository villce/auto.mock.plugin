package com.eastmoney.mock.plugin;

import com.alibaba.fastjson.JSON;
import com.eastmoney.emis.utils.log.utils.LoggerUtil;
import com.eastmoney.mock.core.constant.CommonConstant;
import com.eastmoney.mock.core.utils.FileUtils;
import com.eastmoney.mock.core.utils.UUIDUtils;
import com.esatmoney.mock.data.json.JsonConfig;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Description: 初始化配置文件
 * @ProjectName: mock-plugin
 * @Package: com.eastmoney.mock.plugin
 * @Author: lcz
 * @Date: 2021/7/7 下午4:50
 * @Version: 1.0
 */
public class DownFile {

    private static String tmpFtlFileName = "mock-test-" + UUIDUtils.getID() + ".ftl";

    /**
     * 下载模版文件
     */
    public static void downTemplateFile() {
        if (!CommonConstant.CONFIG_ENTITY.getDownloadTemplateFile()) {
            CommonConstant.CONFIG_ENTITY.setConfigPath("");
            CommonConstant.CONFIG_ENTITY.setConfigFileName(tmpFtlFileName);
        }
        // 下载文件到本地
        String configPath = CommonConstant.CONFIG_ENTITY.getConfigPath();
        String path = CommonConstant.CONFIG_ENTITY.getBasedir().getPath() + configPath;
        try {
            FileUtils.downLoadFile(CommonConstant.CONFIG_ENTITY.getConfigFileName(), path, "mock-test.ftl");
        } catch (Exception e) {
            LoggerUtil.error(DownFile.class, "下载配置文件出现异常：" + e.getMessage());
        }
    }

    /**
     * 移除配置文件
     */
    public static void removeTemplateFile() {
        if (!CommonConstant.CONFIG_ENTITY.getDownloadTemplateFile()) {
            String configPath = CommonConstant.CONFIG_ENTITY.getConfigPath();
            String path = CommonConstant.CONFIG_ENTITY.getBasedir().getPath() + configPath;
            File file = new File(path + File.separator + CommonConstant.CONFIG_ENTITY.getConfigFileName());
            if (file.exists()) {
                boolean delete = file.delete();
                if (!delete) {
                    LoggerUtil.error(DownFile.class, "删除临时配置文件失败，路径：" + file.getPath() + "，文件名：" + file.getName());
                } else {
                    LoggerUtil.error(DownFile.class, "删除临时配置文件成功，路径：" + file.getPath() + "，文件名：" + file.getName());
                }
            }
        }
    }

    /**
     * 下载json文件
     */
    public static void downJsonFile() {
        try {
            String jsonStr = "";
            if (!CommonConstant.CONFIG_ENTITY.getDownloadJsonFile()) {
                // 创建目录
                ClassPathResource classPathResource = new ClassPathResource("mock-test.json");
                // 得到输入流
                InputStream inputStream = classPathResource.getInputStream();
                jsonStr = FileUtils.readInputStreamToString(inputStream);
            } else {
                String configPath = CommonConstant.CONFIG_ENTITY.getJsonConfigPath();
                String path = CommonConstant.CONFIG_ENTITY.getBasedir().getPath() + configPath;
                FileUtils.downLoadFile(CommonConstant.CONFIG_ENTITY.getJsonConfigFileName(), path, "mock-test.json");
                // 读取json配置到实体中
                jsonStr = FileUtils.readFileToString(path + File.separator + CommonConstant.CONFIG_ENTITY.getJsonConfigFileName());
            }
            LoggerUtil.info(DownFile.class, "读取的json配置文件内容为：" + jsonStr);
            JsonConfig jsonConfig = JSON.parseObject(jsonStr, JsonConfig.class);
            CommonConstant.CONFIG_ENTITY.setJsonConfig(jsonConfig);
        } catch (Exception e) {
            LoggerUtil.error(DownFile.class, "下载JSON配置文件/读取/解析json实体出现异常：" + e.getMessage());
        }
    }
}
