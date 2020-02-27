package com.poker.service.rules;

import com.poker.model.Hand;
import com.poker.model.Ranks;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Straight implements RankingRule {
    public static final String RANK_NAME = "Straight";
    private String additionInformation = null;

    @Override
    public void applyRankRule(Hand hand) {
        Map<Ranks, Long> counted = RankingUtil.countGroupByRank(hand);
        if (!hand.isRanked() && counted.size() == 5 && isStraight(counted)) {
            hand.setRanked(this);
            additionInformation = getStraight(counted);
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

    private String getStraight(Map<Ranks, Long> counted) {
        return counted.entrySet().stream()
                .map(Map.Entry::getKey)
                .map(key -> String.valueOf(key.getAsChar()))
                .collect(Collectors.joining(","));
    }
}
