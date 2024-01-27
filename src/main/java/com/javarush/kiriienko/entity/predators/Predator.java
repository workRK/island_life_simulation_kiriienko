package com.javarush.kiriienko.entity.predators;

import com.javarush.kiriienko.animal_factory.Gender;
import com.javarush.kiriienko.entity.Animal;
import com.javarush.kiriienko.entity.map.Cell;

public abstract class Predator extends Animal {
    public Predator(double weight, int maxCellPopulation, int speed, double maxSaturation, Cell cell, int offspring, Gender gender, double saturation, int daysBeforeDecay) {
        super(weight, maxCellPopulation, speed, maxSaturation, cell, offspring, gender, saturation, daysBeforeDecay);
    }
}
