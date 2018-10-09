package com;

import com.app.Application;
import com.app.service.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})// 指定启动类
public class MemberTest {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        System.out.println("-----测试完毕-------");

    }
}
