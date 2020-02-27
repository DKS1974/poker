package com.poker.service.rules.engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RankingRuleEngineFactoryTest {

    @Test
    public void shoutlBeAbleToCreate() {
        assertNotNull(RankingRuleEngineFactory.create());
    }
}