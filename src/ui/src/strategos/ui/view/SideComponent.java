package strategos.ui.view;

import strategos.Config;
import strategos.model.MapLocation;
import strategos.units.Unit;

import javax.swing.*;
import java.awt.*;

import static strategos.ui.config.Config.*;

/**
 * The type Side component.
 *
 * @author Brandon Scott-Hill - scotthbran
 * @author Daniel Pinfold - pinfoldani
 *
 */
public class SideComponent extends JComponent {

    private final View view;
    private JButton waryButton = new JButton(WARY_BUTTON_NAME);
    private JButton entrenchButton = new JButton(ENTRENCH_BUTTON_NAME);
    private JButton attackButton = new JButton(ATTACK_BUTTON_NAME);
    private JButton nextTurnButton = new JButton(NEXT_TURN_BUTTON_NAME);
    private Draw draw;

    private MapLocation selectedMapLocation;
    private Unit selectedUnit;

    private String playerText = PLAYER_NAME; //The text for the current turn

    /**
     * Instantiates a new Side component for drawing on.
     *
     * @param view the view
     * @author Brandon Scott-Hill
     */
    SideComponent(View view) {
        this.view = view;
        setLayout(new BorderLayout());
        setPreferredSize(SIDE_COMPONENT_SIZE);
        draw = new Draw(view);
    }

    /**
     * Gets side panel for buttons.
     *
     * @return the side panel
     * @author Brandon Scott-Hill
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
        paintTurnText(g);
        paintLabels(g);
    }


    /**
     * Draws the player text
     *
     * @author Brandon Scott-Hill
     *
     * @param g the Graphics
     */
    private void paintPlayerText(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString(playerText, PLAYER_NAME_LOCATION.x, PLAYER_NAME_LOCATION.y);
    }


    /**
     * Draws the current turn
     *
     * @author Daniel Pinfold
     *
     * @param g the Graphics
     */
    private void paintTurnText(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Turn " + view.getNumberTurns(), TURN_NUMBER_LOCATION.x, TURN_NUMBER_LOCATION.y);
    }

    /**
     * Draws the current selection
     *
     * @author Brandon Scott-Hill
     *
     * @param g the Graphics
     */
    private void paintSelection(Graphics g) {
        if (selectedMapLocation == null) return;
        if (!view.getSeenTerrain().contains(selectedMapLocation)) return;

        if (selectedUnit != null) {
            draw.drawUnitNonGrid(selectedUnit, SELECTION_LOCATION, g);
            return;
        }
        draw.drawTerrainNonGrid(selectedMapLocation.getTerrain(), SELECTION_LOCATION, g);
    }

    /**
     * Draws the labels of the current selection
     *
     * @author Brandon Scott-Hill
     * @author Daniel Pinfold
     *
     * @param g the Graphics
     */
    private void paintLabels(Graphics g) {
        if (selectedUnit == null) return;
        if (!view.getSeenTerrain().contains(selectedMapLocation)) return;

        g.setColor(Color.BLACK);
        g.drawString(selectedUnit.toString(), UNIT_NAME_LOCATION.x, UNIT_NAME_LOCATION.y);
        g.drawString(HEALTH_LABEL_NAME + " " + Math.max(selectedUnit.getHitpoints(), 0), HEALTH_LABEL_LOCATION.x, HEALTH_LABEL_LOCATION.y);
        g.drawString(ACTIONPOINT_LABEL_NAME + " " + Math.max(selectedUnit.getActionPoints(), 0), ACTIONPOINT_LABEL_LOCATION.x, ACTIONPOINT_LABEL_LOCATION.y);
        g.drawString(ENTRENCH_LABEL_NAME + " " + selectedUnit.getEntrench(), ENTRENCH_LABEL_LOCATION.x, ENTRENCH_LABEL_LOCATION.y);
        g.drawString(WARY_LABEL_NAME + " " + selectedUnit.getWary(), WARY_LABEL_LOCATION.x, WARY_LABEL_LOCATION.y);

        int toughnessMod = 0;
        if (selectedUnit.getWary()) {
            toughnessMod = Config.WARY_MODIFIER;
        } else if (selectedUnit.getEntrench()) {
            toughnessMod = Config.ENTRENCH_MODIFIER;
        }

        g.drawString(STRENGTH_LABEL_NAME + " " + selectedUnit.getStrength(), STRENGTH_LABEL_LOCATION.x, STRENGTH_LABEL_LOCATION.y);
        g.drawString(TOUGHNESS_LABEL_NAME + " " + selectedUnit.getToughness() + ((toughnessMod != 0) ? " ( + " + toughnessMod + ")" : ""), TOUGHNESS_LABEL_LOCATION.x, TOUGHNESS_LABEL_LOCATION.y);

    }

    /**
     * Sets selection.
     *
     * @author Brandon Scott-Hill
     *
     * @param selectedMapLocation the selected map location
     * @param selectedUnit        the selected unit
     */
    public void setSelection(MapLocation selectedMapLocation, Unit selectedUnit) {
        this.selectedMapLocation = selectedMapLocation;
        this.selectedUnit = selectedUnit;
    }

    /**
     * Sets player text.
     *
     * @author Brandon Scott-Hill
     *
     * @param playerText the player text
     */
    public void setPlayerText(String playerText) {
        this.playerText = playerText;
    }


    /**
     * Gets next turn button.
     *
     * @author Brandon Scott-Hill
     *
     * @return the next turn button
     */
    public JButton getNextTurnButton() {
        return nextTurnButton;
    }

    /**
     * Gets wary button.
     *
     * @author Brandon Scott-Hill
     *
     * @return the wary button
     */
    public JButton getWaryButton() {
        return waryButton;
    }

    /**
     * Gets entrench button.
     *
     * @author Brandon Scott-Hill
     *
     * @return the entrench button
     */
    public JButton getEntrenchButton() {
        return entrenchButton;
    }

    /**
     * Gets attack button.
     *
     * @author Brandon Scott-Hill
     *
     * @return the attack button
     */
    public JButton getAttackButton() {
        return attackButton;
    }
}
