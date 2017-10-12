package strategos.ui.config;

import java.awt.*;
import java.awt.event.MouseEvent;

import static strategos.Config.*;

public class Config {
    //Menu
    public static final int MENU_PADDING_SIZE = 40;

    public static final Dimension MENU_BUTTON_SIZE = new Dimension(300,200);

    public static final Dimension MENU_COMPONENT_SIZE = new Dimension(500,500);

    //Text
    public static final String WINDOW_NAME = "Strategos";

    public static final String NEWGAME_BUTTON_NAME = "New Game";

    public static final String EXIT_BUTTON_NAME = "Exit";

    public static final String RESUME_BUTTON_NAME = "Resume";

    public static final String SAVE_BUTTON_NAME = "Save";

    public static final String LOAD_BUTTON_NAME = "Load";

    public static final String CONNECT_BUTTON_NAME = "Connect";

    public static final String HOST_BUTTON_NAME = "Host";

    public static final String SAVE_SLOT_BUTTON_NAME = "Save Slot";

    public static final String HOW_TO_PLAY_NAME = "How to play";

    public static final Dimension GAME_INSTRUCTION_BOX_DIMENSIONS = new Dimension(600, 700);

    public static final String GAME_INSTRUCTION_MESSAGE =
            "Strategos: \n" +
            "This is a turn-based tactical multiplayer board game. You control a number of units to try and take control of the " +
                    "board with. You must attack the enemy units with yours, and once you have killed every enemy unit, " +
                    "you win the game! But beware, your enemy will try to do the same.\n\n" +
                    "The game must be played on a LAN between two computers, or two instances on a single computer. The " +
                    "host must click the 'Host' button in the main " +
                    "menu, and then tell the other player their local IPv4 address. The connector must then click 'Connect' and " +
                    "type in the IP and press 'OK'. The game will then begin, starting at the host's turn. Click 'Next Turn' when " +
                    "your turn is done.\n" +
                    "One computer may be used to run a game. Simply follow the above steps between two instances of the game, " +
                    "except no IP is required for the connector to use.\n\n" +
                    "Select a unit with left click. Hexes that you may move to will appear highlighted in yellow. Right-click to move to them. You may " +
                    "move a unit until it runs out of Action Points (displayed in the right sidebar). If you move within " +
                    "range of an enemy unit, you may attack it. Your attack is proportional to your unit's Strength (" +
                    "in the right sidebar), and the enemy unit's Toughness. When attacking with any unit except for " +
                    "Archers, both units will take damage from combat. Hitpoints are displayed on the right. When your" +
                    " unit reaches 0 hitpoints, it is removed from the game. Use your units wisely! You may Entrench " +
                    "your units, which consumes 2 AP per turn and gives a defensive bonus, or make them Wary, which consumes " +
                    "1 AP and gives a smaller defensive bonus.\n\n" +
                    "You control 5 types of units:\n" +
                    "Swordsman: High attack, decent defence, " + INFANTRY_ACTION_POINTS + " AP.\n" +
                    "Spearman: Low attack, high defence, " + INFANTRY_ACTION_POINTS + " AP.\n" +
                    "Archers: Decent attack, low defence, " + INFANTRY_ACTION_POINTS + " AP, can attack from "+ ARCHER_RANGE +
                    " tiles away and take no damage when attacking.\n" +
                    "Cavalry: Decent attack, decent defence, " + CAVALRY_ACTION_POINTS + " AP.\n" +
                    "Elites: Decent attack, very high defence, " + ELITE_ACTION_POINTS + " AP\n\n" +
                    "Terrain is your friend and enemy! Hills provide a good defensive bonus to units standing on them, " +
                    "and forests slightly less so. Rivers and mountains are impassable. However, you can use your units to " +
                    "attack and capture any of the three bridges that allow them to cross the river. Your enemy will seek to do the " +
                    "same, to deny you your right to cross and meet them in combat!\n\n" +
                    "Be warned, general. While you engage in your test of strength, a number of filthy barbarians wander " +
                    "the land, attacking anything that comes near! Slay the brutes, or lure them into your enemy, to make arrow fodder. " +
                    "They have left supplies on the battlefield, which your units can plunder to regain their fighting spirit and heal.\n\n";

    //Grid

    public static final Dimension GRID_COMPONENT_SIZE = new Dimension(1500, 700);

    public static final Integer HEX_SIZE = 60;

    public static final Color SELECTION_COLOR = new Color(154, 208, 255);

    public static final Color SELECTION_MOVE_COLOR =  new Color(255, 245, 199);

    public static final Color SELECTION_ATTACK_COLOR =  new Color(255, 0, 0);

    public static final float SELECTION_STROKE_SIZE = 2;

    public static final Color HEALTH_HIGH_COLOR = new Color(8, 207, 0);

    public static final Color HEALTH_MID_COLOR = new Color(233, 219, 41);

    public static final Color HEALTH_LOW_COLOR = new Color(207, 11, 18);

    public static final Dimension HEALTH_BAR_SIZE = new Dimension(3, 25);

    public static final Point HEALTH_BAR_RELATIVE_POSITION = new Point(5, 20);

    public static final int HEALTH_MID_PERCENTAGE = 60;

    public static final int HEALTH_LOW_PERCENTAGE = 20;



    //Side

    public static final Dimension SIDE_COMPONENT_SIZE = new Dimension(400, GRID_COMPONENT_SIZE.height);

    public static final Dimension SIDE_PANEL_SIZE = new Dimension(400, 40);

    public static final Dimension SIDEPANEL_BUTTON_SIZE = new Dimension(80,100);

    public static final Point SELECTION_LOCATION = new Point(20,30);

    public static final String PLAYER_NAME = "Your Turn";

    public static final String OTHER_PLAYER_NAME = "Other Player's Turn";

    public static final Point PLAYER_NAME_LOCATION = new Point(20, 10);

    public static final String HEALTH_LABEL_NAME = "Health:";

    public static final Point HEALTH_LABEL_LOCATION = new Point(20, 120);

    public static final int LABEL_PADDING = 20;

    public static final String ACTIONPOINT_LABEL_NAME = "Action Points:";

    public static final Point ACTIONPOINT_LABEL_LOCATION = new Point(20, HEALTH_LABEL_LOCATION.y+LABEL_PADDING);

    public static final String ENTRENCH_LABEL_NAME = "Entrench:";

    public static final Point ENTRENCH_LABEL_LOCATION = new Point(20, ACTIONPOINT_LABEL_LOCATION.y+LABEL_PADDING);

    public static final String WARY_LABEL_NAME = "Wary:";

    public static final Point WARY_LABEL_LOCATION = new Point(20, ENTRENCH_LABEL_LOCATION.y+LABEL_PADDING);

    public static final String STRENGTH_LABEL_NAME = "Strength:";

    public static final Point STRENGTH_LABEL_LOCATION = new Point(20, WARY_LABEL_LOCATION.y+LABEL_PADDING);

    public static final String TOUGHNESS_LABEL_NAME = "Toughness:";

    public static final Point TOUGHNESS_LABEL_LOCATION = new Point(20, STRENGTH_LABEL_LOCATION.y+LABEL_PADDING);

    //Text
    public static final String WARY_BUTTON_NAME = "Wary";

    public static final String ENTRENCH_BUTTON_NAME = "Entrench";

    public static final String ATTACK_BUTTON_NAME = "Attack";

    public static final String NEXT_TURN_BUTTON_NAME = "Next Turn";


    //Input

    public static final int SELECTION_INPUT_BUTTON = MouseEvent.BUTTON1;

    public static final int MOVE_INPUT_BUTTON = MouseEvent.BUTTON3;

    public static final int ATTACK_INPUT_BUTTON = MouseEvent.BUTTON3;

}
