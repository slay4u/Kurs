package com.company.kurs.repository;

import com.company.kurs.domain.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface GameRep extends CrudRepository<Game, Integer> {
    Long countByIdGame(Integer idGame);

    @Transactional
    @Query(value = "SELECT * FROM game WHERE game.date_game BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Game> findGameByDate(@Param("date1") java.sql.Date date1, @Param("date2") java.sql.Date date2);

    @Transactional
    @Query(value = "SELECT count(game.id_game) FROM game " +
            " WHERE EXTRACT(MONTH FROM game.date_game) = EXTRACT(MONTH FROM now())-1", nativeQuery = true)
    double findCount();

    @Transactional
    @Query(value = "SELECT * from game " +
            "where game.date_game >= all (select game.date_game from game)", nativeQuery = true)
    List<Game> findLast();
}
