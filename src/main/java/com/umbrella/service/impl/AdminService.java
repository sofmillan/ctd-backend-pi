package com.umbrella.service.impl;

import com.umbrella.dto.response.UserPanelDto;
import com.umbrella.repository.RoleRepository;
import com.umbrella.repository.UserRepository;
import com.umbrella.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {
    private final UserRepository userRepository;

    @Override
    public List<UserPanelDto> getUsers() {
        return userRepository.findAll().stream().map(user->{
            var panel = new UserPanelDto();
            panel.setFullName(user.getName() + " "+user.getLastName());
            panel.setRole(user.getRole().getName());
            panel.setEmail(user.getEmail());
            panel.setAdmin(panel.getRole().equals("administrator"));
            panel.setInitials(user.getName().substring(0,1).toUpperCase() +user.getLastName().substring(0,1).toUpperCase());
            return panel;
        }).collect(Collectors.toList());

    }
}
