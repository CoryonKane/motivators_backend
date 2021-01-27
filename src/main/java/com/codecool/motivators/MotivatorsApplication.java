package com.codecool.motivators;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MotivatorsApplication {

    // TODO: 2021. 01. 26. add readme before v1.0
    // TODO: 2021. 01. 27. notification implementation
    // TODO: 2021. 01. 27. add company field to user
    // TODO: 2021. 01. 27. implement user checking in service layer after security layer implementation
    public static void main(String[] args) {
        SpringApplication.run(MotivatorsApplication.class, args);
    }

}
