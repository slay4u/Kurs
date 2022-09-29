package com.company.kurs.service;

import com.company.kurs.domain.Game;
import com.company.kurs.repository.GameRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRep repo;

    public List<Game> listAll(){
        return (List<Game>) repo.findAll();
    }

    public void save(Game game) {
        repo.save(game);
    }

    public Game get(Integer idGame) throws EntityNotFoundException {
        Optional<Game> result = repo.findById(idGame);
        try {
            if(result.isPresent()){
                return result.get();
            }
            throw new EntityNotFoundException("Can`t find any game with id "+idGame);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void delete(Integer idGame) throws EntityNotFoundException{
        Long count = repo.countByIdGame(idGame);
        if(count == null || count == 0){
            throw new EntityNotFoundException("Can`t find any game with id "+idGame);
        }
        repo.deleteById(idGame);
    }

    public static <E> List<E> makeCollection(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }

    public List<Game> getGameByDate(java.sql.Date date1, java.sql.Date date2){
        return repo.findGameByDate(date1, date2);
    }

    public double getCount(){
        return repo.findCount();
    }

    public List<Game> getLast(){
        return repo.findLast();
    }
}
