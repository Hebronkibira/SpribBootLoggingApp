package com.hebronworks.registrationapp.service;

import com.hebronworks.registrationapp.model.AppUser;
import com.hebronworks.registrationapp.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final AppUserDetailService appUserDetailService;

    public String register(RegistrationRequest registrationRequest) {
        boolean is_validEmail = emailValidator.test(registrationRequest.getEmail());
        //TODO: implement a regex for validating the email
        if (!is_validEmail) {
            throw new IllegalArgumentException("Its not a valid email address");
        }
        return appUserDetailService.signUpUser(
                new AppUser(registrationRequest.getFirstName(),
                        registrationRequest.getLastName(),
                        registrationRequest.getEmail(),
                        registrationRequest.getPassword(),
                        ApplicationUserRoles.USER)
        );
    }
}
