package com.poker.service.rules;

import com.poker.model.Hand;

public interface RankingRule {
    void applyRankRule(Hand hand);

    String getRankName();

    String getAdditionInformation();
}
