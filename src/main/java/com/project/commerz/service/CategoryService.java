package com.project.commerz.service;


import com.project.commerz.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAllCategories();
    Category findById(Long id);
}
