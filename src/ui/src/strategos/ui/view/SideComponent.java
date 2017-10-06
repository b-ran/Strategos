package strategos.ui.view;

import strategos.MapLocation;
import strategos.units.Unit;

import javax.swing.*;
import java.awt.*;

import static strategos.ui.config.Config.*;

/**
 * The type Side component.
 */
public class SideComponent extends JComponent {

    private JButton waryButton = new JButton(WARY_BUTTON_NAME);
    private JButton chargeButton = new JButton(CHARGE_BUTTON_NAME);
    private JButton attackButton = new JButton(ATTACK_BUTTON_NAME);
    private JButton nextTurnButton = new JButton(NEXT_TURN_BUTTON_NAME);
    private DrawEntity drawEntity = new DrawEntity();
    private MapLocation selectedMapLocation;
    private Unit selectedUnit;

    /**
     * Instantiates a new Side component for drawing on.
     */
    SideComponent() {
        setLayout(new BorderLayout());
        setPreferredSize(SIDE_COMPONENT_SIZE);
    }

    /**
     *
     * Gets side panel for buttons.
     *
     * @return the side panel
     */
    JPanel getSidePanel() {
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

    @Override
    protected void paintComponent(Graphics g) {
        paintSelection(g);
    }

    private void paintSelection(Graphics g) {
        if (selectedMapLocation == null) return;
        if (selectedUnit != null) {
            drawEntity.drawUnitPos(selectedUnit, SELECTION_LOCATION.x, SELECTION_LOCATION.y, g);
            return;
        }
        drawEntity.draw(selectedMapLocation,SELECTION_LOCATION.x, SELECTION_LOCATION.y, g);
    }

    private void paintHealth(Graphics g) {

    }

    public void setSelection(MapLocation selectedMapLocation, Unit selectedUnit) {
        this.selectedMapLocation = selectedMapLocation;
        this.selectedUnit = selectedUnit;
    }
}
