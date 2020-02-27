package com.poker.model;

import com.poker.model.comparator.CardComparator;
import com.poker.service.rules.RankingRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class HandTest {

    RankingRule mockRule;

    @BeforeEach
    public void init() {
        mockRule = mock(RankingRule.class);
        when(mockRule.getRankName()).thenReturn("Some Name");
        when(mockRule.getAdditionInformation()).thenReturn("Some Info");
        doNothing().when(mockRule).applyRankRule(any());
    }

    @Test
    public void shouldBeAbleToBuildHand() {
        Set<Card> cards = getCards();
        Hand hand = Hand.builder().cards(cards).ranked(mockRule).build();
        assertEquals("AH 2H JH QH KH => Some Name (Some Info)", hand.toString());
    }

    @Test
    public void shouldBeAbleToCreateDefaultHand() {
        Set<Card> cards = getCards();
        Hand hand = new Hand();
        hand.setCards(cards);
        hand.setRanked(mockRule);
        assertEquals("AH 2H JH QH KH => Some Name (Some Info)", hand.toString());
    }

    private Set<Card> getCards() {
        Set<Card> cards = new TreeSet<>(new CardComparator());
        cards.add(Card.builder().rank(Ranks.A).suit(Suits.H).build());
        cards.add(Card.builder().rank(Ranks.K).suit(Suits.H).build());
        cards.add(Card.builder().rank(Ranks.J).suit(Suits.H).build());
        cards.add(Card.builder().rank(Ranks.Q).suit(Suits.H).build());
        cards.add(Card.builder().rank(Ranks.two).suit(Suits.H).build());
        return cards;
    }
}