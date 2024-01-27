package com.javarush.kiriienko.entity;

import com.javarush.kiriienko.abstraction.Eatable;
import com.javarush.kiriienko.animal_factory.Gender;
import com.javarush.kiriienko.entity.map.Cell;

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
                  int offspring, Gender gender, double saturation, int daysBeforeDecay){
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


}
