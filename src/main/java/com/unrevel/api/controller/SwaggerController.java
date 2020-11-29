package com.unrevel.api.controller;

import com.unrevel.api.anotation.APiController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@APiController
@RequestMapping("/")
public class SwaggerController {
    @GetMapping
    public RedirectView getSwaggerPage(){
        return  new RedirectView("swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/");
    }
}
