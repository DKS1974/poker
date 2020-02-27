package com.poker.model;

import java.util.Arrays;

public enum Suits {
    H,
    S,
    D,
    C;

    public static Suits getIfPresent(String suitName) {
        return Arrays.stream(Suits.values()).filter(suit -> suit.name().equalsIgnoreCase(suitName)).findFirst().orElse(null);
    }
}
