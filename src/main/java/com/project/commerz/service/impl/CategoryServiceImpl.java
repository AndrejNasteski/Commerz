package com.project.commerz.service.impl;

import com.project.commerz.model.Category;
import com.project.commerz.repository.jpa.CategoryRepository;
import com.project.commerz.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        if (id == null) return null;
        return categoryRepository.findAllByCategoryId(id);
    }

    @Override
    public Category findById(String id) {
        if (id == null || id.isEmpty() || id.equals("null")) return null;
        return categoryRepository.findAllByCategoryId(Long.valueOf(id));
    }
}
