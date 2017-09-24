package strategos.ui.view;

import javax.swing.*;
import java.awt.*;

import static strategos.ui.config.Config.*;

/**
 * The type Side component.
 */
class SideComponent extends JComponent {


    private JButton waryButton = new JButton(WARY_BUTTON_NAME);
    private JButton chargeButton = new JButton(CHARGE_BUTTON_NAME);
    private JButton attackButton = new JButton(ATTACK_BUTTON_NAME);
    private JButton nextTurnButton = new JButton(NEXT_TURN_BUTTON_NAME);

    /**
     * Instantiates a new Side component for drawing on.
     */
    protected SideComponent() {
        setLayout(new BorderLayout());
        setPreferredSize(SIDE_COMPONENT_SIZE);
    }


    /**
     * Gets side panel for buttons.
     *
     * @return the side panel
     */
    public JPanel getSidePanel() {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.RIGHT));

        waryButton.setMaximumSize(SIDEPANEL_BUTTON_SIZE);
        chargeButton.setMaximumSize(SIDEPANEL_BUTTON_SIZE);
        attackButton.setMaximumSize(SIDEPANEL_BUTTON_SIZE);
        nextTurnButton.setMaximumSize(SIDEPANEL_BUTTON_SIZE);

        p.add(waryButton);
        p.add(chargeButton);
        p.add(attackButton);
        p.add(nextTurnButton);

        p.setPreferredSize(SIDE_PANEL_SIZE);

        return p;
    }

}
