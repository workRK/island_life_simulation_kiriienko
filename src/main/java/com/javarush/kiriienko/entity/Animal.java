package com.javarush.kiriienko.entity;

import com.javarush.kiriienko.abstraction.Eatable;
import com.javarush.kiriienko.animal_factory.AnimalFactory;
import com.javarush.kiriienko.animal_factory.AnimalType;
import com.javarush.kiriienko.animal_factory.Gender;
import com.javarush.kiriienko.entity.map.Cell;
import com.javarush.kiriienko.entity.plants.Plants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class Animal implements Eatable, Runnable {

    private double weight;
    private final int maxCellPopulation;
    private int speed;
    private final double maxSaturation;

    private volatile double saturation;
    private boolean isDead = false;
    private Cell cell;
    private final int offspring;
    private final Gender gender;
    int daysBeforeDecay;

    public Animal(double weight, int maxCellPopulation, int speed, double maxSaturation, Cell cell,
                  int offspring, Gender gender, double saturation, int daysBeforeDecay) {
        this.weight = weight;
        this.maxCellPopulation = maxCellPopulation;
        this.speed = speed;
        this.maxSaturation = maxSaturation;
        this.cell = cell;
        this.offspring = offspring;
        this.gender = gender;
        this.saturation = saturation;
        this.daysBeforeDecay = daysBeforeDecay;
    }

    @Override
    public void run() {
        live();
    }

    public void live() {
        move();
        eat();
        reproduce();
    }

    private void move() {
        if (saturation == maxSaturation || speed == 0) {
            return;
        }

        Cell destinationCell = findFood();

        if (cell == destinationCell) {
            return;
        }

        if (speed == 0) {
            return;
        }

        cell.getEatbleList().remove(this);

        while (cell != destinationCell && speed > 0) {

            if (cell.getxCoordinate() == destinationCell.getxCoordinate()) {
                moveYAxis(destinationCell);
            } else if (cell.getyCoordinate() == destinationCell.getyCoordinate()) {
                moveXAxis(destinationCell);
            } else {
                moveXAxis(destinationCell);
                moveYAxis(destinationCell);
            }
            speed--;
        }
        cell.getEatbleList().add(this);
    }

    private void eat() {
        if (saturation == maxSaturation) {
            return;
        }
        Eatable food;
        List<Eatable> eatables = findDiet(cell);
        if (eatables.size() == 0) {
            return;
        } else {
            while (eatables.stream().filter(eatable -> eatable.satiate() > 0).toList().size() > 0 &&
                    saturation < maxSaturation) {
                food = eatables.stream().findAny().get();
                double consumed = consume(food);
                eatables.remove(food);
                food.wasSatiated(consumed);
            }

        }

    }

    private void reproduce() {
        if (gender == Gender.FEMALE && saturation == maxSaturation) {
            List<Animal> population = cell.getEatbleList().stream()
                    .filter(eatable -> eatable instanceof Animal)
                    .map(Animal.class::cast)
                    .filter(animal -> !animal.isDead)
                    .filter(animal -> animal.getAnimalType() == this.getAnimalType())
                    .toList();
            if (population.size() < maxCellPopulation) {
                Animal partner = population.stream()
                        .filter(animal -> animal.getAnimalType() == this.getAnimalType())
                        .filter(animal -> animal.getGender() != this.gender)
                        .findFirst()
                        .orElse(null);
                if (partner != null) {
                    Random random = new Random();
                    for (int i = 0; i < random.nextInt(offspring); i++) {
                        cell.getEatbleList().add(AnimalFactory.createAnimalByType(cell, getAnimalType()));
                    }
                }
            }
        }
    }

    private Cell findFood() {
        Cell destinationCell = cell;
        if (findDiet(destinationCell).size() == 0) {
            int xCoordinate = cell.getxCoordinate();
            int yCoordinate = cell.getyCoordinate();
            Cell[][] islandMap = cell.getIsland().getIslandMap();

            List<Cell> allCellsList = new ArrayList<>();
            for (Cell[] cells : islandMap) {
                allCellsList.addAll(Arrays.asList(cells));
            }
            List<Cell> cellsWithFood = new ArrayList<>();
            for (Cell currentCell : allCellsList) {
                List<Eatable> eatables = findDiet(currentCell);
                if (eatables.size() > 0) {
                    cellsWithFood.add(currentCell);
                }
            }
            if (cellsWithFood.size() > 0) {
                int distance = Integer.MAX_VALUE;
                for (Cell currentCell : cellsWithFood) {
                    int distToCurrentCell = Math.abs(currentCell.getxCoordinate() - xCoordinate) +
                            Math.abs(currentCell.getyCoordinate() - yCoordinate);
                    if (distToCurrentCell < distance) {
                        distance = distToCurrentCell;
                        destinationCell = currentCell;
                    }
                }
            }
        }
        return destinationCell;
    }

    protected List<Eatable> findDiet(Cell currentCell) {
        List<Eatable> diet;
        List<Eatable> eatables = List.copyOf(currentCell.getEatbleList());

        if (AnimalType.getPredatorsType().contains(this.getAnimalType())) {
            diet = eatables.stream()
                    .filter(eatable -> eatable instanceof Animal)
                    .filter(eatable -> eatable.satiate() > 0)
                    .collect(Collectors.toList());
        } else {
            diet = eatables.stream()
                    .filter(eatable -> eatable instanceof Plants)
                    .filter(eatable -> eatable.satiate() > 0)
                    .collect(Collectors.toList());
        }
        return diet;
    }

    private void moveYAxis(Cell destinationCell) {
        if (cell.getyCoordinate() > destinationCell.getyCoordinate()) {
            cell = cell.getIsland().getIslandMap()[cell.getxCoordinate()][cell.getyCoordinate() - 1];
        } else {
            cell = cell.getIsland().getIslandMap()[cell.getxCoordinate()][cell.getyCoordinate() + 1];
        }
    }

    private void moveXAxis(Cell destinationCell) {
        if (cell.getxCoordinate() > destinationCell.getxCoordinate()) {
            cell = cell.getIsland().getIslandMap()[cell.getxCoordinate() - 1][cell.getyCoordinate()];
        } else {
            cell = cell.getIsland().getIslandMap()[cell.getxCoordinate() + 1][cell.getyCoordinate()];
        }
    }

    private double consume(Eatable food) {
        double readyToConsume = maxSaturation - saturation;
        double availableSatuation = food.satiate();
        if (food.satiate() <= 0) {
            return 0.0;
        }
        if (readyToConsume >= availableSatuation) {
            saturation = saturation + availableSatuation;
            return availableSatuation;
        } else {
            saturation = saturation + readyToConsume;
            return readyToConsume;
        }
    }

    public void die() {
        isDead = true;
    }

    @Override
    public synchronized double satiate() {
        return weight;
    }

    @Override
    public synchronized void wasSatiated(double weight) {
        this.weight = this.weight - weight;
        isDead = true;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMaxCellPopulation() {
        return maxCellPopulation;
    }

    public boolean isDead() {
        return isDead;
    }

    public AnimalType getAnimalType() {
        return null;
    }

    public Gender getGender() {
        return gender;
    }

    public double getSaturation() {
        return saturation;
    }

    public void setSaturation(double saturation) {
        this.saturation = saturation;
    }

    public double getMaxSaturation() {
        return maxSaturation;
    }

    public int getDaysBeforeDecay() {
        return daysBeforeDecay;
    }

    public void setDaysBeforeDecay(int daysBeforeDecay) {
        this.daysBeforeDecay = daysBeforeDecay;
    }

    public Cell getCell() {
        return cell;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
