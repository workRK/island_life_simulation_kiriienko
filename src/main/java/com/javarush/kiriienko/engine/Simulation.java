package com.javarush.kiriienko.engine;

import com.javarush.kiriienko.entity.Animal;
import com.javarush.kiriienko.entity.map.Cell;
import com.javarush.kiriienko.entity.map.Island;
import com.javarush.kiriienko.entity.plants.Plants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.javarush.kiriienko.config.GameConfig.ANIMAL_CONFIG;
import static com.javarush.kiriienko.config.GameConfig.ISLAND_CONFIG;

public class Simulation implements Runnable{

    private int dayCounter;
    private List<Animal> animalList;
    private List<Plants> plantsList;
    private final Island island;

    public ExecutorService getExecutorService() {
        return executorService;
    }

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public Simulation(Island island) {
        this.dayCounter = 0;
        this.island = island;
        updateLists();
    }


    @Override
    public void run() {
        startSimulation();
    }

    public void startSimulation(){

        dayCounter++;
        animalList.stream()
                .filter(animal -> !animal.isDead())
                .forEach(executorService::submit);
        plantsList.forEach(executorService::submit);
        updateAnimalCondition(); // need to wait until all Futures are completed
        updateLists();
    }

    private void updateAnimalCondition() {
        double dailyAnimalConsumptionCoefficient = ISLAND_CONFIG.getDailyAnimalConsumptionCoefficient();
        for (Animal animal : animalList) {
            if (animal.isDead()){
                if (animal.getDaysBeforeDecay() == 0){
                    animal.getCell().getEatableList().remove(animal);
                } else if (animal.getWeight() <= 0) {
                    animal.getCell().getEatableList().remove(animal);
                } else {
                    int daysBeforeDecay = animal.getDaysBeforeDecay();
                    daysBeforeDecay--;
                    animal.setDaysBeforeDecay(daysBeforeDecay);
                }
            } else if (animal.getSaturation() <= 0) {
                animal.die();
            } else {
                double animalSaturation = animal.getSaturation();
                animal.setSaturation(animalSaturation - animal.getMaxSaturation() * dailyAnimalConsumptionCoefficient);
                String animalName = animal.getAnimalType().toString().toLowerCase();
                double animalSpeed = ANIMAL_CONFIG
                        .getFor(animalName, "speed");
                animal.setSpeed((int)animalSpeed);
            }
        }
    }

    private void updateLists() {
        Cell[][] islandMap = island.getIslandMap();
        List<Animal> actualAnimalList = new ArrayList<>();
        List<Plants> actualPlantsList = new ArrayList<>();
        for (int i = 0; i < islandMap.length; i++) {
            for (int j = 0; j < islandMap[i].length; j++) {
                Cell cell = islandMap[i][j];
                actualPlantsList.addAll(cell.getEatableList().stream()
                        .filter(plant -> plant instanceof Plants)
                        .map(Plants.class::cast)
                        .toList());
                actualAnimalList.addAll(cell.getEatableList().stream()
                        .filter(animal -> animal instanceof Animal)
                        .map(Animal.class::cast)
                        .toList());
            }
        }
        plantsList = actualPlantsList;
        animalList = actualAnimalList;
    }
    public int getDayCounter() {
        return dayCounter;
    }

    public Island getIsland() {
        return island;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public List<Plants> getPlantsList() {
        return plantsList;
    }
}
