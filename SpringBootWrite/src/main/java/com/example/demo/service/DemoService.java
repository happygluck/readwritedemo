package com.example.demo.service;

import com.example.demo.dao.DemoDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.entity.Demo;
import com.example.demo.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Optional;

@Service
@Transactional
public class DemoService {
    @Autowired
    private DemoDao demoDao;
    @Autowired
    private UserDao userDao;

    public void edit() {
        String id = "8942245788614c288f21fd9e5b6b9ad25";
        Optional<Demo> optional = demoDao.get(id);
        if (optional.isEmpty()) {
            Demo entity = new Demo();
            entity.setOrderNo("E" + System.currentTimeMillis());
            entity.setPrice(new BigDecimal("4.90"));
            entity.setUserId(2l);
            entity.setType(1);
            entity.setRate(0.00000890f);
            entity.setContent(" AA BB");
            entity.setMyTime(LocalTime.now());
            String orderNo = demoDao.edit(entity);
            System.out.println(orderNo);
        } else {
            Demo entity = optional.get();
            entity.setPrice(new BigDecimal("3.66"));
        }

        User user = new User();
        user.setName("你a");
        user.setAge(20);
        userDao.edit(user);

    }

}
