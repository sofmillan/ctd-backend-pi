package com.umbrella.service;

import com.umbrella.dto.request.UserRegistrationDTO;
import com.umbrella.dto.response.UserRegistrationSuccessDto;

public interface IUserService {
    UserRegistrationSuccessDto registerUser(UserRegistrationDTO userRegistration);
}
