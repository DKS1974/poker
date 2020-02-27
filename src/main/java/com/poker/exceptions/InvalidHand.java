package com.poker.exceptions;

public class InvalidHand extends RuntimeException {
    public InvalidHand(String hand) {
        super("Invalid Hand - " + hand);
    }
}
