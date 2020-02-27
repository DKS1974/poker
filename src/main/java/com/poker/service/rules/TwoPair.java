package com.poker.service.rules;

import com.poker.model.Hand;
import com.poker.model.Ranks;

import java.util.Map;
import java.util.stream.Collectors;

public class TwoPair implements RankingRule {
    public static final String RANK_NAME = "Two Pair";
    private String additionInformation = null;

    @Override
    public void applyRankRule(Hand hand) {
        Map<Ranks, Long> counted = RankingUtil.countGroupByRank(hand);
        Map<Ranks, Long> pairs = RankingUtil.pairByRank(counted);
        if (!hand.isRanked() && pairs.size() == 2) {
            hand.setRanked(this);
            additionInformation = getPairedRank(pairs);
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

    private String getPairedRank(Map<Ranks, Long> pairs) {
        return pairs.entrySet().stream()
                .map(Map.Entry::getKey)
                .map(key -> String.valueOf(key.getAsChar()))
                .collect(Collectors.joining(","));
    }
}
