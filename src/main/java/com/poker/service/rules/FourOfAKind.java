package com.poker.service.rules;

import com.poker.model.Hand;
import com.poker.model.Ranks;

import java.util.Map;
import java.util.stream.Collectors;

public class FourOfAKind implements RankingRule {
    public static final String RANK_NAME = "Four Of A Kind";
    private String additionInformation = null;

    @Override
    public void applyRankRule(Hand hand) {
        Map<Ranks, Long> counted = RankingUtil.countGroupByRank(hand);
        if (!hand.isRanked() && counted.size() == 2 && counted.containsValue(Long.valueOf(4))) {
            hand.setRanked(this);
            additionInformation = getFourOfAKindRank(counted);
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

    private String getFourOfAKindRank(Map<Ranks, Long> counted) {
        return counted.entrySet().stream()
                .filter(pair -> pair.getValue() == 4)
                .map(pair -> pair.getKey().getAsChar())
                .collect(Collectors.joining());
    }
}
