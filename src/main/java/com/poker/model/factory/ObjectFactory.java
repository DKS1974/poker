package com.poker.model.factory;

import com.poker.exceptions.InvalidCard;
import com.poker.exceptions.InvalidHand;
import com.poker.model.Card;
import com.poker.model.Hand;
import com.poker.model.Ranks;
import com.poker.model.Suits;
import com.poker.model.comparator.CardComparator;
import com.poker.validator.CardValidator;
import com.poker.validator.HandValidator;

import java.util.*;
import java.util.stream.Collectors;

public class ObjectFactory {
    private static final Map<String, Ranks> rankIndex = new HashMap();
    public static HandValidator handValidator = new HandValidator();
    private static CardValidator cardValidator = new CardValidator();

    static {
        Arrays.stream(Ranks.values()).forEach(rank -> rankIndex.put(String.valueOf(rank.getAsChar()), rank));
    }

    public static Suits getSuit(String suit) {
        return Suits.getIfPresent(suit);
    }

    public static Ranks getRank(String rank) {
        return rankIndex.get(rank);
    }

    public static Card getCard(String rankSuit) {
        if (cardValidator.test(rankSuit)) {
            return Card.builder()
                    .suit(ObjectFactory.getSuit(String.valueOf(rankSuit.charAt(rankSuit.length() - 1))))
                    .rank(ObjectFactory.getRank(rankSuit.substring(0, rankSuit.length() - 1))).build();
        } else {
            throw new InvalidCard(rankSuit);
        }
    }

    public static Hand getHand(String hand) {
        handValidator.setCardValidator(cardValidator);
        if (handValidator.test(hand)) {
            Set<Card> cards = Arrays.stream(hand.split(" "))
                    .map(rankSuitPair -> getCard(rankSuitPair))
                    .collect(
                            Collectors.toCollection(
                                    () -> new TreeSet<>(
                                            new CardComparator())
                            )
                    );
            return Hand.builder().cards(cards).build();
        } else {
            throw new InvalidHand(hand);
        }
    }

}
