package com.example.demo;

import com.example.demo.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringBootWriteApplication.class)
@SpringBootTest
public class SpringBoot2ApplicationTests {
    @Autowired
    private DemoService demoService;

    @Test
    public void edit() {
        demoService.edit();
    }

    @Test
    public void get() {
        long beg = System.currentTimeMillis();
        long end = System.currentTimeMillis();

        System.out.println("耗时：" + (end - beg));
    }
}
