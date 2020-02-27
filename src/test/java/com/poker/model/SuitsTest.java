package com.poker.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SuitsTest {

    @Test
    public void shouldBeAbleToCreateSuits() {
        Suits s = Suits.S;
        assertEquals("S", s.name());
    }

    @Test
    public void shouldBeAbleToGetIfPresent() {
        Suits s = Suits.getIfPresent("C");
        assertEquals("C", Suits.getIfPresent("C").name());
    }

    @Test
    public void shouldReturnNullWhenGetIfPresent() {
        assertNull(Suits.getIfPresent("X"));
    }
}