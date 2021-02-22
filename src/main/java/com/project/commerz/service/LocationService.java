package com.project.commerz.service;

import com.project.commerz.model.Location;

import java.util.List;

public interface LocationService {
    List<Location> findAllLocations();
    Location findById(Long id);
}
