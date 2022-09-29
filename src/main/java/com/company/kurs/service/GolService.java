package com.company.kurs.service;

import com.company.kurs.domain.Gol;
import com.company.kurs.repository.GolRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class GolService {
    @Autowired
    private GolRep repo;

    public List<Gol> listAll(){
        return (List<Gol>) repo.findAll();
    }

    public void save(Gol gol) {
        repo.save(gol);
    }

    public Gol get(Integer idGol) throws EntityNotFoundException {
        Optional<Gol> result = repo.findById(idGol);
        try {
            if(result.isPresent()){
                return result.get();
            }
            throw new EntityNotFoundException("Can`t find any gol with id "+idGol);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void delete(Integer idGol) throws EntityNotFoundException {
        Long count = repo.countByIdGol(idGol);
        if(count == null || count == 0){
            throw new EntityNotFoundException("Can`t find any gol with id "+idGol);
        }
        repo.deleteById(idGol);
    }
}
