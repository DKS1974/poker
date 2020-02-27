package com.poker.service.rules;

import com.poker.model.Hand;

import java.util.TreeSet;

public class HighCard implements RankingRule {
    public static final String RANK_NAME = "High Card";
    private String additionInformation = null;

    @Override
    public void applyRankRule(Hand hand) {
        if (!hand.isRanked()) {
            hand.setRanked(this);
            additionInformation = getHighestCard(hand);
        }
    }

    @Override
    public String getAdditionInformation() {
        return additionInformation;
    }

    @Override
    public String getRankName() {
        return RANK_NAME;
    }

    private String getHighestCard(Hand hand) {
        return ((TreeSet) hand.getCards()).last().toString();
    }
}
