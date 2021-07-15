package mock.test.demo.controller;

import mock.test.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: description
 * @ProjectName: mock-maven-plugin
 * @Package: mock.test.demo.controller
 * @Author: 雷才哲
 * @Date: 2021/7/7 下午2:10
 * @Version: 1.0
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("test")
    public void demo(@RequestParam String str) {
        demoService.demo1(str);
    }
}
