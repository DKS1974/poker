package com.poker.service.rules.engine;

import com.poker.service.rules.*;

public class RankingRuleEngineFactory {
    public static RankingRuleEngine create() {
        RankingRuleEngine rankingRuleEngine = new RankingRuleEngine();
        rankingRuleEngine.addRankingRule(new StraightFlush());
        rankingRuleEngine.addRankingRule(new FourOfAKind());
        rankingRuleEngine.addRankingRule(new FullHouse());
        rankingRuleEngine.addRankingRule(new Flush());
        rankingRuleEngine.addRankingRule(new Straight());
        rankingRuleEngine.addRankingRule(new ThreeOfAKind());
        rankingRuleEngine.addRankingRule(new TwoPair());
        rankingRuleEngine.addRankingRule(new OnePair());
        rankingRuleEngine.addRankingRule(new HighCard());
        return rankingRuleEngine;
    }
}
