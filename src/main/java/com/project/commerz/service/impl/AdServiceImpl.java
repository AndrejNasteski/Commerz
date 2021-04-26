package com.project.commerz.service.impl;

import com.project.commerz.model.Ad;
import com.project.commerz.model.Category;
import com.project.commerz.model.Location;
import com.project.commerz.repository.jpa.AdRepository;
import com.project.commerz.repository.jpa.CategoryRepository;
import com.project.commerz.repository.jpa.LocationRepository;
import com.project.commerz.service.AdService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;

    public AdServiceImpl(AdRepository adRepository, CategoryRepository categoryRepository, LocationRepository locationRepository) {
        this.adRepository = adRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Ad> findAllAds() {
        return adRepository.findAll();
    }

    @Override
    public List<Ad> searchAds(String fullTextSearch, Category category, Location location) {
        List<Ad> adList = adRepository.findAll();
        if (fullTextSearch != null) adList = adRepository.findAllByAdDescriptionLike(fullTextSearch);

        if (category != null && location == null) {    // catId = 1 is Any category
            if (category.getCategoryId() == 1) {
                return new ArrayList<>(adList);
            }
            return adList.stream()
                    .filter(ad -> ad.getCategory().equals(category))
                    .collect(Collectors.toList());
        } else if (category == null && location != null) {
            if (location.getLocationId() == 1) {
                return new ArrayList<>(adList);
            }
            return adList.stream()
                    .filter(ad -> ad.getLocation().equals(location))
                    .collect(Collectors.toList());
        }
        else if (category != null) {
            if (category.getCategoryId() == 1 && location.getLocationId() != 1) {
                return adList.stream()
                        .filter(ad -> ad.getLocation().equals(location))
                        .collect(Collectors.toList());
            } else if (category.getCategoryId() != 1 && location.getLocationId() == 1) {
                return adList.stream()
                        .filter(ad -> ad.getCategory().equals(category))
                        .collect(Collectors.toList());
            } else if (category.getCategoryId() == 1 && location.getLocationId() == 1) {
                return new ArrayList<>(adList);
            } else {
                return adList.stream()
                        .filter(ad -> ad.getCategory().equals(category))
                        .filter(ad -> ad.getLocation().equals(location))
                        .collect(Collectors.toList());
            }
        } else {
            return new ArrayList<>(adList);
        }
    }

    @Override
    public Optional<Ad> findAdById(Long id) {
        return adRepository.findAllByAdId(id).stream().findFirst(); // or else throw ad not found exception
    }

    @Override
    public void deleteById(Long id) {
        adRepository.deleteById(id);
    }

    @Override
    public long numberOfAds() {
        return this.adRepository.findAll().size();
    }

    @Override
    public void save(String name, String description, Long categoryId, Long locationId, Long price, String phone, String email) {
        Category c = this.categoryRepository.findAllByCategoryId(categoryId);
        Location l = this.locationRepository.findAllByLocationId(locationId);
        Ad a = new Ad(name, description, price, c, l, phone, email);
        this.adRepository.save(a);
    }


}
