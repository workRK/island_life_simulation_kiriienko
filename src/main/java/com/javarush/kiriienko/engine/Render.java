package com.javarush.kiriienko.engine;

import com.javarush.kiriienko.animal_factory.AnimalType;
import com.javarush.kiriienko.entity.Animal;
import com.javarush.kiriienko.entity.plants.Plants;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class Render  implements Runnable {

    private final Simulation simulation;

    public Render(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void run() {
        printInfo();
    }

    public void printInfo() {
        List<Animal> animalList = simulation.getAnimalList();
        List<Plants> plantsList = simulation.getPlantsList();

        System.out.println("\uD83D\uDCC5: " + simulation.getDayCounter() +
                " Total animals alive: " + animalList.stream().filter(animal -> !animal.isDead()).toList().size() +
                " Total animals dead: " + animalList.stream().filter(Animal::isDead).toList().size());
        double sumPlantsWeight = 0;
        for (Plants plants : plantsList) {
            sumPlantsWeight = sumPlantsWeight + plants.getWeight();
        }
        Map<AnimalType, List<Animal>> animalsByType = animalList.stream()
                .collect(groupingBy(Animal::getAnimalType));

        System.out.println("\uD83C\uDF3F: " + new DecimalFormat("#0.00").format(sumPlantsWeight) + "\t" +
                AnimalType.WOLF.getPicture() + animalsByType.get(AnimalType.WOLF).size() + "\t" +
                AnimalType.SNAKE.getPicture() + animalList.stream()
                .filter(animal -> !animal.isDead())
                .filter(animal -> animal.getAnimalType() == AnimalType.SNAKE)
                .toList().size() + "\t" +
                AnimalType.FOX.getPicture() + animalList.stream()
                .filter(animal -> !animal.isDead())
                .filter(animal -> animal.getAnimalType() == AnimalType.FOX)
                .toList().size() + "\t" +
                AnimalType.BEAR.getPicture() + animalList.stream()
                .filter(animal -> !animal.isDead())
                .filter(animal -> animal.getAnimalType() == AnimalType.BEAR)
                .toList().size() + "\t" +
                AnimalType.EAGLE.getPicture() + animalList.stream()
                .filter(animal -> !animal.isDead())
                .filter(animal -> animal.getAnimalType() == AnimalType.EAGLE)
                .toList().size() + "\t" +
                AnimalType.HORSE.getPicture() + animalList.stream()
                .filter(animal -> !animal.isDead())
                .filter(animal -> animal.getAnimalType() == AnimalType.HORSE)
                .toList().size() + "\t" +
                AnimalType.DEER.getPicture() + animalList.stream()
                .filter(animal -> !animal.isDead())
                .filter(animal -> animal.getAnimalType() == AnimalType.DEER)
                .toList().size() + "\t" +
                AnimalType.RABBIT.getPicture() + animalList.stream()
                .filter(animal -> !animal.isDead())
                .filter(animal -> animal.getAnimalType() == AnimalType.RABBIT)
                .toList().size() + "\t" +
                AnimalType.MOUSE.getPicture() + animalList.stream()
                .filter(animal -> !animal.isDead())
                .filter(animal -> animal.getAnimalType() == AnimalType.MOUSE)
                .toList().size() + "\t" +
                AnimalType.GOAT.getPicture() + animalList.stream()
                .filter(animal -> !animal.isDead())
                .filter(animal -> animal.getAnimalType() == AnimalType.GOAT)
                .toList().size() + "\t" +
                AnimalType.SHEEP.getPicture() + animalList.stream()
                .filter(animal -> !animal.isDead())
                .filter(animal -> animal.getAnimalType() == AnimalType.SHEEP)
                .toList().size() + "\t" +
                AnimalType.BOAR.getPicture() + animalList.stream()
                .filter(animal -> !animal.isDead())
                .filter(animal -> animal.getAnimalType() == AnimalType.BOAR)
                .toList().size() + "\t" +
                AnimalType.BUFFALO.getPicture() + animalList.stream()
                .filter(animal -> !animal.isDead())
                .filter(animal -> animal.getAnimalType() == AnimalType.BUFFALO)
                .toList().size() + "\t" +
                AnimalType.DUCK.getPicture() + animalList.stream()
                .filter(animal -> !animal.isDead())
                .filter(animal -> animal.getAnimalType() == AnimalType.DUCK)
                .toList().size() + "\t" +
                AnimalType.CATERPILLAR.getPicture() + animalList.stream()
                .filter(animal -> !animal.isDead())
                .filter(animal -> animal.getAnimalType() == AnimalType.CATERPILLAR)
                .toList().size()
        );

    }
}
