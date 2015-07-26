package hotpans.service;

import hotpans.domain.Bakery;
import hotpans.repository.BakeryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BakeryService {
    @Autowired
    BakeryRepository bakeryRepository;

    public List<Bakery> findAll(){
        return bakeryRepository.findAll();
    }

    public Bakery findOne(Integer id){
        return bakeryRepository.findOne(id);
    }

    public Bakery create(Bakery bakery){
        return bakeryRepository.save(bakery);
    }

    public Bakery update(Bakery bakery){
        return bakeryRepository.save(bakery);
    }

    public void delete(Integer id){
        bakeryRepository.delete(id);
    }
}
