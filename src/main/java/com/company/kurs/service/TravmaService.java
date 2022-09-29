package com.company.kurs.service;

import com.company.kurs.domain.Travma;
import com.company.kurs.repository.TravmaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class TravmaService {
    @Autowired
    private TravmaRep repo;

    public List<Travma> listAll(){
        return (List<Travma>) repo.findAll();
    }

    public void save(Travma travma) {
        repo.save(travma);
    }

    public Travma get(Integer idTravma) throws EntityNotFoundException {
        Optional<Travma> result = repo.findById(idTravma);
        try {
            if(result.isPresent()){
                return result.get();
            }
            throw new EntityNotFoundException("Can`t find any travma with id "+idTravma);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void delete(Integer idTravma) throws EntityNotFoundException {
        Long count = repo.countByIdTravma(idTravma);
        if(count == null || count == 0){
            throw new EntityNotFoundException("Can`t find any travma with id "+idTravma);
        }
        repo.deleteById(idTravma);
    }

    public List<Travma> getTravmaByDateTravma(java.sql.Date date1, java.sql.Date date2){
        return repo.findTravmaByDateTravma(date1, date2);
    }

    public double getCount(){
        return repo.findCount();
    }
}
