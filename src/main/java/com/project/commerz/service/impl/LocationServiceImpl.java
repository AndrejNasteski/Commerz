package com.project.commerz.service.impl;

import com.project.commerz.model.Location;
import com.project.commerz.repository.jpa.LocationRepository;
import com.project.commerz.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location findById(Long id) {
        return locationRepository.findAllByLocationId(id);
    }
}
