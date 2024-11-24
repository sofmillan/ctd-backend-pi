package com.umbrella.controller;

import com.umbrella.dto.response.CategoryResponseDto;
import com.umbrella.dto.response.FeatureResponseDto;
import com.umbrella.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final ICategoryService categoryService;
    @GetMapping()
    public List<CategoryResponseDto> getCategories(){
        return categoryService.getCategories();
    }
}
