package com.umbrella.service.impl;

import com.umbrella.dto.response.CategoryResponseDto;
import com.umbrella.mapper.ICategoryMapper;
import com.umbrella.repository.CategoryRepository;
import com.umbrella.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final ICategoryMapper categoryMapper;
    @Override
    public List<CategoryResponseDto> getCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toResponseDto).toList();
    }
}