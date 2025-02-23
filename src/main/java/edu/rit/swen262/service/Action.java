package edu.rit.swen262.service;

/**
 * defines the interface for an Action that can be performed by
 * the User
 * 
 * @author Victor Bovat, Philip Rubbo
 */
public interface Action {
    /**
     * executes the single action contained within an {@link Action} object
     * upon the {@link GameState}
     */
    public void performAction();
}
