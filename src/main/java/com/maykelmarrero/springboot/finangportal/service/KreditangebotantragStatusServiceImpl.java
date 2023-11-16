package com.maykelmarrero.springboot.finangportal.service;

import com.maykelmarrero.springboot.finangportal.dao.KreditangebotantragStatusRepository;
import com.maykelmarrero.springboot.finangportal.entity.KreditangebotantragStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KreditangebotantragStatusServiceImpl implements KreditangebotantragStatusService {

    public KreditangebotantragStatusRepository kreditangebotantragStatusRepository;

    @Autowired
    public KreditangebotantragStatusServiceImpl(KreditangebotantragStatusRepository kreditangebotantragStatusRepository) {
        this.kreditangebotantragStatusRepository = kreditangebotantragStatusRepository;
    }

    @Override
    public List<KreditangebotantragStatus> getKreditangebotantragStatusName() {
        return kreditangebotantragStatusRepository.findAll();
    }
}
