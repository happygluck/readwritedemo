package com.example.demo.controller;

import com.example.demo.service.DemoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoController {

    private static final Logger logger = LogManager.getLogger(DemoController.class);

    @Autowired
    private DemoService demoService;

    @PostMapping("test")
    public String test() {
        long beg = System.currentTimeMillis();

        demoService.get();

        demoService.count();

        long end = System.currentTimeMillis();
        long cha = end - beg;
        System.out.println("耗时：" + cha);
        return "OK!" + cha + "毫秒";
    }

    @PostMapping("test2")
    public String test2() {
        logger.info("我是 Info");
        logger.error("我是 Error");

        int a = Integer.parseInt("aaa");
        return "OK!";
    }
}
