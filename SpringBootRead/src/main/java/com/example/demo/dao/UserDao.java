package com.example.demo.dao;

import com.example.demo.dao.entity.User;
import com.example.demo.dao.repository.Page;
import com.example.demo.dao.repository.SqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserDao {
    @Autowired
    private SqlRepository repository;

    public User get(long id) {
        String sql = "SELECT * FROM `user` WHERE `id`=:id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return repository.queryEntity(sql, paramMap, User.class);
    }

    public long getCount(int age) {
        String sql = "SELECT COUNT(1) FROM `user` WHERE `age`=:age";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("age", age);
        return repository.querySingle(sql, paramMap, long.class);
    }

    public List<User> getList(String name) {
        String sql = "SELECT * FROM `user` WHERE `name` LIKE CONCAT('%',:name,'%')";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        return repository.queryList(sql, paramMap, User.class);
    }

    public Page<User> getPageList(int pageNumber, int pageSize, Map<String, String> params) {
        String sql = "SELECT * FROM `user` WHERE 1=1 ";
        Map<String, Object> paramMap = new HashMap<>();
        if (params.containsKey("name") && !params.get("name").isBlank()) {
            sql += " and `name` LIKE CONCAT('%',:name,'%')";
            paramMap.put("name", params.get("name"));
        }
        return repository.queryPagingList(sql, paramMap, pageNumber, pageSize, User.class);
    }
}
