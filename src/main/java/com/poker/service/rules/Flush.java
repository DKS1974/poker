package com.poker.service.rules;

import com.poker.model.Hand;
import com.poker.model.Suits;

import java.util.Map;

public class Flush implements RankingRule {
    public static final String RANK_NAME = "Flush";
    private String additionInformation = null;

    @Override
    public void applyRankRule(Hand hand) {
        Map<Suits, Long> counted = RankingUtil.countGroupBySuit(hand);
        if (!hand.isRanked() && counted.size() == 1) {
            hand.setRanked(this);
            additionInformation = getFlush(counted);
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

    private String getFlush(Map<Suits, Long> counted) {
        return counted.entrySet().stream().findFirst().get().getKey().toString();
    }
}
