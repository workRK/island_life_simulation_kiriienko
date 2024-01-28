package com.javarush.kiriienko.config;

import java.util.HashMap;
import java.util.Map;

public class AnimalConfigRow {

    private String animalName;
    private Map<String, Double> parameters = new HashMap<>();

    public AnimalConfigRow() {
    }

    public String getAnimalName() {
        return animalName;
    }

    public Map<String, Double> getParameters() {
        return parameters;
    }
}
