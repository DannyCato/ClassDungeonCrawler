package edu.rit.swen262.ui;

import java.io.IOException;
import java.util.Arrays;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.graphics.Theme;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import edu.rit.swen262.service.GameEvent;
import edu.rit.swen262.service.GameEventType;
import edu.rit.swen262.service.GameObserver;
import edu.rit.swen262.service.GameState;

/**
 * The class responsible for rendering the current state of the MUD game to
 * the terminal utilizing the Lanterna Library. 
 * 
 * @author Victor Bovat
 */
public class MUDGame implements GameObserver {
    private Screen screen;
    /**
     * {@inheritDoc}
     */
    public void update(GameEvent event) {
        if (event.getType().equals(GameEventType.QUIT_GAME)) {
            try {
                this.screen.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     */
    public void initialize() {
        /* TO-DO: have this class instantiate all concrete commands +
        bind them to their receivers here
        (or offload that resposibility to the MUDApplication class? responsibilities 
        may need to be rebalanced here)
        */
    }

    /**
     * starts a new game, initializing all relevent objects then taking control
     * of the window to draw the starting game screen
     */
    public void start() {
        this.initialize();
        System.out.println("game initialized!");

        this.drawUI();
    }

    /**
     * takes control of the console and draws the main screen which displays all of the information currently
     * available to the player (map, turn number, etc.)
     */
    public void drawUI() {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        this.screen = null;

        try {
            // create screen component
            this.screen = terminalFactory.createScreen();
            this.screen.startScreen();

            final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(this.screen);
            
            // set tui color scheme
            Theme theme = SimpleTheme.makeTheme(
                true,               
                TextColor.ANSI.WHITE,             // normal foreground
                TextColor.ANSI.BLACK,             // normal background
                TextColor.ANSI.GREEN,             // editable foreground
                TextColor.ANSI.BLACK_BRIGHT,              // editable background
                TextColor.ANSI.GREEN_BRIGHT,             // Focused foreground
                TextColor.ANSI.BLACK_BRIGHT,       // Focused background (highlight)
                TextColor.ANSI.BLACK              // GUI background
            );

            textGUI.setTheme(theme);

            // set no shadow decorations for panels + full screen
            Window.Hint[] windowHints = new Window.Hint[] {
                //Window.Hint.NO_DECORATIONS,
                Window.Hint.NO_POST_RENDERING,
                Window.Hint.EXPANDED};

            final Window window = new BasicWindow("MUD Game");
            window.setHints(Arrays.asList(windowHints));

            // create panel container
            Panel contentPanel = new Panel(new GridLayout(1));
            GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
            gridLayout.setVerticalSpacing(1);

            // create 2-column panel displaying turn info (number/time)
            Panel turnPanel = new Panel(new GridLayout(2));
            GridLayout turnPanelLayout = (GridLayout) turnPanel.getLayoutManager();
            turnPanelLayout.setHorizontalSpacing(20);

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

            textGUI.addWindowAndWait(window);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // if control over the window has not already been yielded, do so
            if(this.screen != null) {
                try {
                    this.screen.close();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
