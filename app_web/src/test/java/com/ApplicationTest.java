package com;

import com.app.Application;
import com.app.service.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dongwk
 * @date 2018/11/26 17:00
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})// 指定启动类
public class ApplicationTest {
    @Autowired
    private UserService userService;

    @Test
    public void aop(){
        userService.login("zhangsan", "123456");
    }
}
