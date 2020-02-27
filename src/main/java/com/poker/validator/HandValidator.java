package com.poker.validator;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HandValidator extends GenericValidator<String> {
    private static final List<Predicate<String>> VALIDATORS = new LinkedList<>();

    static {
        VALIDATORS.add(hand -> hand != null);
        VALIDATORS.add(hand -> !hand.trim().isEmpty());
        VALIDATORS.add(hand -> hand.split(" ").length == 5);
        VALIDATORS.add(hand ->
                Arrays.stream(hand.split(" "))
                        .filter(card -> Collections.frequency(Arrays.asList(hand.split(" ")), card) > 1)
                        .collect(Collectors.toSet()).isEmpty());
    }

    private CardValidator cardValidator;

    public HandValidator() {
        super(VALIDATORS);
    }

    @Override
    public boolean test(String toValidate) {
        return super.test(toValidate) && validateCards(toValidate);
    }

    public void setCardValidator(CardValidator cardValidator) {
        this.cardValidator = cardValidator;
    }

    private boolean validateCards(String hand) {
        return cardValidator == null || Arrays.stream(hand.split(" ")).allMatch(card -> cardValidator.test(card));
    }
}
