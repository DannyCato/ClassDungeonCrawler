package edu.rit.swen262.service;

import java.util.ArrayList;
import java.util.List;

/**
 * A helper interface which holds all suboptions avaialble for applicable 
 * {@link Action concrete commands}, generated when the {@link KeyStrokeListener}
 * is parsing input
 */
public interface Menu {
    //key stroke listener here;

    public void handleInput();
}