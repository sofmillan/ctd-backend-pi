package com.umbrella.service.impl;

import com.umbrella.dto.response.SuccessUpdateDto;
import com.umbrella.dto.response.UserPanelDto;
import com.umbrella.entity.Role;
import com.umbrella.entity.User;
import com.umbrella.exception.ResourceNotFoundException;
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
    private final RoleRepository roleRepository;

    @Override
    public List<UserPanelDto> getUsers() {
        return userRepository.findAll().stream().map(user->{
            var panel = new UserPanelDto();
            panel.setFullName(user.getName() + " "+user.getLastName());
            panel.setRole(user.getRole().getName());
            panel.setEmail(user.getEmail());
            panel.setAdmin(panel.getRole().equals("Administrator"));
            panel.setInitials(user.getName().substring(0,1).toUpperCase() +user.getLastName().substring(0,1).toUpperCase());
            return panel;
        }).collect(Collectors.toList());

    }

    @Override
    public SuccessUpdateDto changeRole(Integer userId, String roleName) {
        User foundUser = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with id "+userId+" was not found"));
        Role role = roleRepository.findByName(roleName).orElseThrow(()->new ResourceNotFoundException("Role: "+roleName+" is not registered"));
        foundUser.setRole(role);
        userRepository.save(foundUser);
        return new SuccessUpdateDto("User "+foundUser.getEmail()+" was updated successfully");
    }
}
