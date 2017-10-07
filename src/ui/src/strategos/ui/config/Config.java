package strategos.ui.config;

import java.awt.*;

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

    //Grid

    public static final Dimension GRID_COMPONENT_SIZE = new Dimension(980, 600);

    public static final Integer HEX_SIZE = 40;

    public static final Color NPC_COLOR = Color.YELLOW;

    public static final Color OTHER_PLAYER_COLOR = Color.RED;

    public static final Color PLAYER_COLOR = Color.BLUE;

    public static final Color UNIT_FONT_COLOR = Color.BLACK;

    public static final String UNIT_ARCHERS_LETTER = "A";

    public static final String UNIT_CAVALRY_LETTER = "C";

    public static final String UNIT_ELITE_LETTER = "E";

    public static final String UNIT_SPEARMEN_LETTER = "Sp";

    public static final String UNIT_SWORDSMEN_LETTER = "Sw";

    public static final Color TERRAIN_FOREST_COLOR = new Color(0,150,0);

    public static final Color TERRAIN_HILL_COLOR = new Color(0,100,0);

    public static final Color TERRAIN_MOUNTAIN_COLOR = Color.LIGHT_GRAY;

    public static final Color TERRAIN_PLAINS_COLOR = new Color(0,255,0);

    public static final Color TERRAIN_RIVER_COLOR = Color.CYAN;

    public static final Color SELECTION_COLOR = new Color(154, 208, 255);

    public static final Color SELECTION_MOVE_COLOR =  new Color(255, 245, 199);

    public static final Color SELECTION_ATTACK_COLOR =  new Color(255, 0, 0);

    public static final int SELECTION_STROKE_SIZE = 2;


    //Side

    public static final Dimension SIDE_COMPONENT_SIZE = new Dimension(400, GRID_COMPONENT_SIZE.height);

    public static final Dimension SIDE_PANEL_SIZE = new Dimension(400, 40);

    public static final Dimension SIDEPANEL_BUTTON_SIZE = new Dimension(80,100);

    public static final Point SELECTION_LOCATION = new Point(20,40);

    public static final String PLAYER_NAME = "Your Turn";

    public static final String OTHER_PLAYER_NAME = "Other Player's Turn";

    public static final Point PLAYER_NAME_LOCATION = new Point(20, 10);

    public static final String HEALTH_LABEL_NAME = "Health:";

    public static final Point HEALTH_LABEL_LOCATION = new Point(20, 80);

    public static final String ACTIONPOINT_LABEL_NAME = "Action Points:";

    public static final Point ACTIONPOINT_LABEL_LOCATION = new Point(20, 100);

    public static final String ENTRENCH_LABEL_NAME = "Entrench:";

    public static final Point ENTRENCH_LABEL_LOCATION = new Point(20, 120);

    public static final String WARY_LABEL_NAME = "Wary:";

    public static final Point WARY_LABEL_LOCATION = new Point(20, 140);


    //Text
    public static final String WARY_BUTTON_NAME = "Wary";

    public static final String ENTRENCH_BUTTON_NAME = "Entrench";

    public static final String ATTACK_BUTTON_NAME = "Attack";

    public static final String NEXT_TURN_BUTTON_NAME = "Next Turn";

}
