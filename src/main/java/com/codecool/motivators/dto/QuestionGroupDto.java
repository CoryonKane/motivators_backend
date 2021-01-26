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
public class QuestionGroupDto {
    private Long id;
    private String value;
    private List<Long> questionIds;
    private List<Long> invitedIds;
    private Long ownerId;
}
