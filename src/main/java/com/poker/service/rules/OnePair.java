package com.poker.service.rules;

import com.poker.model.Hand;
import com.poker.model.Ranks;

import java.util.Map;

public class OnePair implements RankingRule {
    public static final String RANK_NAME = "One Pair";
    private String additionInformation = null;

    @Override
    public void applyRankRule(Hand hand) {
        Map<Ranks, Long> counted = RankingUtil.countGroupByRank(hand);
        Map<Ranks, Long> pairs = RankingUtil.pairByRank(counted);
        if (!hand.isRanked() && pairs.size() == 1) {
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
                .map(Map.Entry::getKey).map(key -> String.valueOf(key.getAsChar())).findFirst().get();
    }
}
