
package com.example;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Current map of the game with the all obstacles, power-ups and player locations
 * 
 */
public class Map {

    /**
     * The width of the map
     * 
     */
    @SerializedName("width")
    @Expose
    private Integer width;
    /**
     * The height of the map
     * 
     */
    @SerializedName("height")
    @Expose
    private Integer height;
    /**
     * The Location of the obstacles
     * 
     */
    @SerializedName("obstacles")
    @Expose
    private List<Obstacle> obstacles = null;
    /**
     * The location of the powerups
     * 
     */
    @SerializedName("power-ups")
    @Expose
    private List<PowerUp> powerUps = null;
    /**
     * The locations of the tracers on the map
     * 
     */
    @SerializedName("tracers")
    @Expose
    private List<Tracer> tracers = null;
    /**
     * All of the points that are on the map with the information about the states stored inside the individual points
     * 
     */
    @SerializedName("allPointsOnMap")
    @Expose
    private List<AllPointsOnMap> allPointsOnMap = null;

    /**
     * The width of the map
     * 
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * The width of the map
     * 
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * The height of the map
     * 
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * The height of the map
     * 
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * The Location of the obstacles
     * 
     */
    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * The Location of the obstacles
     * 
     */
    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    /**
     * The location of the powerups
     * 
     */
    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    /**
     * The location of the powerups
     * 
     */
    public void setPowerUps(List<PowerUp> powerUps) {
        this.powerUps = powerUps;
    }

    /**
     * The locations of the tracers on the map
     * 
     */
    public List<Tracer> getTracers() {
        return tracers;
    }

    /**
     * The locations of the tracers on the map
     * 
     */
    public void setTracers(List<Tracer> tracers) {
        this.tracers = tracers;
    }

    /**
     * All of the points that are on the map with the information about the states stored inside the individual points
     * 
     */
    public List<AllPointsOnMap> getAllPointsOnMap() {
        return allPointsOnMap;
    }

    /**
     * All of the points that are on the map with the information about the states stored inside the individual points
     * 
     */
    public void setAllPointsOnMap(List<AllPointsOnMap> allPointsOnMap) {
        this.allPointsOnMap = allPointsOnMap;
    }

}
