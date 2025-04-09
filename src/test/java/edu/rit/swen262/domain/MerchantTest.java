package edu.rit.swen262.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

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

        Merchant ItemGenerator = new Merchant("Mock");
        Merchant MockMerchant = new Merchant(ItemGenerator.getShopItems(), new MerchantOpened(), "Mock", true);
        PlayerCharacter Mockplayer = new PlayerCharacter("Mock", "Mock");

        String result = MockMerchant.purchaseItem(0, Mockplayer, MockcurrentRoom);
        assertEquals("Shop is closed.", result);
    }

    @Test
    public void test_MerchantOpen() {
        Room MockcurrentRoom = new Room(1, 1);
        Tile MockTile = new Tile(new Occupant() {
            @Override
            public RenderRepresentation render() {
                return RenderRepresentation.CHARACTER;
            }

            @Override
            public String description() {
                return "IM A MOCK OBJECT";
            }
        });

        MockcurrentRoom.addTile(MockTile);

        Merchant MockMerchant = new Merchant("Mock");
        PlayerCharacter Mockplayer = new PlayerCharacter("Mock", "Mock");
        Mockplayer.gold = new Gold(99999);

        MockMerchant.purchaseItem(0, Mockplayer, MockcurrentRoom);

        Inventory playerInventory = Mockplayer.getInventory();
        List<Bag> playerBags = playerInventory.getBags();
        Bag aPlayerBag = playerBags.get(0);
        List<Item> bagContents = aPlayerBag.getItems();
        Gold playerGold = Mockplayer.gold;

        assertEquals(1, bagContents.size());
        assertNotEquals(playerGold.getCount(), new Gold(99999).getCount());
    }

    @Test
    public void test_MerchantOpen_Sell() {
        Room MockcurrentRoom = new Room(1, 1);
        Tile MockTile = new Tile(new Occupant() {
            @Override
            public RenderRepresentation render() {
                return RenderRepresentation.CHARACTER;
            }

            @Override
            public String description() {
                return "IM A MOCK OBJECT";
            }
        });

        MockcurrentRoom.addTile(MockTile);

        Merchant MockMerchant = new Merchant("Mock");
        PlayerCharacter Mockplayer = new PlayerCharacter("Mock", "Mock");
        Mockplayer.gold = new Gold(1000);
        Gold playerGold = Mockplayer.gold;
        int playerGoldBefore = playerGold.getCount();
        
        MockMerchant.purchaseItem(0, Mockplayer, MockcurrentRoom);

        Inventory playerInventory = Mockplayer.getInventory();
        List<Bag> playerBags = playerInventory.getBags();
        Bag aPlayerBag = playerBags.get(0);
        List<Item> bagContents = aPlayerBag.getItems();
        Item itemToSell = bagContents.get(0);

        MockMerchant.sellItem(itemToSell, Mockplayer, MockcurrentRoom);

        int playerGoldAfter = playerGold.getCount();

        assertEquals(0, bagContents.size());
        //System.out.println(playerGoldBefore);
        //System.out.println(playerGoldAfter);
        assertNotEquals(playerGoldAfter, playerGoldBefore);
    }
}
