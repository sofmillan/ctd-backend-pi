package com.umbrella.auth;

import com.umbrella.dto.request.LoginRequestDto;
import com.umbrella.dto.response.LoginResponseDto;
import com.umbrella.exception.ResourceNotFoundException;
import com.umbrella.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public LoginResponseDto authenticate(LoginRequestDto request) {
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(()-> new ResourceNotFoundException("User "+request.getEmail()+" is not registered."));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var jwtToken = jwtService.generateToken(user);

        return LoginResponseDto.builder()
                .token(jwtToken)
                .build();
    }

}
