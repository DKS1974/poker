package com.poker.exceptions;

public class InvalidCard extends RuntimeException {
    public InvalidCard(String card) {
        super("Invalid Card - " + card);
    }
}
