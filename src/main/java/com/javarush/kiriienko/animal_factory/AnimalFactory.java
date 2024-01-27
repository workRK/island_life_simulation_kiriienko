package com.javarush.kiriienko.animal_factory;

import com.javarush.kiriienko.entity.Animal;
import com.javarush.kiriienko.entity.herbivores.*;
import com.javarush.kiriienko.entity.map.Cell;
import com.javarush.kiriienko.entity.predators.*;

import java.util.Random;

import static com.javarush.kiriienko.config.GameConfig.ANIMAL_CONFIG;
import static com.javarush.kiriienko.config.GameConfig.ISLAND_CONFIG;

public class AnimalFactory {

    public static Animal createRandomAnimal(Cell cell) {
        Random random = new Random();
        AnimalType[] animalTypes = AnimalType.values();
        AnimalType type = animalTypes[random.nextInt(animalTypes.length)];
        return createAnimalByType(cell, type);
    }

    public static Animal createAnimalByType(Cell cell, AnimalType type) {
        Animal animal = null;
        double weight = ANIMAL_CONFIG.getFor(type.toString().toLowerCase(), "weight");
        double maxCellCapacity = ANIMAL_CONFIG.getFor(type.toString().toLowerCase(), "maxCellPopulation");
        double speed = ANIMAL_CONFIG.getFor(type.toString().toLowerCase(), "speed");
        double maxSaturation = ANIMAL_CONFIG.getFor(type.toString().toLowerCase(), "maxSaturation");
        double offspring = ANIMAL_CONFIG.getFor(type.toString().toLowerCase(), "offspring");
        double startupSaturation = maxSaturation * ISLAND_CONFIG.getStartupSaturationCoefficient();
        int daysForAnimalDecomposition = ISLAND_CONFIG.getDaysForAnimalDecomposition();
        Gender gender = getGender();
        switch (type) {
            case WOLF -> animal = new Wolf(weight, (int) maxCellCapacity, (int) speed, maxSaturation, cell,
                    (int) offspring, gender, startupSaturation, daysForAnimalDecomposition);
            case SNAKE -> animal = new Snake(weight, (int)maxCellCapacity, (int)speed, maxSaturation, cell,
                    (int) offspring, gender, startupSaturation, daysForAnimalDecomposition);
            case FOX -> animal = new Fox(weight, (int) maxCellCapacity, (int) speed, maxSaturation, cell,
                    (int) offspring, gender, startupSaturation, daysForAnimalDecomposition);
            case BEAR -> animal = new Bear(weight, (int) maxCellCapacity, (int) speed, maxSaturation, cell,
                    (int) offspring, gender, startupSaturation, daysForAnimalDecomposition);
            case EAGLE -> animal = new Eagle(weight, (int) maxCellCapacity, (int) speed, maxSaturation, cell,
                    (int) offspring, gender, startupSaturation, daysForAnimalDecomposition);
            case HORSE -> animal = new Horse(weight, (int) maxCellCapacity, (int) speed, maxSaturation, cell,
                    (int) offspring, gender, startupSaturation, daysForAnimalDecomposition);
            case DEER -> animal = new Deer(weight, (int) maxCellCapacity, (int) speed, maxSaturation, cell,
                    (int) offspring, gender, startupSaturation, daysForAnimalDecomposition);
            case RABBIT -> animal = new Rabbit(weight, (int) maxCellCapacity, (int) speed, maxSaturation, cell,
                    (int) offspring, gender, startupSaturation, daysForAnimalDecomposition);
            case MOUSE -> animal = new Mouse(weight, (int) maxCellCapacity, (int) speed, maxSaturation, cell,
                    (int) offspring, gender, startupSaturation, daysForAnimalDecomposition);
            case GOAT -> animal = new Goat(weight, (int) maxCellCapacity, (int) speed, maxSaturation, cell,
                    (int) offspring, gender, startupSaturation, daysForAnimalDecomposition);
            case SHEEP -> animal = new Sheep(weight, (int) maxCellCapacity, (int) speed, maxSaturation, cell,
                    (int) offspring, gender, startupSaturation, daysForAnimalDecomposition);
            case BOAR -> animal = new Boar(weight, (int) maxCellCapacity, (int) speed, maxSaturation, cell,
                    (int) offspring, gender, startupSaturation, daysForAnimalDecomposition);
            case BUFFALO -> animal = new Buffalo(weight, (int) maxCellCapacity, (int) speed, maxSaturation, cell,
                    (int) offspring, gender, startupSaturation, daysForAnimalDecomposition);
            case DUCK -> animal = new Duck(weight, (int) maxCellCapacity, (int) speed, maxSaturation, cell,
                    (int) offspring, gender, startupSaturation, daysForAnimalDecomposition);
            case CATERPILLAR -> animal = new Caterpillar(weight, (int) maxCellCapacity, (int) speed, maxSaturation, cell,
                    (int) offspring, gender, startupSaturation, daysForAnimalDecomposition);
        }
        return animal;
    }

    private static Gender getGender() {
        Random random = new Random();
        if (random.nextBoolean()) {
            return Gender.FEMALE;
        } else {
            return Gender.MALE;
        }
    }
}
