package com.unrevel.api.controller;

import com.unrevel.api.anotation.APiController;
import com.unrevel.api.anotation.DataValidation;
import com.unrevel.api.dto.Response;
import com.unrevel.api.dto.UserRegDto;
import com.unrevel.api.enums.SecurityRole;
import com.unrevel.api.services.UserService;
import com.unrevel.api.utill.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.unrevel.api.utill.UrlConstrains.*;

@APiController
@RequestMapping(UserManagement.ROOT)
public class SignupController {
    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(UserManagement.ADMIN_SIGNUP)
    @DataValidation
    public ResponseEntity<Object> adminSignUp(@Valid @RequestBody UserRegDto dto, BindingResult result){
        var response = userService.registration(dto, SecurityRole.ADMIN);
        return ResponseEntity.status((int) response.getStatusCode()).body(response);
    }
    @PostMapping(UserManagement.REVIEWER_SIGNUP)
    @DataValidation
    public ResponseEntity<Object> reviewrSignUp(@Valid @RequestBody UserRegDto dto, BindingResult result){
        var response = userService.registration(dto, SecurityRole.REVIEWER);
        return ResponseEntity.status((int) response.getStatusCode()).body(response);
    }
    @PostMapping(UserManagement.INFLUENCER_SIGNUP)
    @DataValidation
    public ResponseEntity<Object> influencerSignUp(@Valid @RequestBody UserRegDto dto, BindingResult result){
        var response = userService.registration(dto, SecurityRole.ADMIN);
        return ResponseEntity.status((int) response.getStatusCode()).body(response);
    }
}
