package com.project.commerz.repository.jpa;

import com.project.commerz.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findAllByCategoryId(Long id);
}
