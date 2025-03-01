package edu.rit.swen262.service;

/**
 * enumeration which contains all types of {@link GameEvent events}
 * which may occur within the game
 */
public enum GameEventType {
    DISPLAY_SUBMENU,
    MOVE_PLAYER,
    TAKE_DAMAGE,
    CHANGE_TIME,
    USE_ITEM,
    QUIT_GAME
}
