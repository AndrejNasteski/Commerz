package com.project.commerz.repository.jpa;

import com.project.commerz.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findAllByLocationId(Long id);
}
