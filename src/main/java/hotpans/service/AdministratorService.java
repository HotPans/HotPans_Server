package hotpans.service;

import hotpans.domain.Administrator;
import hotpans.repository.AdministratorRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdministratorService {
    @Autowired
    AdministratorRepository administratorRepository;

    public List<Administrator> findAll(){
        return administratorRepository.findAll();
    }

    public Administrator findOne(Integer id){
        return administratorRepository.findOne(id);
    }

    public Administrator create(Administrator administrator){
        return administratorRepository.save(administrator);
    }

    public Administrator update(Administrator administrator){
        return administratorRepository.save(administrator);
    }

    public void delete(Integer id){
        administratorRepository.delete(id);
    }
}
