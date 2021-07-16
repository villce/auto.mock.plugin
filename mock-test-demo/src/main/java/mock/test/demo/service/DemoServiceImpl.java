package mock.test.demo.service;

import mock.test.demo.model.Demo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Value("${test}")
    private static String test;

    public void demo1(String str) {
        if (StringUtils.isBlank(str)) {
            System.out.println("lcz");
        }
        System.out.println(test);
    }
    public Demo demo2(Map<String, Date> map, int[] ints) {
        map.put("vilce", new Date());
        Demo demo = new Demo();
        String str = redisTemplate.opsForValue().get("vilce");
        if (StringUtils.isBlank(str)) {
            demo.setStr(mockService.mock(map.get("vilce")));
            redisTemplate.opsForValue().set("vilce", "vilce", 10, TimeUnit.SECONDS);
        } else {
            demo.setStr(str);
        }
        demo.setDate(new Date());
        return demo;
    }
}
