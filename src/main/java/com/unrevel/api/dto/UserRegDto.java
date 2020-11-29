package com.unrevel.api.dto;

import com.unrevel.api.model.Profile;
import com.unrevel.api.model.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserRegDto {
    @NotEmpty(message = "userName can not be empty ")
    @Size(min = 3,max = 15 ,message = "user name must 3 to 15 charter ")
    private String userName;
    @NotEmpty
    @Email(message = "email must be valid")
    private String email;
    @NotEmpty
    @Size(min = 5, max = 20, message = "The password length must be  minimum 5 to max 20 ")
    private String password;
    @NotEmpty(message = "full name is require")
    private String fullName;

    private Profile userProfileInfo;
}
