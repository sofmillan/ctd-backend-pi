package com.umbrella.service;

import com.umbrella.dto.response.SuccessUpdateDto;
import com.umbrella.dto.response.UserPanelDto;

import java.util.List;

public interface IAdminService {
    List<UserPanelDto> getUsers();
    SuccessUpdateDto changeRole(Integer userId, String role);
}
