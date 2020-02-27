package com.poker.service.rules;

import com.poker.model.Hand;
import com.poker.model.Ranks;

import java.util.Map;
import java.util.stream.Collectors;

public class ThreeOfAKind implements RankingRule {
    public static final String RANK_NAME = "Three Of A Kind";
    private String additionInformation = null;

    @Override
    public void applyRankRule(Hand hand) {
        Map<Ranks, Long> counted = RankingUtil.countGroupByRank(hand);
        if (!hand.isRanked() && counted.size() == 3 && counted.containsValue(Long.valueOf(3))) {
            hand.setRanked(this);
            additionInformation = getThreeOfAKindRank(counted);
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

    private String getThreeOfAKindRank(Map<Ranks, Long> counted) {
        return counted.entrySet().stream()
                .filter(pair -> pair.getValue() == 3)
                .map(pair -> pair.getKey().getAsChar())
                .collect(Collectors.joining());
    }
}
