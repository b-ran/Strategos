package strategos.ui.view;

import javax.swing.*;
import java.awt.*;
import static strategos.ui.config.Config.*;

/**
 * The type Menu component.
 */
public class MenuComponent extends JComponent {

    private JButton newGameButton = new JButton(NEWGAME_BUTTON_NAME);
    private JButton exitButton = new JButton(EXIT_BUTTON_NAME);
    private JButton resumeButton = new JButton(RESUME_BUTTON_NAME);
    private JButton saveButton = new JButton(SAVE_BUTTON_NAME);
    private JButton loadButton = new JButton(LOAD_BUTTON_NAME);
    private JButton connectButton = new JButton(CONNECT_BUTTON_NAME);
    private JButton hostButton = new JButton(HOST_BUTTON_NAME);
    private JPanel p = new JPanel();

    /*
    TODO - REVIEW: since setMenu and setEscapeMenu are identical except for the buttons,
    TODO              could you just have a constructMenuSkeleton method that fills out the layout
    TODO              and then populate it with buttons? Or does the order of instruction matter too much?
    */

    /**
     * Sets component as a main menu.
     *
     * @return the menu
     */
    protected JPanel setMenu() {
        p = new JPanel();
        setupButtons();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(newGameButton);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(loadButton);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(connectButton);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(hostButton);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(exitButton);
        p.setPreferredSize(MENU_COMPONENT_SIZE);
        return p;
    }


    /**
     * Sets component as an escape menu.
     *
     * @return the escape menu
     */
    protected JPanel setEscapeMenu(){
        p = new JPanel();
        setupButtons();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(resumeButton);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(newGameButton);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(saveButton);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(loadButton);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(exitButton);
        p.setPreferredSize(MENU_COMPONENT_SIZE);
        return p;
    }

    private void setupButtons() {
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resumeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        connectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hostButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        newGameButton.setMaximumSize(MENU_BUTTON_SIZE);
        exitButton.setMaximumSize(MENU_BUTTON_SIZE);
        resumeButton.setMaximumSize(MENU_BUTTON_SIZE);
        saveButton.setMaximumSize(MENU_BUTTON_SIZE);
        loadButton.setMaximumSize(MENU_BUTTON_SIZE);
        connectButton.setMaximumSize(MENU_BUTTON_SIZE);
        hostButton.setMaximumSize(MENU_BUTTON_SIZE);
    }

    /**
     * Gets new game button.
     *
     * @return the new game button
     */
    public JButton getNewGameButton() {
        return newGameButton;
    }

    /**
     * Gets exit button.
     *
     * @return the exit button
     */
    public JButton getExitButton() {
        return exitButton;
    }

    /**
     * Gets resume button.
     *
     * @return the resume button
     */
    public JButton getResumeButton() {
        return resumeButton;
    }

    /**
     * Gets save button.
     *
     * @return the save button
     */
    public JButton getSaveButton() {
        return saveButton;
    }

    /**
     * Gets load button.
     *
     * @return the load button
     */
    public JButton getLoadButton() {
        return loadButton;
    }

    /**
     * Gets connect button.
     *
     * @return the connect button
     */
    public JButton getConnectButton() {
        return connectButton;
    }

    /**
     * Gets host button.
     *
     * @return the host button
     */
    public JButton getHostButton() {
        return hostButton;
    }
}
