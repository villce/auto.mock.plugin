package com.eastmoney.mock.plugin.base;

import com.eastmoney.mock.core.constant.CommonConstant;
import com.eastmoney.mock.core.utils.StringUtil;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * @Description: 插件基础配置
 * @ProjectName: mock-plugin
 * @Package: com.eastmoney.mock.plugin.base
 * @Author: lcz
 * @Date: 2021/7/7 下午4:01
 * @Version: 1.0
 */
public abstract class AbstractPlugin extends AbstractMojo {

    /**
     * 运行项目的target路径
     */
    @Parameter(property = "target", defaultValue = "${project.build.directory}")
    protected String target;

    /**
     * 运行项目的根路径
     */
    @Parameter(defaultValue = "${project.basedir}")
    protected File basedir;

    /**
     * 运行项目名
     */
    @Parameter(defaultValue = "${project.name}")
    protected String project;

    /**
     * 配置文件路径
     */
    @Parameter(defaultValue = "/src/main/resources/test/template/")
    protected String configPath;

    /**
     * 配置文件名称
     */
    @Parameter(defaultValue = "mock-test.ftl")
    protected String configFileName;

    /**
     * json配置文件路径
     */
    @Parameter(defaultValue = "/src/main/resources/test/template/")
    protected String jsonConfigPath;

    /**
     * json配置文件名称
     */
    @Parameter(defaultValue = "mock-test.json")
    protected String jsonConfigFileName;

    /**
     * 是否将Template配置文件下载到本地，默认true
     */
    @Parameter(defaultValue = "true")
    protected Boolean isDownloadTemplateFile;

    /**
     * 是否将json配置文件下载到本地，默认true
     */
    @Parameter(defaultValue = "true")
    protected Boolean isDownloadJsonFile;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        // 初始化基础配置信息
        CommonConstant.CONFIG_ENTITY.setBasedir(basedir);
        CommonConstant.CONFIG_ENTITY.setTarget(target);
        CommonConstant.CONFIG_ENTITY.setProject(project);
        CommonConstant.CONFIG_ENTITY.setConfigPath(StringUtil.addSeparator(configPath));
        CommonConstant.CONFIG_ENTITY.setConfigFileName(configFileName);
        CommonConstant.CONFIG_ENTITY.setJsonConfigPath(StringUtil.addSeparator(jsonConfigPath));
        CommonConstant.CONFIG_ENTITY.setJsonConfigFileName(jsonConfigFileName);
        CommonConstant.CONFIG_ENTITY.setDownloadTemplateFile(isDownloadTemplateFile);
        CommonConstant.CONFIG_ENTITY.setDownloadJsonFile(isDownloadJsonFile);
    }
}
