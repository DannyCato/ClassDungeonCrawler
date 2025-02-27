package edu.rit.swen262.service;

/**
 * enumeration which contains all types of {@link GameEvent events}
 * which may occur within the game
 */
public enum GameEventType {
    DISPLAY_SUBMENU,
    MOVE_PLAYER,
    DAMAGE_TAKEN,
    TIME_CHANGED,
    QUIT_GAME
}
