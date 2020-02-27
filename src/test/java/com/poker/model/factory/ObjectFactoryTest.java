package com.poker.model.factory;

import com.poker.exceptions.InvalidCard;
import com.poker.exceptions.InvalidHand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectFactoryTest {

    @Test
    public void shouldBeAbleToCreateCardInstance() {
        assertNotNull(ObjectFactory.getCard("AD"));
    }

    @Test
    public void shouldNotBeAbleToCreateCardInstance() {
        assertThrows(InvalidCard.class, () -> { ObjectFactory.getCard("AK");});
    }

    @Test
    public void shouldBeAbleToCreateRankInstance() {
        assertNotNull(ObjectFactory.getRank("A"));
    }

    @Test
    public void shouldNotBeAbleToCreateRankInstance() {
        assertNull(ObjectFactory.getRank("X"));
    }

    @Test
    public void shouldBeAbleToCreateSuitInstance() {
        assertNotNull(ObjectFactory.getSuit("D"));
    }

    @Test
    public void shouldNotBeAbleToCreateSuitInstance() {
        assertNull(ObjectFactory.getSuit("X"));
    }

    @Test
    public void shouldBeAbleToCreareHand() {
        assertNotNull(ObjectFactory.getHand("AD AC 3C 5S 9H"));
    }


    @Test
    public void shouldNotBeAbleToCreareHand() {
        assertThrows(InvalidHand.class,() -> { ObjectFactory.getHand("AD 1C 3C 5S 9H");});
    }


}