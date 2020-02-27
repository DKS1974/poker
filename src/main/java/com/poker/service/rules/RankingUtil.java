package com.poker.service.rules;

import com.poker.model.Hand;
import com.poker.model.Ranks;
import com.poker.model.Suits;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RankingUtil {

    public static Map<Ranks, Long> countGroupByRank(Hand hand) {
        return hand.getCards().stream()
                .map(card -> card.getRank())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static Map<Suits, Long> countGroupBySuit(Hand hand) {
        return hand.getCards().stream()
                .map(card -> card.getSuit())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static Map<Ranks, Long> pairByRank(Map<Ranks, Long> counted) {
        return counted.entrySet().stream()
                .filter(pair -> pair.getValue() == 2)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
