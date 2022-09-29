package com.company.kurs.service;

import com.company.kurs.domain.Travma;
import com.company.kurs.repository.TravmaRep;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class TravmaServiceTest {

    @InjectMocks
    private TravmaService travmaService;
    @MockBean(name = "travmaRep")
    TravmaRep travmaRep = Mockito.mock(TravmaRep.class);

    @Test
    void listAll() {
        Travma travma1 = Travma.builder().idTravma(1).description("befbyusess").dateTravma(Date.valueOf("2022-01-01")).build();
        Travma travma2 = Travma.builder().idTravma(2).description("6g5v5756m").dateTravma(Date.valueOf("2021-02-09")).build();
        Travma travma3 = Travma.builder().idTravma(3).description("6g47gw4637hg7h6wgw").dateTravma(Date.valueOf("2023-11-21")).build();
        List<Travma> list = new ArrayList<>(Arrays.asList(travma1, travma2, travma3));
        Mockito.doAnswer(invocation -> list).when(travmaRep).findAll();
        List<Travma> listResult = (List<Travma>) travmaRep.findAll();
        assertThat(listResult).isSameAs(list);
        verify(travmaRep).findAll();
    }

    @Test
    void save() {
        Travma travma = Travma.builder().idTravma(1).description("befbyusess").dateTravma(Date.valueOf("2022-01-01")).build();
        when(travmaRep.save(ArgumentMatchers.any(Travma.class))).thenReturn(travma);
        Travma createdTravma = travmaRep.save(travma);
        assertThat(createdTravma.getIdTravma()).isSameAs(travma.getIdTravma());
        verify(travmaRep).save(travma);
    }

    @Test
    void get() {
        Travma travma = Travma.builder().idTravma(1).description("befbyusess").dateTravma(Date.valueOf("2022-01-01")).build();
        when(travmaRep.findById(travma.getIdTravma())).thenReturn(Optional.of(travma));
        Optional<Travma> createdTravma = travmaRep.findById(travma.getIdTravma());
        assertNotNull(createdTravma);
        verify(travmaRep).findById(travma.getIdTravma());
    }

    @Test
    void delete() {
        Travma travma = Travma.builder().idTravma(1).description("befbyusess").dateTravma(Date.valueOf("2022-01-01")).build();
        given(travmaRep.countByIdTravma(anyInt())).willReturn(null);
        assertThrows(EntityNotFoundException.class, () -> travmaService.delete(travma.getIdTravma()));
    }

    @Test
    void getTravmaByDateTravma() {
        Travma travma1 = Travma.builder().idTravma(1).description("befbyusess").dateTravma(Date.valueOf("2022-01-01")).build();
        Travma travma2 = Travma.builder().idTravma(2).description("6g5v5756m").dateTravma(Date.valueOf("2021-02-09")).build();
        Travma travma3 = Travma.builder().idTravma(3).description("6g47gw4637hg7h6wgw").dateTravma(Date.valueOf("2023-11-21")).build();
        List<Travma> list = new ArrayList<>(Arrays.asList(travma1, travma2, travma3));
        Date date = Date.valueOf("2023-10-10");
        Date date1 = Date.valueOf("2021-01-01");
        List<Travma> list1 = new ArrayList<>();
        Mockito.doAnswer(invocation -> {
            list.forEach(travma -> {
                if((travma.getDateTravma().toLocalDate().compareTo(date.toLocalDate()) < 0) &&
                        (travma.getDateTravma().toLocalDate().compareTo(date1.toLocalDate()) > 0)) {
                    list1.add(travma);
                }
            });
            return list1;
        }).when(travmaRep).findTravmaByDateTravma(date1, date);
        List<Travma> expect = travmaRep.findTravmaByDateTravma(date1, date);
        assertThat(expect).isSameAs(list1);
        verify(travmaRep).findTravmaByDateTravma(date1, date);
    }

    @Test
    void getCount() {
        Travma travma1 = Travma.builder().idTravma(1).description("befbyusess").dateTravma(Date.valueOf("2022-01-01")).build();
        Travma travma2 = Travma.builder().idTravma(2).description("6g5v5756m").dateTravma(Date.valueOf("2021-09-09")).build();
        Travma travma3 = Travma.builder().idTravma(3).description("6g47gw4637hg7h6wgw").dateTravma(Date.valueOf("2023-11-21")).build();
        List<Travma> list = new ArrayList<>(Arrays.asList(travma1, travma2, travma3));
        Date date = Date.valueOf("2022-02-02");
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        AtomicReference<Double> amount = new AtomicReference<>((double) 0);
        Mockito.doAnswer(invocation -> {
            list.forEach(travma -> {
                Calendar clTravma = Calendar.getInstance();
                clTravma.setTime(travma.getDateTravma());
                if(clTravma.get(Calendar.YEAR) == cl.get(Calendar.YEAR)) {
                    amount.getAndSet(amount.get() + 1);
                }
            });
            return amount.get();
        }).when(travmaRep).findCount();
        Double result = travmaRep.findCount();
        assertThrows(AssertionError.class, () -> assertThat(result).isSameAs(amount.get()));
    }
}