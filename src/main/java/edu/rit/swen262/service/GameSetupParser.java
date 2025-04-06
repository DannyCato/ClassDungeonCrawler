package edu.rit.swen262.service;

import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.service.Action.SetPlayerAction;

/**
 * A class which parses the input of the user while setting up a new game,
 * mapping the inputted text to its corresponding {@link Action}
 * 
 * @author Victor Bovat
 */
public class GameSetupParser {
    private SetPlayerAction setPlayer;

    public GameSetupParser(SetPlayerAction setPlayer) {
        this.setPlayer = setPlayer;
    }

    public void setupPlayer(String nameText, String descriptionText) {
        // not enough detail provided, do nothing/fall back on defaults
        if (nameText.isBlank() || descriptionText.isBlank()) {
            return;
        }

        PlayerCharacter newPlayer = new PlayerCharacter(nameText, descriptionText);

        this.setPlayer.setPlayerData(newPlayer);

        setPlayer.performAction();
    }
}
