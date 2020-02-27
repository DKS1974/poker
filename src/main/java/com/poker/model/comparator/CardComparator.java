package com.poker.model.comparator;

import com.poker.model.Card;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {
    @Override
    public int compare(Card card, Card otherCard) {
        int rankCompared = card.getRank().getAsInt() - otherCard.getRank().getAsInt();
        if (rankCompared == 0) {
            rankCompared = card.getSuit().ordinal() - otherCard.getSuit().ordinal();
        }
        return rankCompared;
    }
}