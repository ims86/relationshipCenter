package com.ubots.relationshipCenter.services;

import com.ubots.relationshipCenter.entities.Attendant;
import com.ubots.relationshipCenter.entities.Called;
import com.ubots.relationshipCenter.entities.enums.CalledStatus;
import com.ubots.relationshipCenter.repositories.AttendantRepository;
import com.ubots.relationshipCenter.repositories.CalledRepository;
import com.ubots.relationshipCenter.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CalledService {
    
    @Autowired
    private CalledRepository repository;

    @Autowired
    private AttendantRepository attendantRepository;

    private String msg;

    public List<Called> findAll(){
        return repository.findAll();
    }

    public Called findById(Long id){
        Optional<Called> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Called insert( Called obj){
        obj.setStatus(CalledStatus.CREATED);
        obj.setMoment(new Date().toInstant());
        isValidAttendant(obj);
        return repository.save(obj);
    }

    public Called update(Long id, Called obj) {
        try {
            Optional<Called> entity = repository.findById(id);
            updateData(obj, entity);
            repository.save(entity.get());
            return isValidAttendant(obj);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private static void updateData(Called obj, Optional<Called> entity) {
            entity.get().setStatus(obj.getStatus());
            entity.get().setMoment(new Date().toInstant());
    }

    private Called isValidAttendant(Called obj) {
        List<Attendant> attendants = attendantRepository.findAll();
        if(obj.getStatus().equals(CalledStatus.FINISHED)){

            List<Called> calleds = repository.listCalledByAttendantIsNull(obj.getType().getCode());
            for (Called called: calleds) {
                for (Attendant attendant: attendants) {
                    Integer countAttendant = getAttendantInProgress(attendant.getId());
                    if(attendant.getCalledType().equals(obj.getType()) && countAttendant < 3) {
                        obj.setId(called.getId());
                        obj.setMoment(called.getMoment());
                        obj.setAttendant(attendant);
                        obj.setType(called.getType());
                        obj.setTitle(called.getTitle());
                        obj.setDescription(called.getDescription());
                        obj.setStatus(CalledStatus.IN_PROGRESS);
                        return repository.save(obj);
                    }
                }
            }

        } else {

            for (Attendant attendant: attendants) {
                Integer countAttendant = getAttendantInProgress(attendant.getId());
                if(attendant.getCalledType().equals(obj.getType()) && countAttendant < 3) {
                    obj.setStatus(CalledStatus.IN_PROGRESS);
                    obj.setAttendant(attendant);
                    return obj;
                }
            }
        }
        return obj;
    }

    private Integer getAttendantInProgress (Long attendantId) {
        return repository.countByAttendantIdInProgress(attendantId);
    }
}
