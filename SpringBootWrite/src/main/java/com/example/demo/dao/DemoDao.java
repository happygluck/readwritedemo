package com.example.demo.dao;

import com.example.demo.dao.entity.Demo;
import com.example.demo.dao.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DemoDao {

    @Autowired
    private DemoRepository repository;

    public String edit(Demo entity) {
        entity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        Demo demo = repository.save(entity);
        return demo.getId();
    }

    public Optional<Demo> get(String id) {
        return repository.findById(id);
    }

    public BigDecimal sumPrice(String id) {
        return repository.sumPrice(id);
    }

    public List<Demo> getList() {
        return repository.findAll();
    }

    public List<Demo> getList(BigDecimal price) {
        return repository.getAllByPrice(price);
    }
}
