package com.javarush.kiriienko.entity.map;

public class Island {

    private final Cell[][] islandMap;

    public Island(int height, int width) {
        islandMap = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                islandMap[i][j] = new Cell(i, j, this);
            }
        }
    }

    public Cell[][] getIslandMap() {
        return islandMap;
    }
}
