package com.example.demo.service;

import com.example.demo.dao.DemoDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.entity.Demo;
import com.example.demo.dao.entity.User;
import com.example.demo.dao.repository.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class DemoService {
    @Autowired
    private DemoDao demoDao;
    @Autowired
    private UserDao userDao;

    public void get() {

    }

    public void count() {
        String id = "4abeb33c421540d3b2dad2cd3372c297";

        long count = userDao.getCount(20);
        System.out.println("count:" + count);

        BigDecimal obj = demoDao.sumPrice(id);
        if (obj != null) {
            var a = 1;
        }

        BigDecimal price = new BigDecimal("1");
        List<Demo> list2 = demoDao.getList(price);

        User user = userDao.get(20);

        String name = "你好";
        List<User> users = userDao.getList(name);

        List<Demo> demoList = demoDao.getListAll();

        id = "qwewt432554364576fuyujtu57657568";
        Demo demo = demoDao.get(id);

        int pageNumber = 2;
        int pageSize = 5;
        Map<String, String> params = new HashMap<>();
        params.put("name", "你好");

        Page<User> userPage = userDao.getPageList(pageNumber, pageSize, params);
        System.out.println("TotalElements:" + userPage.getTotalElements());
        var b = 1;
    }
}
