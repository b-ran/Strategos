package strategos.ui.view;

import java.awt.*;

import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.units.*;
import strategos.terrain.*;

import static strategos.ui.config.Config.HEX_SIZE;

/**
 * The type Entity image.
 */
class DrawEntity {

    Color npcColor = Color.YELLOW;
    Color otherPlayerColor = Color.RED;
    Color playerColor = Color.BLUE;


    private void setUnitColor(Unit unit, Graphics g) {
        UnitOwner owner = unit.getOwner();
        if (owner.isNPC()) {
            g.setColor(npcColor);
        } else if (owner.getUnits().contains(unit)) {
            g.setColor(playerColor);
        } else {
            g.setColor(otherPlayerColor);
        }
    }



    /**
     * Draws Archers.
     *
     * @param archers to draw
     */
    public void draw(Archers archers, Graphics g) {
        MapLocation m = archers.getPosition();
        setUnitColor(archers, g);
        g.fillOval(getGridX(m.getX()), getGridY(m.getY()) , HEX_SIZE/2, HEX_SIZE/2);
    }

    /**
     * Draws A Cavalry.
     *
     * @param cavalry to draw
     */
    public void draw(Cavalry cavalry, Graphics g) {
        MapLocation m = cavalry.getPosition();
        setUnitColor(cavalry, g);
        g.fillOval(getGridX(m.getX()), getGridY(m.getY()) , HEX_SIZE/2, HEX_SIZE/2);
    }

    /**
     * Draws A Elite.
     *
     * @param elite to draw
     */
    public void draw(Elite elite, Graphics g) {
        MapLocation m = elite.getPosition();
        setUnitColor(elite, g);
        g.fillOval(getGridX(m.getX()), getGridY(m.getY()) , HEX_SIZE/2, HEX_SIZE/2);
    }

    /**
     * Draws A Spearmen.
     *
     * @param spearmen to draw
     */
    public void draw(Spearmen spearmen, Graphics g) {
        MapLocation m = spearmen.getPosition();
        setUnitColor(spearmen, g);
        g.fillOval(getGridX(m.getX()), getGridY(m.getY()) , HEX_SIZE/2, HEX_SIZE/2);
    }

    /**
     * Draws A Swordsmen.
     *
     * @param swordsmen to draw
     */
    public void draw(Swordsmen swordsmen, Graphics g) {
        MapLocation m = swordsmen.getPosition();
        setUnitColor(swordsmen, g);
        g.fillOval(getGridX(m.getX()), getGridY(m.getY()) , HEX_SIZE/2, HEX_SIZE/2);
    }

    /**
     * Draws A Forest.
     *
     * @param forest to draw
     */
    public void draw(Forest forest, Graphics g) {

    }

    /**
     * Draws A Hill.
     *
     * @param hill to draw
     */
    public void draw(Hill hill, Graphics g) {

    }

    /**
     * Draws Mountains.
     *
     * @param mountain to draw
     */
    public void draw(Mountain mountain, Graphics g) {

    }

    /**
     * Draws a Plain.
     *
     * @param plains to draw
     */
    public void draw(Plains plains, Graphics g) {

    }

    /**
     * Draws A River.
     *
     * @param river to draw
     */
    public void draw(River river, Graphics g) {

    }

    private int getGridY(int y) {
        return HEX_SIZE/2 * 3/2 * y;
    }

    private int getGridX(int x) {
        return x * HEX_SIZE + HEX_SIZE;
    }

}
