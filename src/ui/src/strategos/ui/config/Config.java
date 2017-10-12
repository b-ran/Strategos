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




    //Text
    public static final String WARY_BUTTON_NAME = "Wary";

    public static final String ENTRENCH_BUTTON_NAME = "Entrench";

    public static final String ATTACK_BUTTON_NAME = "Attack";

    public static final String NEXT_TURN_BUTTON_NAME = "Next Turn";

}
