package com.poker.service.rules;

import com.poker.model.Hand;
import com.poker.model.factory.ObjectFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HighCardTest {
    RankingRule highCard = new HighCard();
    Hand hand = ObjectFactory.getHand("AD AC 3C 5S 9H");

    @Test
    public void shouldBeAbleToGetRankingRuleName() {
        assertEquals(HighCard.RANK_NAME, highCard.getRankName());
    }

    @Test
    public void shouldBeAbleToGetAdditionalInfo() {
        highCard.applyRankRule(hand);
        assertEquals("9H", highCard.getAdditionInformation());
    }

    @Test
    public void shouldBeAbleToEvaluateRanking() {
        highCard.applyRankRule(hand);
        assertEquals(HighCard.RANK_NAME, hand.getRanked().getRankName());
    }


}