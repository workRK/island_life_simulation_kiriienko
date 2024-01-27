package com.javarush.kiriienko.config;

import java.util.HashMap;
import java.util.Map;

public class EatingConfigRow {

    private String animalName;
    private Map<String, Integer> probabilities = new HashMap<>();

    public EatingConfigRow() {
    }

    public String getAnimalName() {
        return animalName;
    }

    public Map<String, Integer> getProbabilities() {
        return probabilities;
    }
}
