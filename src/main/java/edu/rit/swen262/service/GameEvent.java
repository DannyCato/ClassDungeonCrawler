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
     * adds a single {@link Object data value} to the event's data map
     * 
     * @param key String of the type of data that is being inputted
     * @param value the data {@link Object object} to be added
     */
    public void addData(String key, Object value) {
        this.data.put(key, value);
    }

    /**
     * fetches the event's type
     * 
     * @return a {@link GameEventType} value
     */
    public GameEventType getType() {
        return this.eventType;
    }

    /**
     * fetches a single {@link Object data value} from the Event
     * associated with the given key
     * 
     * @param key the String identifier for the data
     * @return Object that is stored with the associated key
     */
    public Object getData(String key) {
        return this.data.get(key);
    }
}
