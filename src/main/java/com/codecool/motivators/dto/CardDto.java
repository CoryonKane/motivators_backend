package com.codecool.motivators.dto;

import com.codecool.motivators.model.CardType;
import com.codecool.motivators.model.CardValueType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CardDto {
    private Long id;
    private CardType type;
    private int position;
    private CardValueType value;
    private Long listId;
}
