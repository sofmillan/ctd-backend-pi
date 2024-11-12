package com.umbrella.service;

import com.umbrella.dto.response.GenreResponseDto;

import java.util.List;

public interface IGenreService {
    List<GenreResponseDto> getGenres(boolean topGenres);
}
