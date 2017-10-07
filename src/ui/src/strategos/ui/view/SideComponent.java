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
    private JButton entrenchButton = new JButton(ENTRENCH_BUTTON_NAME);
    private JButton attackButton = new JButton(ATTACK_BUTTON_NAME);
    private JButton nextTurnButton = new JButton(NEXT_TURN_BUTTON_NAME);
    private DrawEntity drawEntity;
    private MapLocation selectedMapLocation;
    private Unit selectedUnit;
    private String playerText = PLAYER_NAME;

    /**
     * Instantiates a new Side component for drawing on.
     */
    SideComponent(View view) {
        setLayout(new BorderLayout());
        setPreferredSize(SIDE_COMPONENT_SIZE);
        drawEntity = new DrawEntity(view);
    }

    /**
     * Gets side panel for buttons.
     *
     * @return the side panel
     */
    JPanel getSidePanel() {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.RIGHT));

        waryButton.setMaximumSize(SIDEPANEL_BUTTON_SIZE);
        entrenchButton.setMaximumSize(SIDEPANEL_BUTTON_SIZE);
        attackButton.setMaximumSize(SIDEPANEL_BUTTON_SIZE);
        nextTurnButton.setMaximumSize(SIDEPANEL_BUTTON_SIZE);

        p.add(waryButton);
        p.add(entrenchButton);
        p.add(attackButton);
        p.add(nextTurnButton);

        p.setPreferredSize(SIDE_PANEL_SIZE);

        return p;
    }

    @Override
    protected void paintComponent(Graphics g) {
        paintSelection(g);
        paintPlayerText(g);
        paintLabels(g);
    }

    private void paintPlayerText(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString(playerText, PLAYER_NAME_LOCATION.x, PLAYER_NAME_LOCATION.y);
    }

    private void paintSelection(Graphics g) {
        if (selectedMapLocation == null) return;
        if (selectedUnit != null) {
            drawEntity.drawUnitPos(selectedUnit, SELECTION_LOCATION.x, SELECTION_LOCATION.y, g);
            return;
        }
        drawEntity.draw(selectedMapLocation,SELECTION_LOCATION.x, SELECTION_LOCATION.y, g);
    }

    private void paintLabels(Graphics g) {
        if (selectedUnit == null) return;
        g.setColor(Color.BLACK);
        g.drawString(HEALTH_LABEL_NAME + " " + selectedUnit.getHitpoints(), HEALTH_LABEL_LOCATION.x, HEALTH_LABEL_LOCATION.y);
        g.drawString(ACTIONPOINT_LABEL_NAME + " " + selectedUnit.getActionPoints(), ACTIONPOINT_LABEL_LOCATION.x, ACTIONPOINT_LABEL_LOCATION.y);
        g.drawString(ENTRENCH_LABEL_NAME + " " + selectedUnit.getEntrench(), ENTRENCH_LABEL_LOCATION.x, ENTRENCH_LABEL_LOCATION.y);
        g.drawString(WARY_LABEL_NAME + " " + selectedUnit.getWary(), WARY_LABEL_LOCATION.x, WARY_LABEL_LOCATION.y);

    }


    public void setSelection(MapLocation selectedMapLocation, Unit selectedUnit) {
        this.selectedMapLocation = selectedMapLocation;
        this.selectedUnit = selectedUnit;
    }


    public void setPlayerText(String playerText) {
        this.playerText = playerText;
    }

    public JButton getNextTurnButton() {
        return nextTurnButton;
    }

    public JButton getWaryButton() {
        return waryButton;
    }

    public JButton getEntrenchButton() {
        return entrenchButton;
    }
}
