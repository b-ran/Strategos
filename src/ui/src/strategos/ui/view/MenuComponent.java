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
    private JButton howToPlayButton = new JButton(HOW_TO_PLAY_NAME);
    private JButton saveSlotOne = new JButton(SAVE_SLOT_BUTTON_NAME + " 1");
    private JButton saveSlotTwo = new JButton(SAVE_SLOT_BUTTON_NAME + " 2");
    private JButton saveSlotThree = new JButton(SAVE_SLOT_BUTTON_NAME + " 3");
    private JPanel p = new JPanel();

    private JPanel constructMenuSkeleton() {
        p = new JPanel();
        setupButtons();
        p.setPreferredSize(MENU_COMPONENT_SIZE);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        return p;
    }

    /**
     * Sets component as a main menu.
     *
     * @return the menu
     */
     JPanel setMenu() {
        p = constructMenuSkeleton();
        p.add(connectButton);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(hostButton);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(howToPlayButton);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(exitButton);
        return p;
    }


    /**
     * Sets component as an escape menu.
     *
     * @return the escape menu
     */
     JPanel setEscapeMenu(){
        p = constructMenuSkeleton();
        p.add(resumeButton);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(saveButton);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(loadButton);
      /*   p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
         p.add(newGameButton);*/
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(howToPlayButton);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(exitButton);
        return p;
    }

    /**
     * Sets component as the list of load options.
     *
     * @return the load menu
     */
    JPanel setLoadMenu(){
        p = constructMenuSkeleton();
        p.add(saveSlotOne);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(saveSlotTwo);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(saveSlotThree);
        p.add(Box.createVerticalStrut(MENU_PADDING_SIZE));
        p.add(exitButton);
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
        howToPlayButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        saveSlotOne.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveSlotTwo.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveSlotThree.setAlignmentX(Component.CENTER_ALIGNMENT);

        newGameButton.setMaximumSize(MENU_BUTTON_SIZE);
        exitButton.setMaximumSize(MENU_BUTTON_SIZE);
        resumeButton.setMaximumSize(MENU_BUTTON_SIZE);
        saveButton.setMaximumSize(MENU_BUTTON_SIZE);
        loadButton.setMaximumSize(MENU_BUTTON_SIZE);
        connectButton.setMaximumSize(MENU_BUTTON_SIZE);
        hostButton.setMaximumSize(MENU_BUTTON_SIZE);
        howToPlayButton.setMaximumSize(MENU_BUTTON_SIZE);

        saveSlotOne.setMaximumSize(MENU_BUTTON_SIZE);
        saveSlotTwo.setMaximumSize(MENU_BUTTON_SIZE);
        saveSlotThree.setMaximumSize(MENU_BUTTON_SIZE);
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

    public JButton getSaveSlot(int index) {
        switch (index) {
            case 1:
                return saveSlotOne;
            case 2:
                return saveSlotTwo;
            default:
                return saveSlotThree;
        }
    }

    public JButton getHowToPlayButton() {
        return howToPlayButton;
    }
}
