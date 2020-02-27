package com.poker.service;

import com.poker.model.Hand;
import com.poker.model.factory.ObjectFactory;
import com.poker.service.rules.engine.RankingRuleEngineFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Poker {

    public List<Hand> processHands(URL pathFileName) {
        try (InputStream is = pathFileName.openStream()) {
            Stream<String> stream = new BufferedReader(new InputStreamReader(is)).lines();
            return processHands(stream);
        } catch (IOException | NullPointerException e) {
            if (e instanceof NullPointerException)
                System.out.println("Input file does not exists");
            if (e instanceof IOException)
                System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Hand> processHands(Stream<String> stream) {
        List<Hand> hands = stream.parallel().map(line -> ObjectFactory.getHand(line.replaceAll("\\s+", " ").toUpperCase())).collect(Collectors.toList());
        hands.stream().forEach(hand -> RankingRuleEngineFactory.create().evaluate(hand));
        return hands;
    }

}
