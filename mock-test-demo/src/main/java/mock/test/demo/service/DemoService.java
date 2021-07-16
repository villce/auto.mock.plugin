package mock.test.demo.service;

import mock.test.demo.model.Demo;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @Description: description
 * @ProjectName: mock-maven-plugin
 * @Package: mock.test.demo.service
 * @Author: 雷才哲
 * @Date: 2021/7/7 下午2:10
 * @Version: 1.0
 */
public interface DemoService {
    void demo1(String str);

    Demo demo2(Map<String, Date> map, int[] ints);
}
