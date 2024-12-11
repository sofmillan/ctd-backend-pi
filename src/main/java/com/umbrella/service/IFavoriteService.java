package com.umbrella.service;

import com.umbrella.dto.response.EventResponseDto;

import java.util.List;

public interface IFavoriteService {
    void addFavorite(Integer userId, Integer eventId);
    List<EventResponseDto> getFavorites(Integer userId);
}
