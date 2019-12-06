
package com.example;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * stores x and y coordinates and the state of the point
 * 
 */
public class Tracer_ {

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
    private Tracer_.State state;

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

    public Tracer_.State getState() {
        return state;
    }

    public void setState(Tracer_.State state) {
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
        private final static Map<String, Tracer_.State> CONSTANTS = new HashMap<String, Tracer_.State>();

        static {
            for (Tracer_.State c: values()) {
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

        public static Tracer_.State fromValue(String value) {
            Tracer_.State constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
