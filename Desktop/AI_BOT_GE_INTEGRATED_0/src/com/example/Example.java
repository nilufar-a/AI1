
package com.example;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * GameState
 * <p>
 * Current state of the game
 * 
 */
public class Example {

    /**
     * Time left until the next turn
     * 
     */
    @SerializedName("timeToUpdate")
    @Expose
    private Double timeToUpdate;
    /**
     * Current turn number
     * 
     */
    @SerializedName("turnCounter")
    @Expose
    private Integer turnCounter;
    /**
     * Is the game running or has it finished
     * 
     */
    @SerializedName("gameFinished")
    @Expose
    private Boolean gameFinished;
    /**
     * Current map of the game with the all obstacles, power-ups and player locations
     * 
     */
    @SerializedName("map")
    @Expose
    private Map map;
    /**
     * The player
     * 
     */
    @SerializedName("players")
    @Expose
    private List<Player> players = null;

    /**
     * Time left until the next turn
     * 
     */
    public Double getTimeToUpdate() {
        return timeToUpdate;
    }

    /**
     * Time left until the next turn
     * 
     */
    public void setTimeToUpdate(Double timeToUpdate) {
        this.timeToUpdate = timeToUpdate;
    }

    /**
     * Current turn number
     * 
     */
    public Integer getTurnCounter() {
        return turnCounter;
    }

    /**
     * Current turn number
     * 
     */
    public void setTurnCounter(Integer turnCounter) {
        this.turnCounter = turnCounter;
    }

    /**
     * Is the game running or has it finished
     * 
     */
    public Boolean getGameFinished() {
        return gameFinished;
    }

    /**
     * Is the game running or has it finished
     * 
     */
    public void setGameFinished(Boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    /**
     * Current map of the game with the all obstacles, power-ups and player locations
     * 
     */
    public Map getMap() {
        return map;
    }

    /**
     * Current map of the game with the all obstacles, power-ups and player locations
     * 
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * The player
     * 
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * The player
     * 
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
