package edu.rit.swen262;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.service.Action;
import edu.rit.swen262.service.GameState;
import edu.rit.swen262.service.KeystrokeListener;
import edu.rit.swen262.service.QuitGameAction;
import edu.rit.swen262.ui.MUDGame;

@SpringBootApplication
public class MUDApplication {

	public static void main(String[] args) {
		SpringApplication.run(MUDApplication.class, args);
	}

}

@Configuration
@Profile("!test")
class SampleCommandLineRunner implements CommandLineRunner {

	@Autowired
	SampleCommandLineRunner() {
		// TODO:
	}

	@Override
	public void run(String... args) throws Exception {
		PlayerCharacter player = new PlayerCharacter("Bobert", "can lift at least 5 worms.");
		GameState gameState = new GameState(player);

		Map<String, Action> keystrokes = new HashMap<String, Action>() {{
			put("q", new QuitGameAction(gameState));
		}};
		KeystrokeListener keystrokeListener = new KeystrokeListener(keystrokes);
		MUDGame client = new MUDGame(keystrokeListener);
		gameState.register(client);

		client.start();
	}
}