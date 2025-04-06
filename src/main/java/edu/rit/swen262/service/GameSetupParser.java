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

    /**
     * creates a new GameSetupParser with the given {@link SetPlayerAction}
     * to manage starting a new game with the selected options
     * 
     * @param setPlayer binded command to update player data
     */
    public GameSetupParser(SetPlayerAction setPlayer) {
        this.setPlayer = setPlayer;
    }

    /**
     * updates the information of the player with the given name and description
     * 
     * @param nameText text inputted into the name field
     * @param descriptionText text inputted into the description field
     */
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
