package com.javarush.kiriienko.config;

import java.util.ArrayList;
import java.util.List;

public class EatingProbabilityConfig {

    public List<EatingConfigRow> getRows() {
        return rows;
    }

    public void setRows(List<EatingConfigRow> rows) {
        this.rows = rows;
    }

    private List<EatingConfigRow> rows = new ArrayList<>();

    public Double getFor(String predator, String food) {
        EatingConfigRow row = rows.stream()
                .filter(eatingConfigRow -> eatingConfigRow.getAnimalName().equals(predator))
                .findFirst()
                .get();

        return row.getProbabilities().get(food) / 100.0;
    }
}
