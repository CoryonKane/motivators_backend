package com.codecool.motivators.dto;

import com.codecool.motivators.model.CardType;
import com.codecool.motivators.model.CardValueType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CardDto {
    private Long id;
    @NotNull
    private CardType type;
    @NotNull
    private int position;
    @NotNull
    private CardValueType value;
    private Long listId;
}
