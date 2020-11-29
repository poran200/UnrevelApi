package com.unrevel.api.services;

import com.unrevel.api.dto.Response;
import com.unrevel.api.dto.UserRegDto;
import com.unrevel.api.enums.SecurityRole;


public interface UserService {
    public Response registration(UserRegDto userRegDto, SecurityRole role);
}
