package strategos.ui.view;

import java.awt.*;

import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.units.*;
import strategos.terrain.*;

import static strategos.ui.config.Config.*;

/**
 * The type Entity image.
 */
class DrawEntity {

    /*
    TODO - REVIEW: This has a lot of duplicated code (which makes sense at this level of development) but it would be
    TODO            better to use polymorphism and just draw the sprite stored within Unit (or within the Graphical interface
    TODO            which Unit implements). Graphical could also store the letter and colour, and apply to Terrain as well
     */

    /**
     * Draws Archers.
     *
     * @param archers to draw
     */
    void draw(Archers archers, Graphics g) {
        MapLocation m = archers.getPosition();
        setUnitColor(archers, g);
        Point p = getUnitGridPos(m);
        g.fillOval(p.x, p.y , HEX_SIZE/2, HEX_SIZE/2);
        g.setColor(UNIT_FONT_COLOR);
        g.drawString(UNIT_ARCHERS_LETTER, p.x, p.y);
    }

    /**
     * Draws A Cavalry.
     *
     * @param cavalry to draw
     */
    void draw(Cavalry cavalry, Graphics g) {
        MapLocation m = cavalry.getPosition();
        setUnitColor(cavalry, g);
        Point p = getUnitGridPos(m);
        g.fillOval(p.x, p.y , HEX_SIZE/2, HEX_SIZE/2);
        g.setColor(UNIT_FONT_COLOR);
        g.drawString(UNIT_CAVALRY_LETTER, p.x, p.y);
    }

    /**
     * Draws A Elite.
     *
     * @param elite to draw
     */
    public void draw(Elite elite, Graphics g) {
        MapLocation m = elite.getPosition();
        setUnitColor(elite, g);
        Point p = getUnitGridPos(m);
        g.fillOval(p.x, p.y , HEX_SIZE/2, HEX_SIZE/2);
        g.setColor(UNIT_FONT_COLOR);
        g.drawString(UNIT_ELITE_LETTER, p.x, p.y);
    }

    /**
     * Draws A Spearmen.
     *
     * @param spearmen to draw
     */
    void draw(Spearmen spearmen, Graphics g) {
        MapLocation m = spearmen.getPosition();
        setUnitColor(spearmen, g);
        Point p = getUnitGridPos(m);
        g.fillOval(p.x, p.y , HEX_SIZE/2, HEX_SIZE/2);
        g.setColor(UNIT_FONT_COLOR);
        g.drawString(UNIT_SPEARMEN_LETTER, p.x, p.y);
    }

    /**
     * Draws A Swordsmen.
     *
     * @param swordsmen to draw
     */
    void draw(Swordsmen swordsmen, Graphics g) {
        MapLocation m = swordsmen.getPosition();
        setUnitColor(swordsmen, g);
        Point p = getUnitGridPos(m);
        g.fillOval(p.x, p.y , HEX_SIZE/2, HEX_SIZE/2);
        g.setColor(UNIT_FONT_COLOR);
        g.drawString(UNIT_SWORDSMEN_LETTER, p.x, p.y);
    }

    /**
     * Draws A Forest.
     *
     * @param forest to draw
     */
    void draw(Forest forest, Graphics g, int x, int y) {
        Point p = getTerrainGridPos(new Point(x,y));
        fillHexagon(g, p.x, p.y, TERRAIN_FOREST_COLOR);
    }

    /**
     * Draws A Hill.
     *
     * @param hill to draw
     */
    void draw(Hill hill, Graphics g, int x, int y) {
        Point p = getTerrainGridPos(new Point(x,y));
        fillHexagon(g, p.x, p.y, TERRAIN_HILL_COLOR);
    }

    /**
     * Draws Mountains.
     *
     * @param mountain to draw
     */
    void draw(Mountain mountain, Graphics g, int x, int y) {
        Point p = getTerrainGridPos(new Point(x,y));
        fillHexagon(g, p.x, p.y, TERRAIN_MOUNTAIN_COLOR);
    }

    /**
     * Draws a Plain.
     *
     * @param plains to draw
     */
    void draw(Plains plains, Graphics g, int x, int y) {
        Point p = getTerrainGridPos(new Point(x,y));
        fillHexagon(g, p.x, p.y, TERRAIN_PLAINS_COLOR);
    }

    /**
     * Draws A River.
     *
     * @param river to draw
     */
    void draw(River river, Graphics g, int x, int y) {
        Point p = getTerrainGridPos(new Point(x,y));
        fillHexagon(g, p.x, p.y, TERRAIN_RIVER_COLOR);
    }


    private Point getTerrainGridPos (Point p) {
        int y = getGridY(p.y);
        int x = getGridX(p.x);
        if (p.y % 2 != 0) {
            x = getGridX(p.x)+HEX_SIZE/2;
        }
        return new Point(x,y);
    }

    public Point getTerrainGridPos (MapLocation m) {
        int y = getGridY(m.getY());
        int x = getGridX(m.getX());
        if (m.getY() % 2 != 0) {
            x = getGridX(m.getX())+HEX_SIZE/2;
        }
        return new Point(x,y);
    }

    private void setUnitColor(Unit unit, Graphics g) {
        UnitOwner owner = unit.getOwner();
        if (owner.isNPC()) {
            g.setColor(NPC_COLOR);
        } else if (owner.getUnits().contains(unit)) {
            g.setColor(PLAYER_COLOR);
        } else {
            g.setColor(OTHER_PLAYER_COLOR);
        }
    }

    private Point getUnitGridPos(MapLocation m) {
        int y = getGridY(m.getY())+HEX_SIZE/4;
        int x = getGridX(m.getX())+HEX_SIZE/4;
        if (m.getY() % 2 != 0) {
            x = getGridX(m.getX())+HEX_SIZE/4*3;
        }
        return new Point(x,y);
    }

    //Credit: https://www.redblobgames.com/grids/hexagons/#hex-to-pixel for logic of hex to pixels
    int getGridY(int y) {
        return HEX_SIZE/2 * 3/2 * y;
    }

    int getGridX(int x) {
        return x * HEX_SIZE + HEX_SIZE;
    }

    void fillHexagon(Graphics g, int x, int y, Color c) {
        int nPoints = 6;
        int[] xPoints = {x, x+HEX_SIZE/2, x+HEX_SIZE, x+HEX_SIZE, x+HEX_SIZE/2, x, x};
        int[] yPoints = {y+HEX_SIZE/4, y, y+HEX_SIZE/4, y+HEX_SIZE/4*3, y+HEX_SIZE, y+HEX_SIZE/4*3, y+HEX_SIZE/4};
        g.setColor(c);
        g.fillPolygon(xPoints, yPoints, nPoints);
    }

    void drawHexagon(Graphics g, int x, int y, Color c, int strokeSize) {
        Graphics2D g2d = (Graphics2D) g;
        int nPoints = 6;
        int[] xPoints = {x, x+HEX_SIZE/2, x+HEX_SIZE, x+HEX_SIZE, x+HEX_SIZE/2, x, x};
        int[] yPoints = {y+HEX_SIZE/4, y, y+HEX_SIZE/4, y+HEX_SIZE/4*3, y+HEX_SIZE, y+HEX_SIZE/4*3, y+HEX_SIZE/4};
        g2d.setColor(c);
        g2d.setStroke(new BasicStroke(strokeSize));
        g2d.drawPolygon(xPoints, yPoints, nPoints);
    }

}
