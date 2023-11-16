package com.maykelmarrero.springboot.finangportal.service;

import com.maykelmarrero.springboot.finangportal.dao.RoleRepository;
import com.maykelmarrero.springboot.finangportal.dao.UserRepository;
import com.maykelmarrero.springboot.finangportal.entity.Role;
import com.maykelmarrero.springboot.finangportal.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    public UserRepository userRepository;
    public RoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public Users findByUserName(String userName) {
        return null;
    }

    @Override
    public void save(Users users) {
        // Set the creation date before saving the entity
        if (users.getUserErstellungsdatum() == null)
            users.setUserErstellungsdatum(LocalDate.now());

        users.setEnabled(true);
        // Find Role with ID 3 (represent user role)
        Role userRole = roleRepository.findById(3).orElse(null);

        // Assign Role to the User
        if (userRole != null) {
            users.setRoles(Collections.singleton(userRole));
        }
        userRepository.save(users);
    }

/*
    @Override
    public List<Users> findAll() {

        return userRepository.findAll();
    }

    @Override
    public Users findById(int userId) {

        Optional<Users> result = userRepository.findById(userId);
        Users users = null;

        if (result.isPresent()) {
            users = result.get();
        } else {
            // User nicht gefunden
            throw new RuntimeException("User nicht gefunden mit id - " + userId);

        }
        return users;


    }

    @Override
    public void save(Users users) {

    }

    @Override
    public void deleteById(int id) {

    }*/


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }


}
