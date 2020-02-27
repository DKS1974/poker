package com.poker.service;

import com.poker.model.Hand;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PokerTest {

    @Test
    public void shouleBeAbleToProcessStreamOfHands() {
        Poker poker = new Poker();
        List<String> handStream = Arrays.asList("AD 6C 9H 3C 5S",
                "AD AC 6H 3C 5S",
                "AD AC 8H 3C 5S",
                "AD AC KH 3C 5S",
                "AD AC 7H 3C 5S"
        );
        List<Hand> hands = poker.processHands(handStream.stream());
        assertEquals("9H", hands.get(0).getRanked().getAdditionInformation());
    }

    @Test
    public void shouleBeAbleProcessFileOfHands() throws IOException {
        Poker poker = new Poker();
        URL url = this.getClass().getClassLoader().getResource("testHands.txt");
        List<Hand> hands = poker.processHands(url);
        assertEquals("10C", hands.get(0).getRanked().getAdditionInformation());
    }

    @Test
    public void shouleReturnNullHandsIfCantProcess() throws IOException {
        Poker poker = new Poker();
        URL url = this.getClass().getClassLoader().getResource("somefile.txt");
        assertEquals(null, poker.processHands(url));
    }
}