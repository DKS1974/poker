package com.poker.service.rules.engine;

import com.poker.model.Hand;
import com.poker.service.rules.RankingRule;

import java.util.ArrayList;
import java.util.List;

public class RankingRuleEngine {

    private List<RankingRule> rankingRule = new ArrayList();

    public void addRankingRule(RankingRule rankingRule) {
        this.rankingRule.add(rankingRule);
    }

    public void evaluate(Hand hand) {
        for (RankingRule rule : rankingRule) {
            rule.applyRankRule(hand);
            if (hand.isRanked()) break;
        }
    }

}
