package com.codecool.motivators.service;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.dto.UserDto;
import com.codecool.motivators.model.CardValueType;
import com.codecool.motivators.model.User;
import com.codecool.motivators.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final DtoConverterService converter;
    private final UserRepository repository;
    private final CardListService cardListService;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            @Lazy DtoConverterService converter,
            UserRepository repository,
            @Lazy CardListService cardListService) {
        this.converter = converter;
        this.repository = repository;
        this.cardListService = cardListService;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public User getUserById(Long id) {
        return repository.getOne(id);
    }

    public User getUserByEmail (String email) {
        return repository.findByEmail(email).orElse(null);
    }

    public UserDto getUserDtoById(Long id) {
        return converter.convertUser(getUserById(id));
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public List<CardDto> addDefault(String email, List<CardDto> list) {
        User user = getUserByEmail(email);
        list.forEach(cardDto -> cardDto.setValue(CardValueType.NEUTRAL));
        user.addDefault(cardListService.createCardList(list));
        return converter.convertCardList(saveUser(user).getNewestDefault());
    }

    public List<UserDto> searchUser(String name) {
        List<User> list = repository.findAllByName(name);
        return list.stream().map(converter::convertUser).collect(Collectors.toList());
    }

    public UserDto editUser(UserDto userDto, String email) {
        User user = getUserByEmail(email);
        user.setName(userDto.getName());
        user.setPosition(userDto.getPosition());
        user.setCompany(userDto.getCompany());
        return converter.convertUser(saveUser(user));
    }

    public UserDto registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (getUserByEmail(user.getEmail()) == null) {
            user.setId(null);
            return converter.convertUser(saveUser(user));
        } else {
            return null;
        }
    }

    public UserDto getUserDtoByEmail(String dataEmail) {
        return converter.convertUser(getUserByEmail(dataEmail));
    }
}
