package com.poker.service.rules.engine;

import com.poker.model.Hand;
import com.poker.model.factory.ObjectFactory;
import com.poker.service.rules.RankingRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RankingRuleEngineTest {
    private RankingRuleEngine rankingRuleEngine = null;
    private RankingRule mockRule, anotherMockRule, thirdMockRule;
    private Hand hand = null;

    @BeforeEach
    public void init() {
        rankingRuleEngine = new RankingRuleEngine();
        hand = ObjectFactory.getHand("AD AC 3C 5S 9H");
        mockRule = createMockRankngRule("Some Name", "Some Info");
        anotherMockRule = createMockRankngRule("Some Other Name", "Some Other Info");
        thirdMockRule = createMockRankngRule("Some third Name", "Some third Info");
    }

    @Test
    public void shouldBeAbleToApplyRankingRules() {
        updateHandsRankendRule(mockRule);
        rankingRuleEngine.addRankingRule(mockRule);
        rankingRuleEngine.evaluate(hand);
        assertEquals("Some Name", hand.getRanked().getRankName());
        assertEquals("Some Info", hand.getRanked().getAdditionInformation());
        assertTrue(hand.isRanked());
    }

    @Test
    public void shouldBeAbleToApplyHigherOrderRankingRules() {
        updateHandsRankendRule(anotherMockRule);
        rankingRuleEngine.addRankingRule(anotherMockRule);
        rankingRuleEngine.addRankingRule(mockRule);
        rankingRuleEngine.evaluate(hand);
        assertEquals("Some Other Name", hand.getRanked().getRankName());
        assertEquals("Some Other Info", hand.getRanked().getAdditionInformation());
        assertTrue(hand.isRanked());
    }

    @Test
    public void shouldBeAbleToApplyMiddleOrderRankingRules() {
        updateHandsRankendRule(thirdMockRule);
        rankingRuleEngine.addRankingRule(anotherMockRule);
        rankingRuleEngine.addRankingRule(thirdMockRule);
        rankingRuleEngine.addRankingRule(mockRule);
        rankingRuleEngine.evaluate(hand);
        assertEquals("Some third Name", hand.getRanked().getRankName());
        assertEquals("Some third Info", hand.getRanked().getAdditionInformation());
        assertTrue(hand.isRanked());
    }

    @Test
    public void shouldBeAbleToApplyLowestOrderRankingRules() {
        updateHandsRankendRule(mockRule);
        rankingRuleEngine.addRankingRule(anotherMockRule);
        rankingRuleEngine.addRankingRule(thirdMockRule);
        rankingRuleEngine.addRankingRule(mockRule);
        rankingRuleEngine.evaluate(hand);
        assertEquals("Some Name", hand.getRanked().getRankName());
        assertEquals("Some Info", hand.getRanked().getAdditionInformation());
        assertTrue(hand.isRanked());
    }


    private RankingRule createMockRankngRule(String rankingName, String rankingAddInfo) {
        RankingRule mockRule = mock(RankingRule.class);
        when(mockRule.getRankName()).thenReturn(rankingName);
        when(mockRule.getAdditionInformation()).thenReturn(rankingAddInfo);

        return mockRule;
    }

    private void updateHandsRankendRule(RankingRule mockRule) {
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Hand hand = invocation.getArgumentAt(0, Hand.class);
                hand.setRanked(mockRule);
                return null;
            }
        }).when(mockRule).applyRankRule(hand);
    }
}