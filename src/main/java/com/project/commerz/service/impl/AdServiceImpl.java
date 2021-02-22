package com.project.commerz.service.impl;

import com.project.commerz.model.Ad;
import com.project.commerz.model.Category;
import com.project.commerz.model.Location;
import com.project.commerz.repository.jpa.AdRepository;
import com.project.commerz.service.AdService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;

    public AdServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public List<Ad> findAllAds() {
        return adRepository.findAll();
    }

    @Override
    public List<Ad> searchAds(String fullTextSearch, Category category, Location location, Long price) {
        Long maxPrice = Long.MAX_VALUE;
        if(price != null){
            maxPrice = price;
        }
        List<Ad> adList = adRepository.findAll();
        if(fullTextSearch != null) adList = adRepository.findAllByAdDescriptionLike(fullTextSearch);

        if(category !=null && location == null){
            Long finalMaxPrice = maxPrice;
            return adList.stream()
                    .filter(ad -> ad.getCategory().equals(category))
                    .filter(ad -> ad.getPrice() < finalMaxPrice)
                    .collect(Collectors.toList());
        }
        else if(category == null && location != null){
            Long finalMaxPrice1 = maxPrice;
            return adList.stream()
                    .filter(ad -> ad.getLocation().equals(location))
                    .filter(ad -> ad.getPrice() < finalMaxPrice1)
                    .collect(Collectors.toList());
        }
        else if(category != null){
            Long finalMaxPrice2 = maxPrice;
            return adList.stream()
                    .filter(ad -> ad.getCategory().equals(category))
                    .filter(ad -> ad.getLocation().equals(location))
                    .filter(ad -> ad.getPrice() < finalMaxPrice2)
                    .collect(Collectors.toList());
        }
        else {
            Long finalMaxPrice3 = maxPrice;
            return adList.stream()
                    .filter(ad -> ad.getPrice() < finalMaxPrice3)
                    .collect(Collectors.toList());
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
}
