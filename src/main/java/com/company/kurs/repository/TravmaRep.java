package com.company.kurs.repository;

import com.company.kurs.domain.Travma;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TravmaRep extends CrudRepository<Travma, Integer> {
    Long countByIdTravma(Integer idTravma);

    @Transactional
    @Query(value = "SELECT * FROM travma WHERE travma.date_travma BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Travma> findTravmaByDateTravma(@Param("date1") java.sql.Date date1, @Param("date2") java.sql.Date date2);

    @Transactional
    @Query(value = "SELECT count(travma.id_travma) FROM travma " +
            "WHERE EXTRACT(YEAR FROM travma.date_travma) = EXTRACT(YEAR FROM now())", nativeQuery = true)
    double findCount();
}
