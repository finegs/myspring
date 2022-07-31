package com.example.demo.module;

import com.example.demo.util.MyLogFormatter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class MyMain {

    private static final Logger logger = java.util.logging.Logger.getGlobal();

    public static void main(String[] args) {
        loadMyLogger();
        logger.info(String.format("Main : %s", "#1"));

        handleStream();
    }

    private static void handleStream() {
        final Map<String, Integer> fmap = new HashMap<String, Integer>(Map.of("a1", 1, "a2", 2));
        Predicate<String> mapChecker = s -> fmap.containsKey(s);
        String[][] arrays = new String[][]{ {"a1", "a2"}, {"b1", "b2"}, {"c1", "c2", "c3"} };
        Stream<String[]> stream6 = Arrays.stream(arrays);
        Stream<String> stream7 = stream6.flatMap(s -> Arrays.stream(s));
//        stream7.map(String::toLowerCase).filter(mapChecker);
        stream7.filter(mapChecker)
//        stream7.filter(s-> s.startsWith("a"))
                .map(String::toUpperCase).forEach(System.out::println);
    }

    private static void loadMyLogger() {
        MyLogFormatter.setMyLogFormatter(logger);
    }
}
