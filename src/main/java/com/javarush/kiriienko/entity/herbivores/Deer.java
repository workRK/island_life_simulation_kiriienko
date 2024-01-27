package com.javarush.kiriienko.entity.herbivores;

import com.javarush.kiriienko.animal_factory.AnimalType;
import com.javarush.kiriienko.animal_factory.Gender;
import com.javarush.kiriienko.entity.map.Cell;

public class Deer extends Herbivore{
    public Deer(double weight, int maxCellPopulation, int speed, double maxSaturation, Cell cell, int offspring, Gender gender, double saturation, int daysBeforeDecay) {
        super(weight, maxCellPopulation, speed, maxSaturation, cell, offspring, gender, saturation, daysBeforeDecay);
    }

    public AnimalType getAnimalType(){
        return AnimalType.DEER;
    }
}
