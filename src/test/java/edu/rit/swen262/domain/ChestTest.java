package edu.rit.swen262.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ChestTest {

    @Test
    public void test_GenerateNewChest() {

        CreateItemFactory factory = new CreateItemFactory();
        Chest chest = new Chest(new ArrayList<>(), 6, "CHEST.");
        chest.generateChest(factory);

        assertNotNull(chest);

    }
    
}
