package com.company.kurs.repository;

import com.company.kurs.domain.Coach;
import com.company.kurs.domain.Comanda;
import com.company.kurs.query.ComandaAmount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ComandaRep extends CrudRepository<Comanda, Integer> {
    Long countByIdComanda(Integer idComanda);

    @Transactional
    @Query("SELECT new com.company.kurs.domain.Coach(t.country1, COUNT(t.country1)) " +
            "FROM Comanda AS t GROUP BY t.country1")
    List<Coach> findCoach();

    @Transactional
    @Query("SELECT new com.company.kurs.query.ComandaAmount(r.name_c, COUNT(r.name_c)) " +
            "FROM Comanda AS r GROUP BY r.name_c")
    List<ComandaAmount> findPlayer();
}
