package com.codecool.motivators.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class QuestionDto {
    private Long id;
    private String value;
    @Nullable
    private Long answerId;
    @Nullable
    private String note;
    private boolean closed;
    private Long groupId;
    private Date date;
}
