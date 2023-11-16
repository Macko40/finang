package com.maykelmarrero.springboot.finangportal.service;

import com.maykelmarrero.springboot.finangportal.entity.Users;
import com.maykelmarrero.springboot.finangportal.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public Users findByUserName(String userName);

    void save(Users users);


}
