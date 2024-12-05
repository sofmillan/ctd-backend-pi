package com.umbrella.service;

import com.umbrella.dto.request.CategoryRequestDto;
import com.umbrella.dto.response.CategoryResponseDto;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponseDto> getCategories();
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
}
