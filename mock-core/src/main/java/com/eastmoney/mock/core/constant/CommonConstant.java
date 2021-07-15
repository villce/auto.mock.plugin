package com.eastmoney.mock.core.constant;

import com.eastmoney.mock.core.entity.ConfigEntity;
import com.thoughtworks.qdox.JavaProjectBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 公用配置
 * @ProjectName: mock-core
 * @Package: com.eastmoney.mock.core.constant
 * @Author: lcz
 * @Date: 2021/7/7 下午3:56
 * @Version: 1.0
 */
public class CommonConstant {
    /**
     * 生成测试类的对应类名 后缀
     */
    public static final String TEST_CLASS_SUFFIX = "Test";
    /**
     * 项目文件路径
     */
    public static final String JAVA_MAIN_SRC = "/src/main/java/";
    /**
     * 测试类路径
     */
    public static final String JAVA_TEST_SRC = "/src/test/java/";
    /**
     * 测试mock req路径
     */
    public static final String TEST_MOCK_REQ = "/req/";
    /**
     * 测试mock res路径
     */
    public static final String TEST_MOCK_RES = "/res/";

    /**
     * 日期
     */
    public static final String DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    /**
     * 项目中配置的属性数据
     */
    public static final ConfigEntity CONFIG_ENTITY = new ConfigEntity();

    /**
     * 获取类库
     * 预加载类信息
     */
    public static JavaProjectBuilder javaProjectBuilder = new JavaProjectBuilder();
    /**
     * 需要跳过的包的类，不进行设置默认值,默认值设置为null
     * 第三方类，无法进行加载的类需要该设置
     * 1.0.0+ 删除
     */
    @Deprecated
    public static Set<String> skipPackage = new HashSet<>();
}
