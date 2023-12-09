package com.maykelmarrero.springboot.finangportal.service;

import com.maykelmarrero.springboot.finangportal.dao.KreditangeboteRepository;
import com.maykelmarrero.springboot.finangportal.entity.Kreditangeboten;
import com.maykelmarrero.springboot.finangportal.entity.KreditangebotenStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class KreditangeboteServiceImplTest {
    @InjectMocks
    public KreditangeboteServiceImpl kreditangeboteServiceImpl;
    @Mock
    public KreditangeboteRepository kreditangeboteRepository;
    @Test
    public void findAllTest() {
        //given
        List<Kreditangeboten> kreditangebotenList = Arrays.asList(new Kreditangeboten("KreditangebotTestName",
                "KreditangebotTestBeschreibung",
                3000,
                BigDecimal.valueOf(30.00),
                LocalDate.now(),
                null,
                new KreditangebotenStatus("StatusNameTest", "StausBeschreibungTest")));
        when(kreditangeboteRepository.findAllByOrderByKreditangebotErstellungsdatumDesc()).thenReturn(kreditangebotenList);
        //when
        List<Kreditangeboten> kreditangebotenListResult = kreditangeboteServiceImpl.findAll();
        //then
        verify(kreditangeboteRepository,times(1)).findAllByOrderByKreditangebotErstellungsdatumDesc();
        Assert.notNull(kreditangebotenListResult,"Ist es nicht null");
        Assert.isTrue(kreditangebotenListResult.get(0).getKreditangebotName().equals("KreditangebotTestName"), "soll true sein" );
    }
}
