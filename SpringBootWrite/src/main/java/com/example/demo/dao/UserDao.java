package com.example.demo.dao;

import com.example.demo.dao.entity.User;
import com.example.demo.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDao {
    @Autowired
    private UserRepository repository;

    public long edit(User entity) {
        User user = repository.save(entity);
        return user.getId();
    }

    public User get(long id) {
        Optional<User> optional = repository.findById(id);
        if (!optional.isEmpty())
            return optional.get();

        return null;
    }

    public long getCount(int age) {
        return repository.count(age);
    }
}
