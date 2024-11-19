package com.umbrella.controller;

import com.umbrella.dto.response.GenreResponseDto;
import com.umbrella.service.IGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/genre")
public class GenreController {
    private final IGenreService genreService;
    @GetMapping()
    public List<GenreResponseDto> getGenres(@RequestParam(required = false, defaultValue = "false") boolean topGenres){
        return genreService.getGenres(topGenres);
    }

    @GetMapping("/ok")
    public String getGenres(){
        return "ci-cd";
    }
}
