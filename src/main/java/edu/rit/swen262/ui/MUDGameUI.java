package edu.rit.swen262.ui;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.graphics.Theme;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import ch.qos.logback.core.net.QueueFactory;
import edu.rit.swen262.service.GameEvent;
import edu.rit.swen262.service.GameEventType;
import edu.rit.swen262.service.GameObserver;
import edu.rit.swen262.service.GameState;
import edu.rit.swen262.service.InputParser;
import edu.rit.swen262.service.Action.Action;

/**
 * The class responsible for rendering the current state of the MUD game to
 * the terminal utilizing the Lanterna Library. 
 * 
 * @author Victor Bovat
 */
public class MUDGameUI implements GameObserver {
    private InputParser inputParser;
    private Screen screen;
    private Window window;
    private Label turnDisplay;
    private Label timeDisplay;
    private Label menuDisplay;
    private Queue<String> statusMsgs;
    private Label statusDisplay;

    public MUDGameUI(InputParser inputParser) {
        this.inputParser = inputParser;
        this.statusMsgs = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    public void update(GameEvent event) {
        switch (event.getType()) {
            case GameEventType.DISPLAY_SUBMENU:
                //update status panel w/ menu options
                this.redrawMenu((String) event.getData("menuText"));
                break;
            case GameEventType.MOVE_PLAYER:
                //this.statusMsgs.offer();
                this.redrawStatus("you moved.");
                this.redrawMenuDefault();
                break;
            case GameEventType.FINISH_TURN:
                this.redrawTurn(event.getData("turnNumber").toString());
                break;
            case GameEventType.CHANGE_TIME:
                this.redrawStatus(null);
                this.redrawTime(event.getData("time").toString());
                break;
            case GameEventType.TAKE_DAMAGE:
                this.redrawStatus("something took damage.");
                this.redrawMenuDefault();
                break;
            case GameEventType.QUIT_GAME:
                this.stop();
                break;
            default:
                break;
        }
    }

    /**
     * starts a new game, initializing all relevent objects then taking control
     * of the window to draw the starting game screen
     */
    public void start() {
        System.out.println("game initialized!");

        this.drawUI();
    }

    /**
     * exits private mode (clearing away the text window) and yields control
     * back to the main terminal, then exits on success code 0
     */
    public void stop() {
        try {
            this.screen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    /**
     * takes control of the console and draws the main screen which displays all of the information currently
     * available to the player (map, turn number, etc.)
     */
    private void drawUI() {
        this.screen = null;
        this.turnDisplay = null;
        this.timeDisplay = null;
        this.menuDisplay = null;
        this.statusDisplay = null;

        try {
            // create screen component
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
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

            //textGUI.setTheme(theme);

            // set no shadow decorations for panels + full screen
            Window.Hint[] windowHints = new Window.Hint[] {
                //Window.Hint.NO_DECORATIONS,
                //Window.Hint.NO_POST_RENDERING,
                Window.Hint.EXPANDED};

            final Window window = new BasicWindow("MUD Game");
            window.setHints(Arrays.asList(windowHints));
            window.setStrictFocusChange(true);

            // create 2-column panel container
            Panel contentPanel = new Panel(new GridLayout(2));

            // create 1-column panel sub-container for turn/map/input
            Panel controlPanel = new Panel(new GridLayout(1));
            GridLayout gridLayout = (GridLayout) controlPanel.getLayoutManager();
            gridLayout.setVerticalSpacing(1);

            // create panel displaying game status update messages
            Panel statusPanel = new Panel(new GridLayout(2));
            this.statusDisplay = new Label("Event Log: \n\n");
            statusPanel.addComponent(this.statusDisplay);

            // create 2-column panel displaying turn info (number/time)
            Panel turnPanel = new Panel(new GridLayout(2));
            GridLayout turnPanelLayout = (GridLayout) turnPanel.getLayoutManager();
            turnPanelLayout.setHorizontalSpacing(20);

            this.turnDisplay = new Label("Turn #: 1");
            this.timeDisplay = new Label("Time: Day").setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1));

            turnPanel.addComponent(this.turnDisplay);
            turnPanel.addComponent(this.timeDisplay);

            // create panel displaying map
            Panel mapPanel = new Panel(new GridLayout(2));
            Label mapDisplay = new Label("|□□|" + "\n|□□|");
            mapPanel.addComponent(mapDisplay);

            // create panel recieving input (spans entire screen)
            Panel inputPanel = new Panel(new GridLayout(1));
            TextBox userInput = new TextBox().setLayoutData(GridLayout.createHorizontallyFilledLayoutData());
            
            // create panel displaying current menu of possible actions to select
            Panel menuPanel = new Panel(new GridLayout(1));
            this.menuDisplay = new Label("");
            this.redrawMenuDefault();
            menuPanel.addComponent(this.menuDisplay);

            /* TextBox won't clear text + update statusDisplay unless 
             * submitted through a button, so current cmd entry is
             * <keystroke> -> enter (to focus on button) -> enter (to "click" button/submit input)
            */
            Button submitButton = new Button("Submit", () -> {
                String inputString = userInput.getText();
                if (!inputString.isBlank()) {
                    //statusDisplay.setText(inputString);
                    this.inputParser.receivedInput(userInput.getText());
                }
                userInput.setText(""); // Clear the text
                textGUI.setActiveWindow(window);
                userInput.takeFocus();         // refocus user input box
            });
            
            inputPanel.addComponent(userInput);
            inputPanel.addComponent(submitButton);

            // add child panels to container in top-down display order
            controlPanel.addComponent(turnPanel);
            controlPanel.addComponent(mapPanel);

            // align input box to bottom of screen
            controlPanel.addComponent(inputPanel.setLayoutData(
                        GridLayout.createLayoutData(GridLayout.Alignment.FILL, GridLayout.Alignment.END)));

            controlPanel.addComponent(menuPanel);

            contentPanel.addComponent(controlPanel);
            contentPanel.addComponent(statusPanel);

            window.setComponent(contentPanel.setLayoutData(
                GridLayout.createLayoutData(GridLayout.Alignment.CENTER, GridLayout.Alignment.CENTER)));

            textGUI.addWindowAndWait(window);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // if control over the window has not already been yielded, do so
            if(this.screen != null) {
                this.stop();
            }
        }
    }

    /**
     * updates the text displayed in the menu panel to display the user's
     * current action options
     * 
     * @param displayText the new text to display on the menu panel
     */
    private void redrawMenu(String displayText) {
        this.menuDisplay.setText(displayText);
    }

    /**
     * updates the menu display to the default list of actions
     */
    private void redrawMenuDefault() {
        String defaultOptions = """
                [m] Move
                [a] Attack
                [i] Open inventory
                [q] Quit
                """;
        this.redrawMenu(defaultOptions);
    }

    /**
     * updates the text displayed in the status panel to display the response to
     * the most recent action(s) taken
     * 
     * @param displayText the new text to display on the status panel
     */
    private void redrawStatus(String displayText) {
        this.statusDisplay.setText("Event Log: \n\n" + displayText);
    }

    /**
     * updates the text displayed in the turn panel to display the correct turn number
     * 
     * @param turnNumber the turn number to be displayed on the turn panel's turn field
     */
    private void redrawTurn(String turnNumber) {
        this.turnDisplay.setText("Turn: " + turnNumber);
    }

    /**
     * updates the text displayed in the turn panel to display the correct time of day
     * 
     * @param displayText the new text to be displayed on the turn panel's time field
     */
    private void redrawTime(String displayText) {
        this.timeDisplay.setText("Time: " + displayText);
    }
}