package com.codecool.motivators.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NotificationDto {
    private Long id;
    @NotNull
    private Long senderId;
    @NotNull
    private Long ownerId;
    @NotNull
    private Long groupId;
}
