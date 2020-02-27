package com.poker.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HandValidatorTest {
    CardValidator cardValidator = mock(CardValidator.class);
    HandValidator handValidator = null;

    @BeforeEach
    public void init() {
        handValidator = new HandValidator();
        when(cardValidator.test(anyString())).thenAnswer(
                new Answer<Boolean>() {
                    @Override
                    public Boolean answer(InvocationOnMock invocation) throws Throwable {
                        String card = invocation.getArgumentAt(0, String.class);
                        return !card.equalsIgnoreCase("1C");
                    }
                }
        );
        handValidator.setCardValidator(cardValidator);
    }

    @Test
    public void shouldBeAbleToValidateHand() {
        assertTrue(handValidator.test("AD AC 3C 5S 9H"));
    }

    @Test
    public void shouldFailValidationDuplicateCard() {
        assertFalse(handValidator.test("AD AD 3C 5S 9H"));
    }


    @Test
    public void shouldFailValidationInvalidCard() {
        assertFalse(handValidator.test("AD 1C 3C 5S 9H"));
    }

    @Test
    public void shouldFailValidationInvalidNumberOfCards() {
        assertFalse(handValidator.test("AD AC 3C 5S 9H 5S"));
    }
}