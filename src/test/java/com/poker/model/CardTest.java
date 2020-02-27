package com.poker.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {

    @Test
    public void shouldBeAbleToBuildCard() {
        Card card = Card.builder().rank(Ranks.K).suit(Suits.H).build();
        assertEquals("KH", card.toString());
    }

    @Test
    public void shouldBeAbleToSetRank() {
        Card card = Card.builder().rank(Ranks.K).suit(Suits.H).build();
        card.setRank(Ranks.A);
        assertEquals("AH", card.toString());
    }

    @Test
    public void shouldBeAbleToSuit() {
        Card card = Card.builder().rank(Ranks.K).suit(Suits.H).build();
        card.setSuit(Suits.S);
        assertEquals("KS", card.toString());
    }

    @Test
    public void shouldBeAbleToCreateDefaultCard() {
        Card card = new Card();
        assertEquals(null, card.getRank());
        assertEquals(null, card.getSuit());
        card.setRank(Ranks.two);
        card.setSuit(Suits.S);
        assertEquals("2S", card.toString());
    }
}