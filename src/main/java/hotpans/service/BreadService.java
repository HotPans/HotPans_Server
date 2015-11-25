package hotpans.service;

import hotpans.domain.Bread;
import hotpans.repository.BreadRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BreadService {
    @Autowired
    BreadRepository breadRepository;

    public List<Bread> findAll(){
        return breadRepository.findAll();
    }

    public Bread findOne(Integer id){
        return breadRepository.findOne(id);
    }

    public Bread create(Bread bread){
        return breadRepository.save(bread);
    }

    public Bread update(Bread bread){
        return breadRepository.save(bread);
    }

    public void delete(Integer id){
        breadRepository.delete(id);
    }
}
