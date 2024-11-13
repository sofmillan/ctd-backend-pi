package com.umbrella.service.impl;

import com.umbrella.dto.response.GenreResponseDto;
import com.umbrella.entity.Gallery;
import com.umbrella.entity.Genre;
import com.umbrella.mapper.IGenreMapper;
import com.umbrella.repository.GenreRepository;
import com.umbrella.service.IGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService implements IGenreService {
    private final GenreRepository repository;
    private final IGenreMapper mapper;
    public List<GenreResponseDto> getGenres(boolean topGenres) {
        List<Genre> genres =repository.findAll();
        if(topGenres){
            return genres.stream().limit(4).map(mapper::toResponseDto).collect(Collectors.toList());
        }
        return genres.stream().map(mapper::toResponseDto).collect(Collectors.toList());
    }
}
