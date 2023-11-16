package com.maykelmarrero.springboot.finangportal.service;

import com.maykelmarrero.springboot.finangportal.dao.KreditangebotantragRepository;
import com.maykelmarrero.springboot.finangportal.entity.Kreditangebotantrag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class KreditangebotantragServiceImpl implements KreditangebotantragService {

    private KreditangebotantragRepository kreditangebotantragRepository;

    @Autowired
    public KreditangebotantragServiceImpl(KreditangebotantragRepository kreditangebotantragRepository) {
        this.kreditangebotantragRepository = kreditangebotantragRepository;
    }

    @Override
    public List<Kreditangebotantrag> getKreditangebotantragList() {


        return kreditangebotantragRepository.findAllByOrderByAntragErstellungsdatumDesc();
    }

    @Override
    public void save(Kreditangebotantrag kreditangebotantrag) {
        // Set the creation date before saving the entity
        kreditangebotantrag.setAntragErstellungsdatum(LocalDate.now());

        kreditangebotantragRepository.save(kreditangebotantrag);
    }


}
