package com.maykelmarrero.springboot.finangportal.dao;

import com.maykelmarrero.springboot.finangportal.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {


}
