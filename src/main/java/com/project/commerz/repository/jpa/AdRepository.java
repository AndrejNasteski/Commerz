package com.project.commerz.repository.jpa;

import com.project.commerz.model.Ad;
import com.project.commerz.model.Category;
import com.project.commerz.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    List<Ad> findAllByAdId(Long id);
    List<Ad> findAllByAdDescriptionLike(String description);
    List<Ad> findAllByCategory(Category category);
    List<Ad> findAllByPriceLessThanEqual(Long price);
    List<Ad> findAllByLocation(Location location);
}
