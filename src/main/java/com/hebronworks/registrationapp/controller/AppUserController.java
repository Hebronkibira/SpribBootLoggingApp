package com.hebronworks.registrationapp.controller;

import com.hebronworks.registrationapp.service.EmailValidator;
import com.hebronworks.registrationapp.service.RegistrationRequest;
import com.hebronworks.registrationapp.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/registration")
public class AppUserController {
   private final RegistrationService registrationService;
   private final EmailValidator emailValidator;
    @PostMapping
    public String registerAppUser(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

}
