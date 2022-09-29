package com.company.kurs.service;

import com.company.kurs.domain.Coach;
import com.company.kurs.domain.Comanda;
import com.company.kurs.query.ComandaAmount;
import com.company.kurs.repository.ComandaRep;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ComandaServiceTest {

    @InjectMocks
    private ComandaService comandaService;
    @MockBean(name = "comandaRep")
    ComandaRep comandaRep = Mockito.mock(ComandaRep.class);

    @Test
    void listAll() {
        Comanda comanda1 = Comanda.builder().idComanda(1).name_c("Dinamo").country1("UK").build();
        Comanda comanda2 = Comanda.builder().idComanda(2).name_c("Zoria").country1("UA").build();
        Comanda comanda3 = Comanda.builder().idComanda(3).name_c("MU").country1("DN").build();
        List<Comanda> list = new ArrayList<>(Arrays.asList(comanda1, comanda2, comanda3));
        Mockito.doAnswer(invocation -> list).when(comandaRep).findAll();
        List<Comanda> listResult = (List<Comanda>) comandaRep.findAll();
        assertThat(listResult).isSameAs(list);
        verify(comandaRep).findAll();
    }

    @Test
    void save() {
        Comanda comanda = Comanda.builder().idComanda(1).name_c("Manchester").logo("logo1").country1("UA").build();
        when(comandaRep.save(ArgumentMatchers.any(Comanda.class))).thenReturn(comanda);
        Comanda createdComanda = comandaRep.save(comanda);
        assertThat(createdComanda.getIdComanda()).isSameAs(comanda.getIdComanda());
        verify(comandaRep).save(comanda);
    }

    @Test
    void get() {
        Comanda comanda = Comanda.builder().idComanda(1).name_c("Dinamo").country1("UA").build();
        when(comandaRep.findById(comanda.getIdComanda())).thenReturn(Optional.of(comanda));
        Optional<Comanda> createdComanda = comandaRep.findById(comanda.getIdComanda());
        assertNotNull(createdComanda);
        verify(comandaRep).findById(comanda.getIdComanda());
    }

    @Test
    void delete() {
        Comanda comanda = Comanda.builder().idComanda(1).name_c("Dinamo").country1("UA").build();
        given(comandaRep.countByIdComanda(anyInt())).willReturn(null);
        assertThrows(EntityNotFoundException.class, () -> comandaService.delete(comanda.getIdComanda()));
    }

    @Test
    void makeCollection() {
        Comanda comanda1 = Comanda.builder().idComanda(1).name_c("Dinamo").country1("UK").build();
        Comanda comanda2 = Comanda.builder().idComanda(2).name_c("Zoria").country1("UA").build();
        Comanda comanda3 = Comanda.builder().idComanda(3).name_c("MU").country1("DN").build();
        List<Comanda> list = new ArrayList<>(Arrays.asList(comanda1, comanda2, comanda3));
        List<Comanda> list1 = ComandaService.makeCollection(list);
        assertThrows(AssertionError.class, () -> assertThat(list1).isSameAs(list));
    }

    @Test
    void getCoach() {
        Comanda comanda1 = Comanda.builder().idComanda(1).name_c("Dinamo").country1("UK").build();
        Comanda comanda2 = Comanda.builder().idComanda(2).name_c("Zoria").country1("UA").build();
        Comanda comanda3 = Comanda.builder().idComanda(3).name_c("MU").country1("DN").build();
        List<Comanda> list = new ArrayList<>(Arrays.asList(comanda1, comanda2, comanda3));
        List<Coach> cl = new ArrayList<>();
        List<String> str = new ArrayList<>();
        AtomicBoolean flag = new AtomicBoolean(false);
        Mockito.doAnswer(invocation -> {
            list.forEach(comanda -> {
                for (String value : str) {
                    if (value.equals(comanda.getCountry1())) {
                        flag.set(true);
                        break;
                    }
                }
                if (!flag.get()) {
                    str.add(comanda.getCountry1());
                    cl.add(new Coach(comanda.getCountry1(), (long) 1));
                } else {
                    for (Coach coach : cl) {
                        if (coach.getCom().equals(comanda.getCountry1())) {
                            coach.setAm(coach.getAm() + 1);
                            break;
                        }
                    }
                }
                flag.set(false);
            });
            return cl;
        }).when(comandaRep).findCoach();
        List<Coach> sl = comandaRep.findCoach();
        assertThat(sl).isSameAs(cl);
        verify(comandaRep).findCoach();
    }

    @Test
    void getComandaAmount() {
        Comanda comanda1 = Comanda.builder().idComanda(1).name_c("Dinamo").country1("UK").build();
        Comanda comanda2 = Comanda.builder().idComanda(2).name_c("Zoria").country1("UA").build();
        Comanda comanda3 = Comanda.builder().idComanda(3).name_c("MU").country1("DN").build();
        List<Comanda> list = new ArrayList<>(Arrays.asList(comanda1, comanda2, comanda3));
        List<ComandaAmount> cl = new ArrayList<>();
        List<String> str = new ArrayList<>();
        AtomicBoolean flag = new AtomicBoolean(false);
        Mockito.doAnswer(invocation -> {
            list.forEach(comanda -> {
                for (String value : str) {
                    if (value.equals(comanda.getName_c())) {
                        flag.set(true);
                        break;
                    }
                }
                if (!flag.get()) {
                    str.add(comanda.getName_c());
                    cl.add(new ComandaAmount(comanda.getName_c(), (long) 1));
                } else {
                    for (ComandaAmount comandaAmount : cl) {
                        if (comandaAmount.getName_com().equals(comanda.getName_c())) {
                            comandaAmount.setAmount(comandaAmount.getAmount() + 1);
                            break;
                        }
                    }
                }
                flag.set(false);
            });
            return cl;
        }).when(comandaRep).findPlayer();
        List<ComandaAmount> sl = comandaRep.findPlayer();
        assertThat(sl).isSameAs(cl);
        verify(comandaRep).findPlayer();
    }
}