package com.maykelmarrero.springboot.finangportal.dao;

import com.maykelmarrero.springboot.finangportal.entity.Kreditangeboten;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface KreditangeboteRepository extends JpaRepository<Kreditangeboten, Integer> {
    // Das war's. Man muss keinen Code schreiben.


    // add a method to sort by Erstellungsdatum
    public List<Kreditangeboten> findAllByOrderByKreditangebotErstellungsdatumDesc();

    // get veröffentlichten Kreditangebote by Id
    public List<Kreditangeboten> findByKreditangebotenStatus_StatusId(int statusId);

}
























