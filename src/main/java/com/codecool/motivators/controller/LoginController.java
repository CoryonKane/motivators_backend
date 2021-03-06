package com.codecool.motivators.controller;

import com.codecool.motivators.dto.UserDto;
import com.codecool.motivators.model.UserCredentials;
import com.codecool.motivators.security.JwtTokenServices;
import com.codecool.motivators.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("login/")
@CrossOrigin(origins = {"https://development-codecool-moving-motivators.netlify.app/", "https://codecool-moving-motivators.netlify.app/"})
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenServices jwtTokenServices;
    private final UserService userService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody UserCredentials data) {
        try {
            String dataEmail = data.getEmail();
            UserDto user = userService.getUserDtoByEmail(dataEmail);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dataEmail, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            String token = jwtTokenServices.createToken(dataEmail, roles);
            Map<Object, Object> model = new HashMap<>();
            model.put("user", user);
            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid login attempt:" + data.getEmail() + data.getPassword());
        }
    }
}
