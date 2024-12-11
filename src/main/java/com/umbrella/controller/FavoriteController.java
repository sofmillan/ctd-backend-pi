package com.umbrella.controller;

import com.umbrella.dto.response.EventResponseDto;
import com.umbrella.service.IFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    private final IFavoriteService favoriteService;

    @PostMapping("")
    public void saveFavorite(@RequestParam Integer userId, @RequestParam Integer eventId){
        favoriteService.addFavorite(userId, eventId);
    }

    @GetMapping("")
    public List<EventResponseDto> getFavorites(@RequestParam Integer userId){
        return favoriteService.getFavorites(userId);
    }
}
