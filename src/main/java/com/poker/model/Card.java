package com.poker.model;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class Card {
    private Ranks rank;
    private Suits suit;

    public @Override
    String toString() {
        return rank.getAsChar() + suit.name();
    }
}
