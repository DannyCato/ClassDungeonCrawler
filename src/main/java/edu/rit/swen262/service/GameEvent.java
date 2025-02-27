package edu.rit.swen262.service;

import java.util.HashMap;

/**
 * encapsulates all relevent information regarding a single change that occurs
 * within an {@link IObservable}
 * 
 * @author Victor Bovat
 */
public class GameEvent {
    private GameEventType eventType;
    private HashMap<String, Object> data;

    public GameEvent(GameEventType eventType) {
        this.eventType = eventType;
        this.data = new HashMap<String, Object>();
    }

    /**
     * 
     * @param key
     * @param value
     */
    public void addData(String key, Object value) {
        this.data.put(key, value);
    }

    /**
     * 
     * @return a GameEventType enum value
     */
    public GameEventType getType() {
        return this.eventType;
    }

    /**
     * 
     * @param key 
     * @return Object that is stored with the associated key
     */
    public Object getData(String key) {
        return this.data.get(key);
    }
}
