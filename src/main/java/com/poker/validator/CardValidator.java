package com.poker.validator;

import com.poker.model.factory.ObjectFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class CardValidator extends GenericValidator<String> {
    private static final List<Predicate<String>> VALIDATORS = new LinkedList<>();

    static {
        Predicate<String> lengthIs2 = (rankSuit) -> rankSuit.length() == 2;
        Predicate<String> lengthIs3 = (rankSuit) -> rankSuit.length() == 3;
        Predicate<String> startsWith10 = (rankSuit) -> rankSuit.startsWith("10");

        VALIDATORS.add(rankSuit -> rankSuit != null);
        VALIDATORS.add(rankSuit -> !rankSuit.trim().isEmpty());
        VALIDATORS.add(lengthIs2.or(lengthIs3.and(startsWith10)));
        VALIDATORS.add(rankSuit -> ObjectFactory.getSuit(String.valueOf(rankSuit.charAt(rankSuit.length() - 1))) != null);
        VALIDATORS.add(rankSuit -> ObjectFactory.getRank(rankSuit.substring(0, rankSuit.length() - 1)) != null);
    }

    public CardValidator() {
        super(VALIDATORS);
    }


}
