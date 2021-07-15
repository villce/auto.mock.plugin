package mock.test.demo.service;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description: description
 * @ProjectName: com.eastmoney.emis.mock
 * @Package: mock.test.demo.service
 * @Author: 雷才哲
 * @Date: 2021/7/13 下午3:43
 * @Version: 1.0
 */
@Service
public class MockServiceImpl implements MockService{

    public String mock(Date date) {
        return DateFormatUtils.format(date, "yyyy-MM-dd");
    }
}
