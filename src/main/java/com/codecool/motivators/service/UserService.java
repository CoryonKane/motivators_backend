package com.codecool.motivators.service;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.dto.NotificationDto;
import com.codecool.motivators.dto.QuestionGroupDto;
import com.codecool.motivators.dto.UserDto;
import com.codecool.motivators.model.CardValueType;
import com.codecool.motivators.model.Notification;
import com.codecool.motivators.model.User;
import com.codecool.motivators.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final DtoConverterService converter;
    private final UserRepository repository;
    private final CardListService cardListService;

    public UserService(
            @Lazy DtoConverterService converter,
            UserRepository repository,
            @Lazy CardListService cardListService,
            @Lazy NotificationService notificationService) {
        this.converter = converter;
        this.repository = repository;
        this.cardListService = cardListService;
    }

    public User getUserById(Long id) {
        return repository.getOne(id);
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public UserDto getUserDtoById(Long id) {
        return converter.convertUser(getUserById(id));
    }

    public List<CardDto> addDefault(Long id, List<CardDto> list) {
        User user = getUserById(id);
        list.forEach(cardDto -> cardDto.setValue(CardValueType.NEUTRAL));
        user.addDefault(cardListService.createCardList(list));
        return converter.convertCardList(saveUser(user).getNewestDefault());
    }

    public List<UserDto> searchUser(String name) {
        List<User> list = repository.findAllByName(name);
        return list.stream().map(converter::convertUser).collect(Collectors.toList());
    }

    public UserDto editUser(UserDto userDto) {
        User user = getUserById(userDto.getId());
        user.setName(userDto.getName());
        user.setPosition(userDto.getPosition());
        user.setCompany(userDto.getCompany());
        return converter.convertUser(saveUser(user));
    }
}
