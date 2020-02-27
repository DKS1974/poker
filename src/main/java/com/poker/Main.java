package com.poker;

import com.poker.model.Hand;
import com.poker.service.Poker;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java -jar poker-1.0-SNAPSHOT-jar-with-dependencies.jar <hands input  file path >");
            System.exit(0);
        }
        run(args[0]);
    }

    private static void run(String arg) throws MalformedURLException {
        String path = arg;
        URL url = new File(path).toURI().toURL();
        Poker poker = new Poker();
        List<Hand> hands = poker.processHands(url);
        hands.forEach(System.out::println);
    }
}
