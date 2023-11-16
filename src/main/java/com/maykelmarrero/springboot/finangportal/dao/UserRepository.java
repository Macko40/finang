package com.maykelmarrero.springboot.finangportal.dao;

import com.maykelmarrero.springboot.finangportal.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users,Integer> {

    Users findByUserName(String userName);
}
