package com.hebronworks.registrationapp.service;

import com.hebronworks.registrationapp.model.AppUser;
import com.hebronworks.registrationapp.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AppUserDetailService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("No user found with email %s ", email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean emaiExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if (emaiExists) {
            throw new IllegalArgumentException("Email already exists");
        }
        String encoded = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encoded);
        appUserRepository.save(appUser);
        //TODO: Generate and send email confirmation token
        return "";
    }
}
