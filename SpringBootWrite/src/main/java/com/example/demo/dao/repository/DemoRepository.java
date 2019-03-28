package com.example.demo.dao.repository;

import com.example.demo.dao.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface DemoRepository extends JpaRepository<Demo, String> {

    @Query("select sum(t.price) from Demo t where t.id=:id")
    BigDecimal sumPrice(@Param("id") String id);

    @Query("select t from Demo t where t.price=:price")
    List<Demo> getAllByPrice(@Param("price") BigDecimal price);
}
