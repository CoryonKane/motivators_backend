package com.codecool.motivators;

import com.codecool.motivators.model.User;
import com.codecool.motivators.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MotivatorsApplication {

    @Autowired
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    // TODO: 2021. 01. 26. add readme before v1.0
    // TODO: 2021. 02. 04. delete user api
    public static void main(String[] args) {
        SpringApplication.run(MotivatorsApplication.class, args);
    }

}
