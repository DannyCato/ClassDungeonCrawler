package edu.rit.swen262.ui;

import java.io.IOException;
import java.util.ArrayList;
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
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LayoutData;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorAutoCloseTrigger;

import ch.qos.logback.core.net.QueueFactory;
import edu.rit.swen262.service.GameEvent;
import edu.rit.swen262.service.GameEventType;
import edu.rit.swen262.service.GameObserver;
import edu.rit.swen262.service.GameSetupParser;
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
    // invokers
    private GameSetupParser setupParser;
    private InputParser inputParser;

    // functional display components
    private SwingTerminalFrame terminal;
    private WindowBasedTextGUI textGUI;
    private Screen screen;

    // data display elements
    private Label mapDisplay;
    private Label turnDisplay;
    private Label timeDisplay;
    private Label menuDisplay;
    private Queue<String> eventLogMsgs;
    private Label eventLogDisplay;

    /**
     * initiazes a new UI component which manages displaying all data and
     * interactions involved with a single instance of the MUD Game
     * 
     * @param setupParser invoker which manages inputs regarding game setup
     * @param inputParser invoker which manages inputs regarding gameplay
     */
    public MUDGameUI(GameSetupParser setupParser, InputParser inputParser) {
        this.setupParser = setupParser;
        this.inputParser = inputParser;
        //init map with placeholder to be replaced once startup is complete
        this.mapDisplay = new Label("|..|" + "\n|..|");
        this.eventLogMsgs = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    public void update(GameEvent event) {
        switch (event.getType()) {
            case GameEventType.DISPLAY_SUBMENU:
                //update status panel w/ menu options
                this.redrawMenu(event.getData("menuText").toString());
                break;
            case GameEventType.UPDATE_MAP:
                this.redrawMap(event.getData("currentRoom").toString());
                break;
            case GameEventType.MOVE_PLAYER:
                if (event.getData("direction") != null) {
                    this.eventLogMsgs.offer("You moved.");
                    this.redrawEventLog();
                }
                this.redrawMap(event.getData("currentRoom").toString());
                this.redrawMenuDefault();

                Object endGameData = event.getData("canEndGame");
                if (endGameData != null) {
                    boolean canEndGame = (Boolean) endGameData;
                    if (canEndGame) {
                        this.drawEndGameUI((String) event.getData("playerName"), 
                                           (String) event.getData("playerDescription"));
                    }
                }
                break;
            case GameEventType.FINISH_TURN:
                this.redrawTurn(event.getData("turnNumber").toString());
                break;
            case GameEventType.CHANGE_TIME:
                String time = event.getData("time").toString();

                this.eventLogMsgs.offer("Time changed to " + time);

                this.redrawEventLog();
                this.redrawTime(time);
                break;
            case GameEventType.TAKE_DAMAGE:
                this.eventLogMsgs.offer("Something took damage.");
                this.redrawEventLog();
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
     * starts a new game, initializing all relevent objects (terminal, textGUI), sets the color
     * theme, then takes control of the window to draw the starting game screen
     */
    public void start() {
        try {
            /* create a Swing-based terminal emulator -- UNIX-based terminal
            works, but seems to cause graphical errors based upon the system running it
            */
            this.terminal = new SwingTerminalFrame(
                "MUD Game",
                TerminalEmulatorAutoCloseTrigger.CloseOnExitPrivateMode
            );

            this.terminal.setVisible(true);

            // create screen component
            //DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            this.screen = new TerminalScreen(this.terminal);
            this.screen.startScreen();

            this.textGUI = new MultiWindowTextGUI(this.screen);

            // set tui color scheme
            Theme theme = SimpleTheme.makeTheme(
                true,               
                TextColor.ANSI.WHITE,             // normal foreground
                TextColor.ANSI.BLACK,             // normal background
                TextColor.ANSI.GREEN,             // editable foreground
                TextColor.ANSI.BLACK_BRIGHT,              // editable background
                TextColor.ANSI.GREEN_BRIGHT,             // focused foreground
                TextColor.ANSI.BLACK_BRIGHT,       // focused background (highlight)
                TextColor.ANSI.BLACK              // gui background
            );

            this.textGUI.setTheme(theme);

            System.out.println("game initialized!");

            this.drawStartUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * takes control of the console and draws the starting screen for the game,
     * then boots up the main game's UI once setup is complete
     */
    private void drawStartUp() {
        // set no shadow decorations for panels + full screen
        Window.Hint[] windowHints = new Window.Hint[] {
            Window.Hint.NO_DECORATIONS,
            //Window.Hint.NO_POST_RENDERING,
            Window.Hint.EXPANDED};

        final Window window = new BasicWindow("Welcome!");
        window.setHints(Arrays.asList(windowHints));

        Panel contentPanel = new Panel();
        LinearLayout layout = new LinearLayout(Direction.VERTICAL);
        layout.setSpacing(1);
        contentPanel.setLayoutManager(layout);
        LayoutData centerLayoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);

        Label welcomeLabel = new Label("Welcome, Adventurer!");
        welcomeLabel.setLayoutData(centerLayoutData);

        Label nameLabel = new Label("Enter your name before you plunge into the dungeons of MUD.");
        nameLabel.setLayoutData(centerLayoutData);

        TextBox nameBox = new TextBox().setPreferredSize(new TerminalSize(20, 1));
        nameBox.setLayoutData(centerLayoutData);

        Label descriptionLabel = new Label("Leave a few words behind--who are you?");
        descriptionLabel.setLayoutData(centerLayoutData);

        TextBox descriptionBox = new TextBox().setPreferredSize(new TerminalSize(40, 1));
        descriptionBox.setLayoutData(centerLayoutData);

        // fetch inputted name + description on submit
        Button submitButton = new Button("Submit", () -> {
            String playerName = nameBox.getText();
            String playerDescription = descriptionBox.getText();
            this.setupParser.setupPlayer(playerName, playerDescription);

            window.close(); // close the name prompt window
            drawUI(); // now start the main UI
        });
        submitButton.setLayoutData(centerLayoutData);

        contentPanel.addComponent(welcomeLabel);

        contentPanel.addComponent(nameLabel);
        contentPanel.addComponent(nameBox);

        contentPanel.addComponent(descriptionLabel);
        contentPanel.addComponent(descriptionBox);

        contentPanel.addComponent(submitButton);

        window.setComponent(contentPanel);
        this.textGUI.addWindowAndWait(window);
    }

    /**
     * takes control of the console and draws the main screen which displays all of the information currently
     * available to the player (map, turn number, etc.)
     */
    private void drawUI() {
        try {
            // set no shadow decorations for panels + full screen
            Window.Hint[] windowHints = new Window.Hint[] {
                Window.Hint.NO_DECORATIONS,
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
            this.eventLogDisplay = new Label("Event Log: \n\n");
            statusPanel.addComponent(this.eventLogDisplay);

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
            mapPanel.addComponent(this.mapDisplay);

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

            this.textGUI.addWindowAndWait(window);
        } finally {
            // if control over the window has not already been yielded, do so
            if(this.screen != null) {
                this.stop();
            }
        }
    }

    public void drawEndGameUI(String playerName, String playerDescription) {
        // set no shadow decorations for panels + full screen
        Window.Hint[] windowHints = new Window.Hint[] {
            Window.Hint.NO_DECORATIONS,
            //Window.Hint.NO_POST_RENDERING,
            Window.Hint.EXPANDED};

        final Window window = new BasicWindow("Welcome!");
        window.setHints(Arrays.asList(windowHints));

        Panel contentPanel = new Panel();
        LinearLayout layout = new LinearLayout(Direction.VERTICAL);
        layout.setSpacing(1);
        contentPanel.setLayoutManager(layout);
        LayoutData centerLayoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);

        Label congratsLabel = new Label("Congrats, you reached the goal!");
        congratsLabel.setLayoutData(centerLayoutData);

        Label playerDataText = new Label("And so ends the tale of " + playerName + 
                                    ", starting a legend anew in the lands of MUD of the one who \"" 
                                    + playerDescription + ".\"");
        playerDataText.setPreferredSize(new TerminalSize(60, 4));
        playerDataText.setLayoutData(centerLayoutData);

        Label queryLabel = new Label("End your adventure?");
        queryLabel.setLayoutData(centerLayoutData);

        Panel buttonPanel = new Panel();
        buttonPanel.setLayoutManager(new GridLayout(2));

        // if selected, immediately closes out the window + ends the game
        Button yesButton = new Button("Yes", () -> {
            window.close(); // close the name prompt window
            this.stop();
        });

        // if selected, dismiss the ending notification window and resume gameplay
        Button noButton = new Button("No", () -> {
            window.close(); // close the name prompt window
        });

        buttonPanel.addComponent(yesButton);
        buttonPanel.addComponent(noButton);
        buttonPanel.setLayoutData(centerLayoutData);

        contentPanel.addComponent(congratsLabel);
        contentPanel.addComponent(playerDataText);

        contentPanel.addComponent(queryLabel);
        contentPanel.addComponent(buttonPanel);

        window.setComponent(contentPanel);
        this.textGUI.addWindowAndWait(window);
    }

    /**
     * updates the text displayed in the menu panel to display the user's
     * current action options
     * 
     * @param displayText the new text to display on the menu panel
     */
    private void redrawMenu(String displayText) {
        this.menuDisplay.setText("Select an Option:\n" + displayText);
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
     * updates the text displayed in the event log panel to display the response to
     * the most recent action(s) taken
     * 
     */
    private void redrawEventLog() {
        //remove oldest event log message from the display
        if (this.eventLogMsgs.size() > 5) {
            this.eventLogMsgs.poll();
        }

        String displayText = this.eventLogMsgsToString();
        this.eventLogDisplay.setText("Event Log: \n\n" + displayText);
    }

    /**
    * helper method which converts the internal event log queue into a String,
    * placed in reverse order with new lines after each element in the following format:
    * <status msg>\n
    * <status msg>\n
    * ...
    */
    private String eventLogMsgsToString() {
        if (eventLogMsgs.isEmpty()) {
            return "";
        }

        ArrayList<String> statusMsgList = new ArrayList<String>(this.eventLogMsgs);
        
        StringBuilder sb = new StringBuilder();
        for  (int i = statusMsgList.size() - 1; i >= 0; i--) {
            sb.append(statusMsgList.get(i)).append("\n");
        }
        return sb.toString();
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

    /**
     * updates the text displayed in the map panel to show the change after
     * a player turn is taken
     * 
     * @param displayText
     */
    private void redrawMap(String displayText) {
        this.mapDisplay.setText(displayText);
    }
}