package mock.test.demo.service;

import mock.test.demo.model.Demo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @Description: description
 * @ProjectName: mock-maven-plugin
 * @Package: mock.test.demo.service
 * @Author: 雷才哲
 * @Date: 2021/7/7 下午2:11
 * @Version: 1.0
 */
@Service
public class DemoServiceImpl implements DemoService{
    @Autowired
    private MockService mockService;

    public void demo1(String str) {
        if (StringUtils.isBlank(str)) {
            System.out.println("lcz");
        }
        System.out.println(str);
    }
    public Demo demo2(Map<String, Date> map, int[] ints, HttpServletResponse response) {
        map.put("vilce", new Date());
        Demo demo = new Demo();
        demo.setStr(mockService.mock(map.get("vilce")));
        demo.setDate(new Date());
        return demo;
    }
}
