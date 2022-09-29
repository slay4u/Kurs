package com.company.kurs.service;

import com.company.kurs.domain.Trener;
import com.company.kurs.repository.TrenerRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class TrenerService {
    @Autowired private TrenerRep repo;

    public List<Trener> listAll(){
        return (List<Trener>) repo.findAll();
    }

    public void save(Trener trener) {
        repo.save(trener);
    }

    public Trener get(Integer idTrener) throws EntityNotFoundException {
        Optional<Trener> result = repo.findById(idTrener);
        try {
            if(result.isPresent()){
                return result.get();
            }
            throw new EntityNotFoundException("Can`t find any trener with id "+idTrener);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void delete(Integer idTrener) throws EntityNotFoundException {
        Long count = repo.countByIdTrener(idTrener);
        if(count == null || count == 0){
            throw new EntityNotFoundException("Can`t find any trener with id "+idTrener);
        }
        repo.deleteById(idTrener);
    }

    public List<Trener> getTrenerByComanda(String name_c){
        return repo.findTrenerByComanda(name_c);
    }

    public List<Trener> getTrenerByLetter(String pibtrener){
        return repo.findTrenerByLetter(pibtrener);
    }

}
