package com.company.kurs.service;

import com.company.kurs.domain.Coach;
import com.company.kurs.domain.Comanda;
import com.company.kurs.query.ComandaAmount;
import com.company.kurs.repository.ComandaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComandaService {
    @Autowired private ComandaRep repo;

    public List<Comanda> listAll(){
        return (List<Comanda>) repo.findAll();
    }

    public void save(Comanda comanda) {
        repo.save(comanda);
    }

    public Comanda get(Integer idComanda) throws EntityNotFoundException {
        Optional<Comanda> result = repo.findById(idComanda);
        try {
            if(result.isPresent()){
                return result.get();
            }
            throw new EntityNotFoundException("Can`t find any comanda with id "+idComanda);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void delete(Integer idComanda) throws EntityNotFoundException{
        Long count = repo.countByIdComanda(idComanda);
        if(count == null || count == 0){
            throw new EntityNotFoundException("Can`t find any comanda with id "+idComanda);
        }
        repo.deleteById(idComanda);
    }

    public static <E> List<E> makeCollection(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }

    public List<Coach> getCoach(){
        return repo.findCoach();
    }

    public List<ComandaAmount> getComandaAmount(){
        return repo.findPlayer();
    }
}
