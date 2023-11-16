package com.maykelmarrero.springboot.finangportal.service;

import com.maykelmarrero.springboot.finangportal.entity.Kreditangebotantrag;
import com.maykelmarrero.springboot.finangportal.entity.Kreditangeboten;

import java.util.List;

public interface KreditangebotantragService {
    List<Kreditangebotantrag> getKreditangebotantragList();

    void save(Kreditangebotantrag kreditangebotantrag);



}
