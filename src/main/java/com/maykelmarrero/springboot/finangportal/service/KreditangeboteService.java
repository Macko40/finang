package com.maykelmarrero.springboot.finangportal.service;

import com.maykelmarrero.springboot.finangportal.entity.Kreditangeboten;

import java.util.List;

public interface KreditangeboteService {

    List<Kreditangeboten> findAll();

    Kreditangeboten findById(int theId);

    List<Kreditangeboten> getVeroeffentlichtenKreditangebote();

    void save(Kreditangeboten kreditangeboten);

    void deleteById(int theId);
}





















