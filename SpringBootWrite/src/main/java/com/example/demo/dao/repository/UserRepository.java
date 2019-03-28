package com.example.demo.dao.repository;

import com.example.demo.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select count(t.id) from User t where t.age=:age")
    long count(@Param("age") int age);
}
