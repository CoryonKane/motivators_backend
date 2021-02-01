package com.codecool.motivators.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NotificationDto {
    private Long id;
    private Long senderId;
    private Long ownerId;
    private Long groupId;
}
