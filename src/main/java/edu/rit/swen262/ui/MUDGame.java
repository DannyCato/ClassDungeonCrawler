package edu.rit.swen262.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties.Simple;
import org.w3c.dom.Text;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.graphics.Theme;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.service.GameObserver;
import edu.rit.swen262.service.GameState;

public class MUDGame implements GameObserver {
    private GameState gameState;

    /**
     * {@inheritDoc}
     */
    public void update(GameState g) {
        this.gameState = g;
        System.out.println("client notified! game state has been updated");
    }

    public void initialize() {
        PlayerCharacter p = new PlayerCharacter("Bobert", "incredibly underprepared for this dungeon life");
        GameState g = new GameState(p);
        this.gameState = g;
    }

    public void start() {
        this.initialize();
        System.out.println("game initialized!");

        this.drawUI();
    }

    public void drawUI() {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = null;

        try {
            // create screen component
            screen = terminalFactory.createScreen();
            screen.startScreen();

            final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
            
            // set tui color scheme
            Theme theme = SimpleTheme.makeTheme(
                true,               
                TextColor.ANSI.WHITE,             // normal foreground
                TextColor.ANSI.BLACK,             // normal background
                TextColor.ANSI.BLACK,             // editable foreground
                TextColor.ANSI.GREEN,              // editable background
                TextColor.ANSI.WHITE,             // Focused foreground
                TextColor.ANSI.CYAN_BRIGHT,       // Focused background (highlight)
                TextColor.ANSI.BLACK              // GUI background
            );

            //textGUI.setTheme(theme);

            // set no borders/shadow decorations for panels
            Window.Hint[] windowHints = new Window.Hint[] {
                //Window.Hint.NO_DECORATIONS,
                //Window.Hint.NO_POST_RENDERING,
                Window.Hint.FULL_SCREEN};

            final Window window = new BasicWindow("MUD Game");
            window.setHints(Arrays.asList(windowHints));

            // create panel container
            Panel contentPanel = new Panel(new GridLayout(1));
            GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
            gridLayout.setVerticalSpacing(1);

            // create 2-column panel displaying turn info (number/time)
            Panel turnPanel = new Panel(new GridLayout(2));
            Label turnLabel = new Label("Turn #: 0");
            Label timeLabel = new Label("Time: Day").setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1));

            turnPanel.addComponent(turnLabel);
            turnPanel.addComponent(timeLabel);

            // create panel displaying map
            Panel mapPanel = new Panel(new GridLayout(2));
            Label mapDisplay = new Label("|□□|" + "\n|□□|");
            mapPanel.addComponent(mapDisplay);

            // create panel displaying game status update messages
            Panel statusPanel = new Panel(new GridLayout(2));
            Label statusDisplay = new Label("something happened.");
            statusPanel.addComponent(statusDisplay);

            // create panel recieving input (spans entire screen)
            Panel inputPanel = new Panel(new GridLayout(1));
            TextBox userInput = new TextBox().setLayoutData(GridLayout.createHorizontallyFilledLayoutData());
            inputPanel.addComponent(userInput);

            // add child panels to container in top-down display order
            contentPanel.addComponent(turnPanel);
            contentPanel.addComponent(mapPanel);
            contentPanel.addComponent(statusPanel);

            // align input box to bottom of screen
            contentPanel.addComponent(inputPanel.setLayoutData(
                        GridLayout.createLayoutData(GridLayout.Alignment.FILL, GridLayout.Alignment.END)));

            window.setComponent(contentPanel.setLayoutData(
                GridLayout.createLayoutData(GridLayout.Alignment.CENTER, GridLayout.Alignment.CENTER)));

            //window.setFixedSize(terminalSize);

            textGUI.addWindowAndWait(window);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(screen != null) {
                try {
                    screen.close();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
