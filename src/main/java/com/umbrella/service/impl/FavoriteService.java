package com.umbrella.service.impl;

import com.umbrella.dto.response.EventResponseDto;
import com.umbrella.entity.Event;
import com.umbrella.entity.Favorite;
import com.umbrella.entity.User;
import com.umbrella.exception.ResourceNotFoundException;
import com.umbrella.mapper.IEventMapper;
import com.umbrella.repository.FavoriteRepository;
import com.umbrella.repository.IEventRepository;
import com.umbrella.repository.UserRepository;
import com.umbrella.service.IFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService implements IFavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final IEventMapper eventMapper;
    private final IEventRepository eventRepository;
    private final UserRepository userRepository;
    @Override
    public void addFavorite(Integer userId, Integer eventId) {
        Optional<Favorite> optionalFavorite = favoriteRepository.findByUserIdAndEventId(userId,eventId);
        if(optionalFavorite.isPresent()){
            favoriteRepository.delete(optionalFavorite.get());
        }else{
            Event foundEvent = eventRepository.findById(eventId).orElseThrow(ResourceNotFoundException::new);
            User foundUser = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
            Favorite favorite = new Favorite();
            favorite.setEvent(foundEvent);
            favorite.setUser(foundUser);
            favoriteRepository.save(favorite);
        }

    }

    @Override
    public List<EventResponseDto> getFavorites(Integer userId) {
        List<Favorite> foundFavorites = favoriteRepository.findByUserId(userId);
        return foundFavorites.stream().map(favorite -> eventMapper.toDto(favorite.getEvent())).toList();
    }
}
