package com.example.demo.controller;

import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @PostMapping("test")
    public String test() {
        long beg = System.currentTimeMillis();

        demoService.edit();

        long end = System.currentTimeMillis();
        long cha = end - beg;
        System.out.println("耗时：" + cha);
        return "OK!" + cha + "毫秒";
    }
}
