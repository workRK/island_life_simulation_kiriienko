package com.javarush.kiriienko.config;

public class IslandConfig {

    private int islandHeight;
    private int islandWidth;
    private int daysOfSimulation;
    private int daysForAnimalDecomposition;
    private double dailyAnimalConsumptionCoefficient;
    private double startupSaturationCoefficient;
    private int startupAnimalGenerationOnCell;

    public int getIslandHeight() {
        return islandHeight;
    }

    public void setIslandHeight(int islandHeight) {
        this.islandHeight = islandHeight;
    }

    public int getIslandWidth() {
        return islandWidth;
    }

    public void setIslandWidth(int islandWidth) {
        this.islandWidth = islandWidth;
    }

    public int getDaysOfSimulation() {
        return daysOfSimulation;
    }

    public void setDaysOfSimulation(int daysOfSimulation) {
        this.daysOfSimulation = daysOfSimulation;
    }

    public int getDaysForAnimalDecomposition() {
        return daysForAnimalDecomposition;
    }

    public void setDaysForAnimalDecomposition(int daysForAnimalDecomposition) {
        this.daysForAnimalDecomposition = daysForAnimalDecomposition;
    }

    public double getDailyAnimalConsumptionCoefficient() {
        return dailyAnimalConsumptionCoefficient;
    }

    public void setDailyAnimalConsumptionCoefficient(double dailyAnimalConsumptionCoefficient) {
        this.dailyAnimalConsumptionCoefficient = dailyAnimalConsumptionCoefficient;
    }

    public double getStartupSaturationCoefficient() {
        return startupSaturationCoefficient;
    }

    public void setStartupSaturationCoefficient(double startupSaturationCoefficient) {
        this.startupSaturationCoefficient = startupSaturationCoefficient;
    }

    public int getStartupAnimalGenerationOnCell() {
        return startupAnimalGenerationOnCell;
    }

    public void setStartupAnimalGenerationOnCell(int startupAnimalGenerationOnCell) {
        this.startupAnimalGenerationOnCell = startupAnimalGenerationOnCell;
    }
}
