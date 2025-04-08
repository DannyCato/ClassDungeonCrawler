package edu.rit.swen262.domain;

import org.junit.jupiter.api.Test;

import edu.rit.swen262.domain.DungeonPiece.Room;
import edu.rit.swen262.domain.DungeonPiece.Tile;

public class MerchantTest {
    @Test
    public void test_MerchantClosed() {
        Room MockcurrentRoom = new Room(1, 1);
        Tile MockTile = new Tile(new Occupant() {
            @Override
            public RenderRepresentation render() {
                return RenderRepresentation.ENEMY;
            }

            @Override
            public String description() {
                return "IM A MOCK OBJECT";
            }
        });

        MockcurrentRoom.addTile(MockTile);

        Merchant MockMerchant = new Merchant("I sell stuff.");
        PlayerCharacter Mockplayer = new PlayerCharacter("Mock", "Mock");
    }

    @Test
    public void test_MerchantOpen() {

    }

    
}
