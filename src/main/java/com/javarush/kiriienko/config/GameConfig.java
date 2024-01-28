package com.javarush.kiriienko.config;

import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class GameConfig {

    public static final String EATING_PROBABILITIES_CONFIG_FILE_PATH = "/eatingProbabilities.json";
    public static final EatingProbabilityConfig EATING_PROBABILITY_CONFIG = initEatingProbablitiesConfig();

    public static final String ANIMAL_CONFIG_FILE_PATH = "/animalConfig.json";
    public static final AnimalConfig ANIMAL_CONFIG = initAnimalConfig();

    public static final String ISLAND_CONFIG_FILE_PATH = "/islandConfig.json";
    public static final IslandConfig ISLAND_CONFIG = initIslandConfig();

    private static EatingProbabilityConfig initEatingProbablitiesConfig() {
        try {
            URL resource = GameConfig.class.getResource(EATING_PROBABILITIES_CONFIG_FILE_PATH);
            File file = Paths.get(resource.toURI()).toFile();
            JsonMapper jsonMapper = new JsonMapper();
            return jsonMapper.readValue(file, EatingProbabilityConfig.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static AnimalConfig initAnimalConfig() {
        try {
            URL resource = GameConfig.class.getResource(ANIMAL_CONFIG_FILE_PATH);
            File file = Paths.get(resource.toURI()).toFile();
            JsonMapper jsonMapper = new JsonMapper();
            return jsonMapper.readValue(file, AnimalConfig.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static IslandConfig initIslandConfig() {
        try {
            URL resource = GameConfig.class.getResource(ISLAND_CONFIG_FILE_PATH);
            File file = Paths.get(resource.toURI()).toFile();
            JsonMapper jsonMapper = new JsonMapper();
            return jsonMapper.readValue(file, IslandConfig.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <C> C initConfig(Class<C> clazz, String pathtoFile) {
        try {
            URL resource = clazz.getResource(pathtoFile);
            File file = Paths.get(resource.toURI()).toFile();
            JsonMapper jsonMapper = new JsonMapper();
            return jsonMapper.readValue(file, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
