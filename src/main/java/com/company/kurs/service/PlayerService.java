package com.company.kurs.service;

import com.company.kurs.domain.Player;
import com.company.kurs.repository.PlayerRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRep repo;

    public List<Player> listAll(){
        return (List<Player>) repo.findAll();
    }

    public void save(Player player) {
        repo.save(player);
    }

    public Player get(Integer idPlayer) throws EntityNotFoundException {
        Optional<Player> result = repo.findById(idPlayer);
        try {
            if(result.isPresent()){
                return result.get();
            }
            throw new EntityNotFoundException("Can`t find any player with id "+idPlayer);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void delete(Integer idPlayer) throws EntityNotFoundException {
        Long count = repo.countByIdPlayer(idPlayer);
        if(count == null || count == 0){
            throw new EntityNotFoundException("Can`t find any player with id "+idPlayer);
        }
        repo.deleteById(idPlayer);
    }

    public static <E> List<E> makeCollection(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }

    public List<Player> getPlayerByComanda(String name_c){
        return repo.findPlayerByComanda(name_c);
    }

    public List<Player> getPlayerByLetter(String pibPlayer){
        return repo.findPlayerByLetter(pibPlayer);
    }
}
