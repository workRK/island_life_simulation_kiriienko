package com.javarush.kiriienko.config;

import java.util.ArrayList;
import java.util.List;

public class AnimalConfig {

    private List<AnimalConfigRow> rows = new ArrayList<>();

    public List<AnimalConfigRow> getRows() {
        return rows;
    }

    public void setRows(List<AnimalConfigRow> rows) {
        this.rows = rows;
    }

    public Double getFor(String animal, String parameter) {
        AnimalConfigRow row = rows.stream()
                .filter(animalConfigRow -> animalConfigRow.getAnimalName().equals(animal))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Smth went wrong with parameters"));

        return row.getParameters().get(parameter);
    }
}
