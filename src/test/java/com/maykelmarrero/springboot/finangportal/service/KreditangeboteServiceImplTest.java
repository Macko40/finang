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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class KreditangeboteServiceImplTest {
    @InjectMocks
    public KreditangeboteServiceImpl kreditangeboteServiceImpl;
    @Mock
    public KreditangeboteRepository kreditangeboteRepository;
    @Test
    public void findAllTest() {
        //given Mock-Daten erstellen
        List<Kreditangeboten> kreditangebotenList = Arrays.asList(new Kreditangeboten(
                "KreditangebotTestName",
                "KreditangebotTestBeschreibung",
                3000,
                BigDecimal.valueOf(30.00),
                LocalDate.now(),
                null,
                new KreditangebotenStatus("StatusNameTest", "StatusBeschreibungTest")));
        when(kreditangeboteRepository.findAllByOrderByKreditangebotErstellungsdatumDesc()).thenReturn(kreditangebotenList);
        //when Testenden Methodenaufruf
        List<Kreditangeboten> kreditangebotenListResult = kreditangeboteServiceImpl.findAll();
        // then Überprüfen, das erwartete Verhalten und das Ergebnis
        verify(kreditangeboteRepository,times(1)).findAllByOrderByKreditangebotErstellungsdatumDesc();
        Assert.notNull(kreditangebotenListResult,"Ist es nicht null");
        Assert.isTrue(kreditangebotenListResult.get(0).getKreditangebotName().equals("KreditangebotTestName"), "soll true sein" );
    }

    @Test
    public void testFindById_KreditangebotGefunden() {
        // given Mock-Daten erstellen
        int kreditangebotId = 1;
        Kreditangeboten mockKreditangebot = new Kreditangeboten(
                "KreditangebotTestName",
                "KreditangebotTestBeschreibung",
                3000,
                BigDecimal.valueOf(30.00),
                LocalDate.now(),
                null,
                new KreditangebotenStatus("StatusNameTest", "StatusBeschreibungTest"));
        Optional<Kreditangeboten> mockResult = Optional.of(mockKreditangebot);
        when(kreditangeboteRepository.findById(kreditangebotId)).thenReturn(mockResult);
        // when
        Kreditangeboten result = kreditangeboteServiceImpl.findById(kreditangebotId);
        // then Überprüfen, ob das erwartete Verhalten eingetreten ist
        assertEquals(mockKreditangebot, result);
        verify(kreditangeboteRepository,times(1)).findById(1);
    }


    @Test
    public void testFindById_KreditangebotNichtGefunden() {
        // given Mock-Daten erstellen
        int kreditangebotId = 2;
        Optional<Kreditangeboten> mockResult = Optional.empty();
        // when Mock-Verhalten definieren
        when(kreditangeboteRepository.findById(kreditangebotId)).thenReturn(mockResult);
        // then Methode aufrufen und auf eine RuntimeException prüfen
        assertThrows(RuntimeException.class, () -> kreditangeboteServiceImpl.findById(kreditangebotId));
        verify(kreditangeboteRepository).findById(2);
    }
}
