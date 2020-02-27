package com.poker.service.rules;

import com.poker.model.Hand;
import com.poker.model.Ranks;
import com.poker.model.Suits;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class StraightFlush implements RankingRule {
    public static final String RANK_NAME = "Straight Flush";
    private String additionInformation = null;

    @Override
    public void applyRankRule(Hand hand) {
        Map<Ranks, Long> counted = RankingUtil.countGroupByRank(hand);
        Map<Suits, Long> countedBySuits = RankingUtil.countGroupBySuit(hand);
        if (!hand.isRanked() && counted.size() == 5
                && isStraight(counted) && countedBySuits.size() == 1) {
            hand.setRanked(this);
            additionInformation = getStraight(counted, countedBySuits);
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

    private boolean isStraight(Map<Ranks, Long> counted) {
        int[] values = counted.entrySet().stream()
                .map(Map.Entry::getKey)
                .mapToInt(key -> key.getAsInt()).toArray();
        Arrays.sort(values);
        boolean isStraight = true;
        for (int i = 1; i < 5; i++) {
            if (values[i - 1] + 1 != values[i]) {
                isStraight = false;
                break;
            }
        }
        return isStraight;
    }

    private String getStraight(Map<Ranks, Long> counted, Map<Suits, Long> countedBySuits) {
        return countedBySuits.entrySet().stream().findFirst().get().getKey().name()
                + "(" + counted.entrySet().stream()
                .map(Map.Entry::getKey)
                .map(key -> String.valueOf(key.getAsChar()))
                .collect(Collectors.joining(",")) + ")";
    }
}
