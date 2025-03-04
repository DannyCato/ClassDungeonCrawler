package edu.rit.swen262.domain;

import edu.rit.swen262.service.GameState;

public class DayTime implements TimePeriod {
    private GameState gameState;

    /**
     * instantiates a new concrete time period representing the day time period
     * 
     * @param gameState the context object using this time period
     */
    public DayTime(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * {@inheritDoc}
     */
    public void handlePlayerTurn() {
        int turn = gameState.getTurnNumber();

        //if ten turns have passed since daytime started, change to night
        if (turn % 2 == 0) {
            gameState.setTime(new NightTime(this.gameState));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void handleApplyEnemyBuffs() {
        //TODO: add enemies to GameState's list of observes so TIME_CHANGE event can be sent
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Day";
    }
}
