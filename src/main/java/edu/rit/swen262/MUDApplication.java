package edu.rit.swen262;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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
		System.out.println("Hello World! :D");
		MUDGame client = new MUDGame();
		client.start();
	}
}