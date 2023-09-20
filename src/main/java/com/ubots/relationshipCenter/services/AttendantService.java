package com.ubots.relationshipCenter.services;

import com.ubots.relationshipCenter.entities.Attendant;
import com.ubots.relationshipCenter.repositories.AttendantRepository;
import com.ubots.relationshipCenter.services.exceptions.DatabaseException;
import com.ubots.relationshipCenter.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendantService {
    
    @Autowired
    private AttendantRepository repository;

    public List<Attendant> findAll(){
        return repository.findAll();
    }

    public Attendant findById(Long id){
        Optional<Attendant> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Attendant insert( Attendant obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Attendant update(Long id, Attendant obj){
        try {
            Attendant entity = repository.getReferenceById(id);
            updateData(obj, entity);
            return repository.save(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private static void updateData(Attendant obj, Attendant entity) {
        entity.setName(obj.getName());
        entity.setCalledType(obj.getCalledType());
    }
}
