package com.javarush.kiriienko.entity.plants;

import com.javarush.kiriienko.abstraction.Eatable;
import com.javarush.kiriienko.entity.map.Cell;

public class Plants implements Eatable, Runnable {

    private final double maxCellCapacity;
    private final double growSpeed;
    private double weight;
    private final Cell cell;

    public Plants(double maxCellCapacity, double growSpeed, double weight, Cell cell) {
        this.maxCellCapacity = maxCellCapacity;
        this.growSpeed = growSpeed;
        this.weight = weight;
        this.cell = cell;
    }

    @Override
    public void run() {
        grow();
    }

    @Override
    public synchronized double satiate() {
        return weight;
    }

    @Override
    public synchronized void wasSatiated(double weight) {
        this.weight = this.weight - weight;
    }

    public void grow() {
        double maxPlantweight = ANIMAL_CONFIG.getFor("plants", "weight");
        if (weight < maxPlantweight) {
            weight = weight + maxPlantweight + growSpeed;
        }
    }

    public double getWeight() {
        return weight;
    }
}

