package com.umbrella.mapper;

import com.umbrella.dto.request.UserRegistrationDTO;
import com.umbrella.dto.response.UserDetailDto;
import com.umbrella.dto.response.UserPanelDto;
import com.umbrella.dto.response.UserRegistrationSuccessDto;
import com.umbrella.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserMapper {
    UserRegistrationSuccessDto toRegistrationResponse(User user);
    @Mapping(target = "role", source = "role.name")
    UserPanelDto toPanel(User user);

    @Mapping(target = "fullName", source = "user", qualifiedByName = "concatenateNames")
    @Mapping(target = "initials", source = "user", qualifiedByName = "getInitials")
    @Mapping(target = "role", source = "role.name")
    UserDetailDto toDetail(User user);

    @Named("concatenateNames")
    default String concatenateNames(User user) {
        return user.getName() + " " + user.getLastName();
    }

    @Named("getInitials")
    default String getInitials(User user) {
        return user.getName().substring(0,1).toUpperCase() +user.getLastName().substring(0,1).toUpperCase();
    }

}
