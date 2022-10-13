package com.company.kurs.service;

import com.company.kurs.domain.Comanda;
import com.company.kurs.domain.Trener;
import com.company.kurs.repository.TrenerRep;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class TrenerServiceTest {

    @InjectMocks
    private TrenerService trenerService;
    @MockBean(name = "trenerRep")
    TrenerRep trenerRep = Mockito.mock(TrenerRep.class);

    @Test
    void listAll() {
        Trener trener1 = Trener.builder().idTrener(1).pibtrener("Victor Krylosov").presence("true").result("2-0").build();
        Trener trener2 = Trener.builder().idTrener(2).pibtrener("Valentin Shulga").presence("false").result("4-1").build();
        Trener trener3 = Trener.builder().idTrener(3).pibtrener("Dmytro Vovk").presence("true").result("0-9").build();
        List<Trener> list = new ArrayList<>(Arrays.asList(trener1, trener2, trener3));
        Mockito.doAnswer(invocation -> list).when(trenerRep).findAll();
        List<Trener> listResult = (List<Trener>) trenerRep.findAll();
        assertThat(listResult).isSameAs(list);
        verify(trenerRep).findAll();
    }

    @Test
    void save() {
        Trener trener = Trener.builder().idTrener(1).pibtrener("Victor Krylosov").presence("true").result("2-0").build();
        when(trenerRep.save(ArgumentMatchers.any(Trener.class))).thenReturn(trener);
        Trener createdTrener = trenerRep.save(trener);
        assertThat(createdTrener.getIdTrener()).isSameAs(trener.getIdTrener());
        verify(trenerRep).save(trener);
    }

    @Test
    void get() {
        Trener trener = Trener.builder().idTrener(1).pibtrener("Victor Krylosov").presence("true").result("2-0").build();
        when(trenerRep.findById(trener.getIdTrener())).thenReturn(Optional.of(trener));
        Optional<Trener> createdTrener = trenerRep.findById(trener.getIdTrener());
        assertNotNull(createdTrener);
        verify(trenerRep).findById(trener.getIdTrener());
    }

    @Test
    void delete() {
        Trener trener = Trener.builder().idTrener(1).pibtrener("Victor Krylosov").presence("true").result("2-0").build();
        given(trenerRep.countByIdTrener(anyInt())).willReturn(null);
        assertThrows(EntityNotFoundException.class, () -> trenerService.delete(trener.getIdTrener()));
    }

    @Test
    void getTrenerByComanda() {
        Comanda comanda1 = Comanda.builder().idComanda(1).name_c("Zoria").build();
        Comanda comanda2 = Comanda.builder().idComanda(2).name_c("Dinamo").build();
        Trener trener1 = Trener.builder().idTrener(1).trenerComanda(comanda1).pibtrener("Victor Krylosov").presence("true").result("2-0").build();
        Trener trener2 = Trener.builder().idTrener(2).trenerComanda(comanda1).pibtrener("Valentin Shulga").presence("false").result("4-1").build();
        Trener trener3 = Trener.builder().idTrener(3).trenerComanda(comanda2).pibtrener("Dmytro Vovk").presence("true").result("0-9").build();
        List<Trener> list = new ArrayList<>(Arrays.asList(trener1, trener2, trener3));
        List<Trener> list1 = new ArrayList<>();
        Mockito.doAnswer(invocation -> {
            list.forEach(trener -> {
                if(trener.getTrenerComanda().getName_c().equals("Dinamo")) {
                    list1.add(trener);
                }
            });
            return list1;
        }).when(trenerRep).findTrenerByComanda("Dinamo");
        List<Trener> expected = trenerRep.findTrenerByComanda("Dinamo");
        assertThat(expected).isSameAs(list1);
        verify(trenerRep).findTrenerByComanda("Dinamo");
    }

    @Test
    void getTrenerByLetter() {
        Trener trener1 = Trener.builder().idTrener(1).pibtrener("Victor Krylosov").presence("true").result("2-0").build();
        Trener trener2 = Trener.builder().idTrener(2).pibtrener("Valentin Shulga").presence("false").result("4-1").build();
        Trener trener3 = Trener.builder().idTrener(3).pibtrener("Dmytro Vovk").presence("true").result("0-9").build();
        List<Trener> list = new ArrayList<>(Arrays.asList(trener1, trener2, trener3));
        List<Trener> list1 = new ArrayList<>();
        Mockito.doAnswer(invocation -> {
            list.forEach(trener -> {
                if(trener.getPibtrener().startsWith("D")) {
                    list1.add(trener);
                }
            });
            return list1;
        }).when(trenerRep).findTrenerByLetter("D");
        List<Trener> expected = trenerRep.findTrenerByLetter("D");
        assertThat(expected).isSameAs(list1);
        verify(trenerRep).findTrenerByLetter("D");
    }
}