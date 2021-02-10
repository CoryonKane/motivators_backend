package com.codecool.motivators.dto;

import lombok.*;
import javax.validation.constraints.NotNull;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class QuestionDto {
    private Long id;
    @NotNull
    private String value;
    private Long answerId;
    private String note;
    private boolean closed;
    @NotNull
    private Long groupId;
    private Date date;
}
