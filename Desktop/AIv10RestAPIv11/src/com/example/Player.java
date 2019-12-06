
package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * stores the player data
 * 
 */
public class Player {

    /**
     * the color of the player
     * 
     */
    @SerializedName("color")
    @Expose
    private Player.Color color;
    /**
     * Player Identifier
     * 
     */
    @SerializedName("id")
    @Expose
    private String id;
    /**
     * stores x and y coordinates and the state of the point
     * 
     */
    @SerializedName("headPosition")
    @Expose
    private HeadPosition headPosition;
    /**
     * the direction that the player was moving according to the last turn
     * 
     */
    @SerializedName("direction")
    @Expose
    private Player.Direction direction;
    /**
     * the line that the player leaves behind
     * 
     */
    @SerializedName("tracer")
    @Expose
    private List<Tracer_> tracer = null;
    /**
     * the number of turbo powerups
     * 
     */
    @SerializedName("turboAmount")
    @Expose
    private Integer turboAmount;
    /**
     * the number of kills made by the player
     * 
     */
    @SerializedName("numberOfKills")
    @Expose
    private Integer numberOfKills;
    /**
     * the time elapsed so far
     * 
     */
    @SerializedName("timeElapsed")
    @Expose
    private Double timeElapsed;
    /**
     * the length of player in the game
     * 
     */
    @SerializedName("length")
    @Expose
    private Integer length;
    /**
     * The score defined by the predefined formula
     * 
     */
    @SerializedName("score")
    @Expose
    private Double score;
    /**
     * Players is alive or not
     * 
     */
    @SerializedName("isAlive")
    @Expose
    private Boolean isAlive;

    /**
     * the color of the player
     * 
     */
    public Player.Color getColor() {
        return color;
    }

    /**
     * the color of the player
     * 
     */
    public void setColor(Player.Color color) {
        this.color = color;
    }

    /**
     * Player Identifier
     * 
     */
    public String getId() {
        return id;
    }

    /**
     * Player Identifier
     * 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * stores x and y coordinates and the state of the point
     * 
     */
    public HeadPosition getHeadPosition() {
        return headPosition;
    }

    /**
     * stores x and y coordinates and the state of the point
     * 
     */
    public void setHeadPosition(HeadPosition headPosition) {
        this.headPosition = headPosition;
    }

    /**
     * the direction that the player was moving according to the last turn
     * 
     */
    public Player.Direction getDirection() {
        return direction;
    }

    /**
     * the direction that the player was moving according to the last turn
     * 
     */
    public void setDirection(Player.Direction direction) {
        this.direction = direction;
    }

    /**
     * the line that the player leaves behind
     * 
     */
    public List<Tracer_> getTracer() {
        return tracer;
    }

    /**
     * the line that the player leaves behind
     * 
     */
    public void setTracer(List<Tracer_> tracer) {
        this.tracer = tracer;
    }

    /**
     * the number of turbo powerups
     * 
     */
    public Integer getTurboAmount() {
        return turboAmount;
    }

    /**
     * the number of turbo powerups
     * 
     */
    public void setTurboAmount(Integer turboAmount) {
        this.turboAmount = turboAmount;
    }

    /**
     * the number of kills made by the player
     * 
     */
    public Integer getNumberOfKills() {
        return numberOfKills;
    }

    /**
     * the number of kills made by the player
     * 
     */
    public void setNumberOfKills(Integer numberOfKills) {
        this.numberOfKills = numberOfKills;
    }

    /**
     * the time elapsed so far
     * 
     */
    public Double getTimeElapsed() {
        return timeElapsed;
    }

    /**
     * the time elapsed so far
     * 
     */
    public void setTimeElapsed(Double timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    /**
     * the length of player in the game
     * 
     */
    public Integer getLength() {
        return length;
    }

    /**
     * the length of player in the game
     * 
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * The score defined by the predefined formula
     * 
     */
    public Double getScore() {
        return score;
    }

    /**
     * The score defined by the predefined formula
     * 
     */
    public void setScore(Double score) {
        this.score = score;
    }

    /**
     * Players is alive or not
     * 
     */
    public Boolean getIsAlive() {
        return isAlive;
    }

    /**
     * Players is alive or not
     * 
     */
    public void setIsAlive(Boolean isAlive) {
        this.isAlive = isAlive;
    }

    public enum Color {

        @SerializedName("white")
        WHITE("white"),
        @SerializedName("yellow")
        YELLOW("yellow"),
        @SerializedName("orange")
        ORANGE("orange"),
        @SerializedName("red")
        RED("red"),
        @SerializedName("pink")
        PINK("pink"),
        @SerializedName("purple")
        PURPLE("purple"),
        @SerializedName("blue")
        BLUE("blue"),
        @SerializedName("green")
        GREEN("green"),
        @SerializedName("brown")
        BROWN("brown"),
        @SerializedName("grey")
        GREY("grey"),
        @SerializedName("black")
        BLACK("black");
        private final String value;
        private final static Map<String, Player.Color> CONSTANTS = new HashMap<String, Player.Color>();

        static {
            for (Player.Color c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Color(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static Player.Color fromValue(String value) {
            Player.Color constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public enum Direction {

        @SerializedName("up")
        UP("up"),
        @SerializedName("down")
        DOWN("down"),
        @SerializedName("left")
        LEFT("left"),
        @SerializedName("right")
        RIGHT("right");
        private final String value;
        private final static Map<String, Player.Direction> CONSTANTS = new HashMap<String, Player.Direction>();

        static {
            for (Player.Direction c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Direction(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static Player.Direction fromValue(String value) {
            Player.Direction constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
