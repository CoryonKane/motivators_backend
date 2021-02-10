package com.codecool.motivators.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private Long id;
    @NotNull
    private String name;
    private String position;
    private String company;
    private List<Long> groupIds;
    private Long defaultCardListId;
    private List<Long> olderCardListsIds;
}
