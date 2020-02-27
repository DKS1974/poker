package com.poker.validator;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenericValidatorTest {

    @Test
    public void shouldEvaluateToTrue() {
        String someData = "AD";
        List<Predicate<String>> validators = new LinkedList<>();
        validators.add(data -> data != null);
        validators.add(data -> !data.trim().isEmpty());
        validators.add(data -> data.length() == 2);
        GenericValidator<String> genericValidator = new GenericValidator(validators);
        assertTrue(genericValidator.test(someData));
    }


    @Test
    public void shouldEvaluateToFalse() {
        String someData = "AdD";
        List<Predicate<String>> validators = new LinkedList<>();
        validators.add(data -> data != null);
        validators.add(data -> !data.trim().isEmpty());
        validators.add(data -> data.length() == 2);
        GenericValidator<String> genericValidator = new GenericValidator(validators);
        assertFalse(genericValidator.test(someData));
    }
}