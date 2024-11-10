package com.umbrella.controller;

import com.umbrella.dto.response.UserPanelDto;
import com.umbrella.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final IAdminService adminService;

    @GetMapping("/users")
    public List<UserPanelDto> getUsers(){
        return adminService.getUsers();
    }
}
