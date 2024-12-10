package com.umbrella.controller;

import com.umbrella.dto.request.CategoryRequestDto;
import com.umbrella.dto.response.CategoryResponseDto;
import com.umbrella.dto.response.FeatureResponseDto;
import com.umbrella.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(
            @Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto createdCategory = categoryService.createCategory(categoryRequestDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

}
