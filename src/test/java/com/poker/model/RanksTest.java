package com.poker.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RanksTest {
    @Test
    public void shouldBeAbletoBuildCreateRanks() {
        Ranks r = Ranks.valueOf("four");
        assertEquals("4", r.getAsChar());
    }

    @Test
    public void shouldBeAbleToGetIfPresent() {
        assertEquals("K", Ranks.getIfPresent("K").name());
    }

    @Test
    public void shouldReturnNullWhenGetIfPresent() {
        assertNull(Ranks.getIfPresent("x"));
    }
}