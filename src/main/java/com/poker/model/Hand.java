package com.poker.model;

import com.poker.service.rules.RankingRule;
import lombok.*;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hand {
    Set<Card> cards = new TreeSet();
    private RankingRule ranked;
    private boolean valid;

    public @Override
    String toString() {
        String hand = cards.stream().map(Object::toString).collect(Collectors.joining(" "));
        return String.format("%s => %s (%s)", hand, Optional.of(ranked).get().getRankName(), Optional.of(ranked).get().getAdditionInformation());
    }

    public RankingRule getRanked() {
        return ranked;
    }

    public boolean isRanked() {
        return ranked != null;
    }

    public void setRanked(RankingRule applied) {
        this.ranked = applied;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}