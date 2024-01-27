package com.javarush.kiriienko.entity.herbivores;

import com.javarush.kiriienko.abstraction.Eatable;
import com.javarush.kiriienko.animal_factory.AnimalType;
import com.javarush.kiriienko.animal_factory.Gender;
import com.javarush.kiriienko.entity.Animal;
import com.javarush.kiriienko.entity.map.Cell;

import java.util.ArrayList;
import java.util.List;

public class Duck extends Herbivore{
    public Duck(double weight, int maxCellPopulation, int speed, double maxSaturation, Cell cell, int offspring, Gender gender, double saturation, int daysBeforeDecay) {
        super(weight, maxCellPopulation, speed, maxSaturation, cell, offspring, gender, saturation, daysBeforeDecay);
    }

    @Override
    protected List<Eatable> findDiet(Cell currentCell) {
        List<Eatable> eatableList = currentCell.getEatableList();
        List<Eatable> diet = new ArrayList<>();
        for (Eatable eatable :
                eatableList) {
            if (eatable instanceof Animal) { //change for instanceof Caterpillar
                Animal animal = (Animal) eatable;
                if (animal.getAnimalType() == AnimalType.CATERPILLAR) {
                    diet.add(animal);
                }
            } else {
                diet.add(eatable);
            }
        }
        return diet;
    }

    public AnimalType getAnimalType() {
        return AnimalType.DUCK;
    }
}
