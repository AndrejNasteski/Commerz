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
        if (id == null) return null;
        return locationRepository.findAllByLocationId(id);
    }

    @Override
    public Location findById(String id) {
        if (id == null || id.isEmpty() || id.equals("null")) return null;
        return locationRepository.findAllByLocationId(Long.valueOf(id));
    }
}
