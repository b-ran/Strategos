package strategos.ui.view;

import javax.swing.*;
import java.awt.*;
import static strategos.ui.config.Config.*;

public class MenuComponent extends JComponent {

    private JButton newGameButton = new JButton(NEWGAME_BUTTON_NAME);
    private JButton exitButton = new JButton(EXIT_BUTTON_NAME);
    private JButton resumeButton = new JButton(RESUME_BUTTON_NAME);
    private JButton saveButton = new JButton(SAVE_BUTTON_NAME);
    private JButton loadButton = new JButton(LOAD_BUTTON_NAME);
    private JButton connectButton = new JButton(CONNECT_BUTTON_NAME);
    private JButton hostButton = new JButton(HOST_BUTTON_NAME);
    private JPanel p = new JPanel();

    protected JPanel getMenu() {
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


    protected  JPanel getEscapeMenu(){
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

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getResumeButton() {
        return resumeButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getConnectButton() {
        return connectButton;
    }

    public JButton getHostButton() {
        return hostButton;
    }
}
