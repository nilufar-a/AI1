
package com.example;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * stores x and y coordinates and the state of the point
 * 
 */
public class HeadPosition {

    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("x")
    @Expose
    private Integer x;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("y")
    @Expose
    private Integer y;
    @SerializedName("state")
    @Expose
    private HeadPosition.State state;

    /**
     * 
     * (Required)
     * 
     */
    public Integer getX() {
        return x;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * 
     * (Required)
     * 
     */
    public Integer getY() {
        return y;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setY(Integer y) {
        this.y = y;
    }

    public HeadPosition.State getState() {
        return state;
    }

    public void setState(HeadPosition.State state) {
        this.state = state;
    }

    public enum State {

        @SerializedName("empty")
        EMPTY("empty"),
        @SerializedName("obstacle")
        OBSTACLE("obstacle"),
        @SerializedName("powerup")
        POWERUP("powerup"),
        @SerializedName("tracer")
        TRACER("tracer");
        private final String value;
        private final static Map<String, HeadPosition.State> CONSTANTS = new HashMap<String, HeadPosition.State>();

        static {
            for (HeadPosition.State c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private State(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static HeadPosition.State fromValue(String value) {
            HeadPosition.State constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
