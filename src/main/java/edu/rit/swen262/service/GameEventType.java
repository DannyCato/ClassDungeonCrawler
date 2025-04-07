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
    FINISH_TURN,
    ADD_ITEM,
    USE_ITEM,
    UPDATE_MAP,
    QUIT_GAME
}
