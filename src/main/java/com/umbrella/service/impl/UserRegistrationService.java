package com.umbrella.service.impl;

import com.umbrella.auth.TokenUtils;
import com.umbrella.dto.request.UserRegistrationDTO;
import com.umbrella.dto.response.UserDetailDto;
import com.umbrella.dto.response.UserRegistrationSuccessDto;
import com.umbrella.entity.Role;
import com.umbrella.entity.User;
import com.umbrella.exception.ResourceNotFoundException;
import com.umbrella.mapper.IUserMapper;
import com.umbrella.repository.RoleRepository;
import com.umbrella.repository.UserRepository;
import com.umbrella.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    private final IUserMapper mapper;

    public UserRegistrationSuccessDto registerUser(UserRegistrationDTO registrationDTO) {
        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            throw new RuntimeException("This email is aleady registered.");
        }

        Role userRole = roleRepository.findById(2)
                .orElseThrow(() -> new RuntimeException("Role not fond"));

        User user = new User();
        user.setName(registrationDTO.getName());
        user.setLastName(registrationDTO.getLastName());
        user.setEmail(registrationDTO.getEmail());
        user.setPasswordHash(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setRole(userRole);
        User savedUser = userRepository.save(user);

        UserRegistrationSuccessDto dto = mapper.toRegistrationResponse(savedUser);
        dto.setId(savedUser.getId());
        return dto;

    }

    @Override
    public UserDetailDto getUserDeatil(String token) {
        String newtoken = token.replace("Bearer ","");
        String email = TokenUtils.getEmailFromToken(newtoken);
        User foundUser = userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User: "+email+" was not found"));
        return mapper.toDetail(foundUser);
    }


}
