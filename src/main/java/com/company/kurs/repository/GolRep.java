package com.company.kurs.repository;

import com.company.kurs.domain.Gol;
import org.springframework.data.repository.CrudRepository;

public interface GolRep extends CrudRepository<Gol, Integer> {
    Long countByIdGol(Integer idGame);
}
