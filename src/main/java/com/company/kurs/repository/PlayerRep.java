package com.company.kurs.repository;

import com.company.kurs.domain.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PlayerRep extends CrudRepository<Player, Integer> {
    Long countByIdPlayer(Integer idPlayer);

    @Transactional
    @Query(value = "SELECT * FROM player WHERE player.id_comanda IN" +
            "(SELECT comanda.id_comanda FROM comanda WHERE comanda.name_c = ?1) ORDER BY player.pib_player", nativeQuery = true)
    List<Player> findPlayerByComanda(@Param("name_c") String name_c);

    @Transactional
    @Query(value = "SELECT * FROM player WHERE player.pib_player LIKE ?1%", nativeQuery = true)
    List<Player> findPlayerByLetter(@Param("pibPlayer") String pibPlayer);
}
