package com.javarush.kiriienko.entity.map;

import com.javarush.kiriienko.abstraction.Eatable;

import java.util.List;

public class Cell {

    private final int xCoordinate;
    private final int yCoordinate;
    private final Island island;
    private List<Eatable> eatbleList;

    public Cell(int xCoordinate, int yCoordinate, Island island) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.island = island;
    }

    public synchronized List<Eatable> getEatableList() {
        return eatbleList;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setEatableList(List<Eatable> eatbleList) {
        this.eatbleList = eatbleList;
    }

    public Island getIsland() {
        return island;
    }
}
