package edu.rit.swen262.service;

/**
 * enumeration which contains all types of {@link GameEvent events}
 * which may occur within the game
 */
public enum GameEventType {
    MOVE_PLAYER,
    DAMAGE_TAKEN,
    TIME_CHANGED,
    INVENTORY_OPENED,
    QUIT_GAME
}
