package com.poker.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Ranks {
    A("A", 1),
    two("2", 2),
    three("3", 3),
    four("4", 4),
    five("5", 5),
    six("6", 6),
    seven("7", 7),
    eight("8", 8),
    nine("9", 9),
    ten("10", 10),
    J("J", 11),
    Q("Q", 12),
    K("K", 13);
    private String asChar;
    private int asInt;

    Ranks(String asChar, int asInt) {
        this.asChar = asChar;
        this.asInt = asInt;
    }

    public static Ranks getIfPresent(String rankName) {
        return Arrays.stream(Ranks.values()).filter(suit -> suit.name().equalsIgnoreCase(rankName)).findFirst().orElse(null);
    }
}