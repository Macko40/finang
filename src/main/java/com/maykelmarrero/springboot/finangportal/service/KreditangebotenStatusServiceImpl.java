package com.maykelmarrero.springboot.finangportal.service;

import com.maykelmarrero.springboot.finangportal.dao.KreditangebotenStatusRepository;
import com.maykelmarrero.springboot.finangportal.entity.KreditangebotenStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KreditangebotenStatusServiceImpl implements KreditangebotenStatusService {

    public KreditangebotenStatusRepository kreditangebotenStatusRepository;

    @Autowired
    public KreditangebotenStatusServiceImpl(KreditangebotenStatusRepository kreditangebotenStatusRepository) {
        this.kreditangebotenStatusRepository = kreditangebotenStatusRepository;
    }

    @Override
    public List<KreditangebotenStatus> getKreditangebotenStatusName() {
        return kreditangebotenStatusRepository.findAll();
    }
}
