package edu.rit.swen262.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ChestTest {

    @Test
    public void test_GenerateNewChest() {

        CreateItemFactory factory = new CreateItemFactory();
        Chest chest = new Chest(null, 0);
        chest.generateChest(factory);

        assertNotNull(chest);

    }
    
}
