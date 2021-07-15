package com.eastmoney.mock.core.entity;

import com.esatmoney.mock.data.json.JsonConfig;

import java.io.File;

/**
 * @Description: 配置属性
 * @ProjectName: mock-core
 * @Package: com.eastmoney.mock.core.constant
 * @Author: lcz
 * @Date: 2021/7/7 下午3:56
 * @Version: 1.0
 */
public class ConfigEntity {
    /**
     * 需要测试的项目包名
     */
    private String testPackageName;
    /**
     * 作者
     */
    private String author;
    /**
     * 是否获取子包下的类
     */
    private Boolean isGetChildPackage;


    /**
     * 运行项目的target路径
     */
    private String target;

    /**
     * 运行项目的跟路径
     */
    private File basedir;

    /**
     * 运行项目名
     */
    private String project;

    /**
     * 配置文件路径
     */
    private String configPath;

    /**
     * 配置文件名称
     */
    private String configFileName;


    /**
     * 配置是否mock掉父类以及自身测试类非测试的方法
     */
    private Boolean isMockThisOtherMethod;

    /**
     * 配置是否设置基础类型的值随机生成
     */
    private Boolean isSetBasicTypesRandomValue;


    /**
     * 配置字符串随机值的位数（例如："10"，表示10位随机字母/数字字符）
     *
     */
    private String setStringRandomRange;
    /**
     * 配置int/Integer类型随机值的范围（例如："0,1000"，表示[0,1000]范围的int数值，配置固定的值可配置为"0",则int值固定为0）
     *
     */
    private String setIntRandomRange;
    /**
     * 配置long/Long类型随机值的范围(配置规则与setIntRandomRange类似)
     *
     */
    private String setLongRandomRange;
    /**
     * 配置boolean/Boolean类型随机值的范围（例如："true,false"，表示true和false随机。配置为"true"/"false"表示为固定的值）
     *
     */
    private String setBooleanRandomRange;

    /**
     * json的配置值
     */
    private JsonConfig jsonConfig;

    /**
     * json配置文件路径
     */
    private String jsonConfigPath;

    /**
     * json配置文件名称
     */
    private String jsonConfigFileName;


    protected Boolean isDownloadTemplateFile;

    /**
     * 是否将json配置文件下载到本地，默认true
     */
    protected Boolean isDownloadJsonFile;

    public String getTestPackageName() {
        return testPackageName;
    }

    public void setTestPackageName(String testPackageName) {
        this.testPackageName = testPackageName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getGetChildPackage() {
        return isGetChildPackage;
    }

    public void setGetChildPackage(Boolean getChildPackage) {
        isGetChildPackage = getChildPackage;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public File getBasedir() {
        return basedir;
    }

    public void setBasedir(File basedir) {
        this.basedir = basedir;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigFileName() {
        return configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    public Boolean getMockThisOtherMethod() {
        return isMockThisOtherMethod;
    }

    public void setMockThisOtherMethod(Boolean mockThisOtherMethod) {
        isMockThisOtherMethod = mockThisOtherMethod;
    }

    public Boolean getSetBasicTypesRandomValue() {
        return isSetBasicTypesRandomValue;
    }

    public void setSetBasicTypesRandomValue(Boolean setBasicTypesRandomValue) {
        isSetBasicTypesRandomValue = setBasicTypesRandomValue;
    }

    public String getSetStringRandomRange() {
        return setStringRandomRange;
    }

    public void setSetStringRandomRange(String setStringRandomRange) {
        this.setStringRandomRange = setStringRandomRange;
    }

    public String getSetIntRandomRange() {
        return setIntRandomRange;
    }

    public void setSetIntRandomRange(String setIntRandomRange) {
        this.setIntRandomRange = setIntRandomRange;
    }

    public String getSetLongRandomRange() {
        return setLongRandomRange;
    }

    public void setSetLongRandomRange(String setLongRandomRange) {
        this.setLongRandomRange = setLongRandomRange;
    }

    public String getSetBooleanRandomRange() {
        return setBooleanRandomRange;
    }

    public void setSetBooleanRandomRange(String setBooleanRandomRange) {
        this.setBooleanRandomRange = setBooleanRandomRange;
    }

    public JsonConfig getJsonConfig() {
        return jsonConfig;
    }

    public void setJsonConfig(JsonConfig jsonConfig) {
        this.jsonConfig = jsonConfig;
    }

    public String getJsonConfigPath() {
        return jsonConfigPath;
    }

    public void setJsonConfigPath(String jsonConfigPath) {
        this.jsonConfigPath = jsonConfigPath;
    }

    public String getJsonConfigFileName() {
        return jsonConfigFileName;
    }

    public void setJsonConfigFileName(String jsonConfigFileName) {
        this.jsonConfigFileName = jsonConfigFileName;
    }

    public Boolean getDownloadTemplateFile() {
        return isDownloadTemplateFile;
    }

    public void setDownloadTemplateFile(Boolean downloadTemplateFile) {
        isDownloadTemplateFile = downloadTemplateFile;
    }

    public Boolean getDownloadJsonFile() {
        return isDownloadJsonFile;
    }

    public void setDownloadJsonFile(Boolean downloadJsonFile) {
        isDownloadJsonFile = downloadJsonFile;
    }
}
