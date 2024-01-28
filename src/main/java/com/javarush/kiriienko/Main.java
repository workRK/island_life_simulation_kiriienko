package com.javarush.kiriienko;

import com.javarush.kiriienko.config.GameConfig;
import com.javarush.kiriienko.engine.Render;
import com.javarush.kiriienko.engine.Simulation;
import com.javarush.kiriienko.engine.WorldGenerator;
import com.javarush.kiriienko.entity.map.Island;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Main {
    public static void main(String[] args) {

        Island island = WorldGenerator.generate();
        Simulation simulation = new Simulation(island);
        Render render = new Render(simulation);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        scheduledExecutorService.scheduleAtFixedRate(render, 0, 1000, MILLISECONDS);
        scheduledExecutorService.scheduleAtFixedRate(simulation, 0, 1000, TimeUnit.MILLISECONDS);

        while (!scheduledExecutorService.isShutdown()){
            if (simulation.getDayCounter() > GameConfig.ISLAND_CONFIG.getDaysOfSimulation()){
                scheduledExecutorService.shutdown();
                simulation.getExecutorService().shutdown();
            }
        }
    }
}