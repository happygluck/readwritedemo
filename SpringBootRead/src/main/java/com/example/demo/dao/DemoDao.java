package com.example.demo.dao;

import com.example.demo.dao.entity.Demo;
import com.example.demo.dao.repository.SqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DemoDao {

    @Autowired
    private SqlRepository repository;

    public BigDecimal sumPrice(String id) {
        String sql = "SELECT SUM(`price`) FROM `demo` WHERE `id`=:id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return repository.querySingle(sql, paramMap, BigDecimal.class);
    }

    public List<Demo> getList(BigDecimal price) {
        String sql = "SELECT * FROM `demo` WHERE `price`=:price";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("price", price);
        return repository.queryList(sql, paramMap, Demo.class);
    }

    public Demo get(String id) {
        String sql = "SELECT * FROM `demo` WHERE `id`=:id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return repository.queryEntity(sql, paramMap, Demo.class);
    }

    public List<Demo> getListAll() {
        String sql = "SELECT * FROM `demo` ORDER BY `userId`";
        Map<String, Object> paramMap = new HashMap<>();
        return repository.queryList(sql, paramMap, Demo.class);
    }
}
