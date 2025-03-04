package edu.rit.swen262.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import edu.rit.swen262.domain.TimePeriod;
import edu.rit.swen262.domain.DayTime;
import edu.rit.swen262.domain.NightTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TimePeriodTest {
    private GameState spyGameState;

    @BeforeEach
    void setupGameState() {
        this.spyGameState = spy(GameState.class);
    }

    @Test
    @DisplayName("Validate that")
    void handlePlayerTurnDayNormal() {
        TimePeriod day = new DayTime(this.spyGameState);

        day.handlePlayerTurn();

        verify(this.spyGameState).getTurnNumber();
    }
}
