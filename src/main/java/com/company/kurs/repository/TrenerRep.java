package com.company.kurs.repository;

import com.company.kurs.domain.Trener;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TrenerRep extends CrudRepository<Trener, Integer> {
    Long countByIdTrener(Integer idTrener);

    @Transactional
    @Query(value = "SELECT * FROM trener WHERE trener.id_comanda IN" +
            "(SELECT comanda.id_comanda FROM comanda WHERE comanda.name_c = ?1) ORDER BY trener.pibtrener", nativeQuery = true)
    List<Trener> findTrenerByComanda(@Param("name_c") String name_c);

    @Transactional
    @Query(value = "SELECT * FROM trener WHERE trener.pibtrener LIKE ?1%", nativeQuery = true)
    List<Trener> findTrenerByLetter(@Param("pibtrener") String pibtrener);

}
