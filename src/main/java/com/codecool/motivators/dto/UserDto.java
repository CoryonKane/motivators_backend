package com.codecool.motivators.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String position;
    private List<Long> groupIds;
    private Long defaultCardListId;
    private List<Long> cardListIds;
}
