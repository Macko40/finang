package com.maykelmarrero.springboot.finangportal.dao;

import com.maykelmarrero.springboot.finangportal.entity.Kreditangebotantrag;
import com.maykelmarrero.springboot.finangportal.entity.Kreditangeboten;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KreditangebotantragRepository extends JpaRepository<Kreditangebotantrag,Integer> {
    // Das war's. Man muss keinen Code schreiben.

    // add a method to sort by Erstellungsdatum
    public List<Kreditangebotantrag> findAllByOrderByAntragErstellungsdatumDesc();

}
