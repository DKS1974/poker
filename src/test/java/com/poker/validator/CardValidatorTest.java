package com.poker.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardValidatorTest {

    @Test
    public void shouldReturnValidCard() {
        CardValidator cardValidator = new CardValidator();
        assertTrue(cardValidator.test("AD"));
    }

    @Test
    public void shouldReturnValidCard10() {
        CardValidator cardValidator = new CardValidator();
        assertTrue(cardValidator.test("10D"));
    }

    @Test
    public void shouldReturnInvalidCard() {
        CardValidator cardValidator = new CardValidator();
        assertFalse(cardValidator.test("AK"));
    }

}