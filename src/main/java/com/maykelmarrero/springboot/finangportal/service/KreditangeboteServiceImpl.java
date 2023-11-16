package com.maykelmarrero.springboot.finangportal.service;

import com.maykelmarrero.springboot.finangportal.dao.KreditangeboteRepository;
import com.maykelmarrero.springboot.finangportal.entity.Kreditangeboten;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class KreditangeboteServiceImpl implements KreditangeboteService {
    // define Field KreditangebotDAO
    public KreditangeboteRepository kreditangeboteRepository;

    // set up constructor injection
    @Autowired
    public KreditangeboteServiceImpl(KreditangeboteRepository kreditangeboteRepository) {
        this.kreditangeboteRepository = kreditangeboteRepository;
    }

    @Override
    public List<Kreditangeboten> findAll() {

        return kreditangeboteRepository.findAllByOrderByKreditangebotErstellungsdatumDesc();
    }

    public List<Kreditangeboten> findAllKreditangeboten(){
        return kreditangeboteRepository.findAll();
    }

    @Override
    public Kreditangeboten findById(int kreditangebotId) {

        Optional<Kreditangeboten> result = kreditangeboteRepository.findById(kreditangebotId);
        Kreditangeboten kreditangeboten = null;

        if (result.isPresent()) {
            kreditangeboten = result.get();
        } else {
            // Kreditangebot nicht gefunden
            throw new RuntimeException("Kreditangebot nicht gefunden mit id - " + kreditangebotId);

        }
        return kreditangeboten;
    }

    @Override
    public List<Kreditangeboten> getVeroeffentlichtenKreditangebote() {
        int veroeffentlichKreditangeboteStatusId = 10;

        return kreditangeboteRepository.findByKreditangebotenStatus_StatusId(veroeffentlichKreditangeboteStatusId);
    }


    @Override
    public void save(Kreditangeboten kreditangeboten) {
        // Set the creation date before saving the entity
        if(kreditangeboten.getKreditangebotErstellungsdatum() == null)
            kreditangeboten.setKreditangebotErstellungsdatum(LocalDate.now());

        kreditangeboteRepository.save(kreditangeboten);
    }

    @Override
    public void deleteById(int id) {
        kreditangeboteRepository.deleteById(id);
    }


}
