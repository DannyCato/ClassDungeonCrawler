package edu.rit.swen262;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import edu.rit.swen262.domain.DirectionalVector;
import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.service.GameState;
import edu.rit.swen262.service.InputParser;
import edu.rit.swen262.service.MenuState;
import edu.rit.swen262.service.Action.Action;
import edu.rit.swen262.service.Action.AttackAction;
import edu.rit.swen262.service.Action.DisplayMenuAction;
import edu.rit.swen262.service.Action.DisplayMenuType;
import edu.rit.swen262.service.Action.MoveAction;
import edu.rit.swen262.service.Action.QuitGameAction;
import edu.rit.swen262.ui.MUDGameUI;

@SpringBootApplication
public class MUDApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(MUDApplication.class);

		builder.headless(false);

		ConfigurableApplicationContext context = builder.run(args);
	}

}

@Configuration
@Profile("!test")
class SampleCommandLineRunner implements CommandLineRunner {

	@Autowired
	SampleCommandLineRunner() {
		// TODO: don't
	}
	
	/**
	 * instantiates all concrete commands and binds them to the {@link InputParser}
	 * at run-time
	 * 
	 * @param gameState the current state of the game
	 * @return a nested hashmap connecting the menu state to a list of relevent commands to execute
	 */
	public HashMap<MenuState, HashMap<Character, Action>> bindCommands(GameState gameState) {
		HashMap<MenuState, HashMap<Character, Action>> keystrokes = new HashMap<>();

		HashMap<Character, Action> moveKeystrokes = new HashMap<>() {{
			put('1', new MoveAction(gameState, DirectionalVector.NORTH));
			put('2', new MoveAction(gameState, DirectionalVector.NORTHEAST));
			put('3', new MoveAction(gameState, DirectionalVector.EAST));
			put('4', new MoveAction(gameState, DirectionalVector.SOUTHEAST));
			put('5', new MoveAction(gameState, DirectionalVector.SOUTH));
			put('6', new MoveAction(gameState, DirectionalVector.SOUTHWEST));
			put('7', new MoveAction(gameState, DirectionalVector.WEST));
			put('8', new MoveAction(gameState, DirectionalVector.NORTHWEST));
		}};

		HashMap<Character, Action> attackKeystrokes = new HashMap<>() {{
			put('1', new AttackAction(gameState, DirectionalVector.NORTH));
			put('2', new AttackAction(gameState, DirectionalVector.NORTHEAST));
			put('3', new AttackAction(gameState, DirectionalVector.EAST));
			put('4', new AttackAction(gameState, DirectionalVector.SOUTHEAST));
			put('5', new AttackAction(gameState, DirectionalVector.SOUTH));
			put('6', new AttackAction(gameState, DirectionalVector.SOUTHWEST));
			put('7', new AttackAction(gameState, DirectionalVector.WEST));
			put('8', new AttackAction(gameState, DirectionalVector.NORTHWEST));
		}};

		/*inv command map should change based upon what's in the inventory?
		 AKA Bags/Individual Items

		 (generation of these maps may need to move to another class depending upon
		 integraton)
		 */
		HashMap<Character, Action> inventoryKeystrokes = new HashMap<>() {{
			// put('1', new UseItemAction(gameState, "North East"));
			// put('2', new UseItemAction(gameState, "North"));
		}};

		String moveMenuString = this.buildMenuString(moveKeystrokes);
		String attackMenuString = this.buildMenuString(attackKeystrokes);
		String inventoryMenuString = this.buildMenuString(inventoryKeystrokes);

		HashMap<Character, Action> defaultKeystrokes = new HashMap<>() {{
			put('q', new QuitGameAction(gameState));
			put('m', new DisplayMenuAction(gameState, DisplayMenuType.MOVE, moveMenuString));
			put('a', new DisplayMenuAction(gameState, DisplayMenuType.ATTACK, attackMenuString));
			put('i', new DisplayMenuAction(gameState, DisplayMenuType.INVENTORY, inventoryMenuString));
		}};

		keystrokes.put(MenuState.DEFAULT, defaultKeystrokes);
		keystrokes.put(MenuState.MOVE, moveKeystrokes);
		keystrokes.put(MenuState.ATTACK, attackKeystrokes);
		keystrokes.put(MenuState.INVENTORY, inventoryKeystrokes);

		return keystrokes;
	}

	/**
	 * helper method which produces a string representation of a hash map of
	 * {@link Action commands}, producing a list in the format of:
	 * '[n] <Action>' for every command and its associated number value
	 * 
	 * @param commandMap map which associates a set of {@link Action commands}
	 * with a different keystroke
	 * @return a String representation of the given command hashmap
	 */
	public String buildMenuString(HashMap<Character, Action> commandMap) {
        StringBuilder displayText = new StringBuilder();
        commandMap.forEach((key, value) -> 
			displayText.append(String.format("[%s] %s\n", key, value))
		);
		
		return displayText.toString();
	}

	
	@Override
	public void run(String... args) throws Exception {
		PlayerCharacter player = new PlayerCharacter("Bobert", "can lift at least 5 worms.");
		GameState gameState = new GameState(player);

		HashMap<MenuState, HashMap<Character, Action>> keystrokes = this.bindCommands(gameState);
		
		InputParser inputParser = new InputParser(keystrokes);
		MUDGameUI client = new MUDGameUI(inputParser);
		gameState.register(client);

		client.start();
	}
}