package com.company.kurs.service;

import com.company.kurs.domain.Gol;
import com.company.kurs.repository.GolRep;
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
class GolServiceTest {

    @InjectMocks
    private GolService golService;
    @MockBean(name = "golRep")
    GolRep golRep = Mockito.mock(GolRep.class);

    @Test
    void listAll() {
        Gol gol1 = Gol.builder().idGol(1).timeGol("12:47").time1("1").build();
        Gol gol2 = Gol.builder().idGol(2).timeGol("23:51").time1("1").build();
        Gol gol3 = Gol.builder().idGol(3).timeGol("63:57").time1("2").build();
        List<Gol> list = new ArrayList<>(Arrays.asList(gol1, gol2, gol3));
        Mockito.doAnswer(invocation -> list).when(golRep).findAll();
        List<Gol> listResult = (List<Gol>) golRep.findAll();
        assertThat(listResult).isSameAs(list);
        verify(golRep).findAll();
    }

    @Test
    void save() {
        Gol gol = Gol.builder().idGol(1).timeGol("12:47").time1("1").build();
        when(golRep.save(ArgumentMatchers.any(Gol.class))).thenReturn(gol);
        Gol createdGol = golRep.save(gol);
        assertThat(createdGol.getIdGol()).isSameAs(gol.getIdGol());
        verify(golRep).save(gol);
    }

    @Test
    void get() {
        Gol gol = Gol.builder().idGol(1).timeGol("12:47").time1("1").build();
        when(golRep.findById(gol.getIdGol())).thenReturn(Optional.of(gol));
        Optional<Gol> createdGol = golRep.findById(gol.getIdGol());
        assertNotNull(createdGol);
        verify(golRep).findById(gol.getIdGol());
    }

    @Test
    void delete() {
        Gol gol = Gol.builder().idGol(1).timeGol("12:47").time1("1").build();
        given(golRep.countByIdGol(anyInt())).willReturn(null);
        assertThrows(EntityNotFoundException.class, () -> golService.delete(gol.getIdGol()));
    }
}