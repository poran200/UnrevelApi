package com.unrevel.api.services.impl;

import com.unrevel.api.dto.Response;
import com.unrevel.api.dto.UserRegDto;
import com.unrevel.api.dto.UserRespondDto;
import com.unrevel.api.enums.SecurityRole;
import com.unrevel.api.model.Profile;
import com.unrevel.api.model.Role;
import com.unrevel.api.model.User;
import com.unrevel.api.repository.UserRepository;
import com.unrevel.api.services.ProfileService;
import com.unrevel.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.unrevel.api.utill.ResponseBuilder.getFailureResponse;
import static com.unrevel.api.utill.ResponseBuilder.getSuccessResponse;
import static com.unrevel.api.utill.SecurityConstants.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ProfileService profileService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.profileService = profileService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Response registration(UserRegDto userRegDto, SecurityRole role) {
        userRegDto.setPassword(passwordEncoder.encode(userRegDto.getPassword()));
        var userOptional = userRepository.findByUserNameIsAndIsActiveTrue(userRegDto.getUserName());
        if (userOptional.isPresent()){
            return getFailureResponse(HttpStatus.BAD_REQUEST,"User name already exits! userName = "+userRegDto.getUserName());
        }else {
            var userByeMail = userRepository.findByEmailAndIsActiveTrue(userRegDto.getEmail());
            if (userByeMail.isPresent()){
                return getFailureResponse(HttpStatus.BAD_REQUEST,"Email already exits !");
            }
            User  user = modelMapper.map(userRegDto,User.class);
             switch (role){
                 case ADMIN: user.addRole(new Role("Role_"+ADMIN.toLowerCase()));
                 break;
                 case REVIEWER:user.addRole(new Role("Role_"+REVIEWER.toLowerCase()));
                 break;
                 case INFLUENCER:user.addRole(new Role("Role_"+INFLUENCER.toLowerCase()));
                 break;
                 default: break;
             }

            var saveUser = userRepository.save(user);
            var dto = userRegDto.getProfileDto();
            var userProfileInfo = modelMapper.map(dto, Profile.class);

            if (userProfileInfo != null){
                userProfileInfo.setUser(saveUser);
                profileService.create(userProfileInfo);
            }
          return  getSuccessResponse(HttpStatus.CREATED,"User created",
                  modelMapper.map(saveUser, UserRespondDto.class));
        }
    }
}
