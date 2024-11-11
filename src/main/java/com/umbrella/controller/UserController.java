package com.umbrella.controller;

import com.umbrella.dto.request.UserRegistrationDTO;
import com.umbrella.dto.response.UserDetailDto;
import com.umbrella.dto.response.UserRegistrationSuccessDto;

import com.umbrella.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @PostMapping("/registration")
    public ResponseEntity<UserRegistrationSuccessDto> registerUser(@Validated @RequestBody UserRegistrationDTO registrationDTO) {
        return ResponseEntity.ok(userService.registerUser(registrationDTO));
    }

    @GetMapping("/me")
    public UserDetailDto getPersonalInformation(@RequestHeader("Authorization") String token){
        return userService.getUserDeatil(token);
    }

}
