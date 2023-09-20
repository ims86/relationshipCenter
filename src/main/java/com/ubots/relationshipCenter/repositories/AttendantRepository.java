package com.ubots.relationshipCenter.repositories;

import com.ubots.relationshipCenter.entities.Attendant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendantRepository extends JpaRepository<Attendant, Long> {
}
