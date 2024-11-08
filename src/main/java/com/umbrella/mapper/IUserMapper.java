package com.umbrella.mapper;

import com.umbrella.dto.request.UserRegistrationDTO;
import com.umbrella.dto.response.UserRegistrationSuccessDto;
import com.umbrella.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserMapper {
    UserRegistrationSuccessDto toRegistrationResponse(User user);
}
