package com.ubots.relationshipCenter.repositories;

import com.ubots.relationshipCenter.entities.Called;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalledRepository extends JpaRepository<Called, Long> {

    @Query(nativeQuery = true, value = "Select count(id) from tb_called " +
            "where attendant_id = (:id) and status = 2")
    Integer countByAttendantIdInProgress(Long id);


    @Query(nativeQuery = true, value = "Select * from tb_called " +
            "where attendant_id is null and type = (:id) and status = 1")
    List<Called> listCalledByAttendantIsNull(Integer id);

}
