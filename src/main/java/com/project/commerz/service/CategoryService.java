package com.project.commerz.service;


import com.project.commerz.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();

    Category findById(Long id);

    Category findById(String id);

}
