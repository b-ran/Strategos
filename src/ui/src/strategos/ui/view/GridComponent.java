package strategos.ui.view;

import strategos.model.MapLocation;
import strategos.units.*;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static strategos.ui.config.Config.*;


/**
 * Used to create grid that the board and units are drawn on
 * @author Brandon Scott-Hill
 */

public class GridComponent extends JComponent {

    private final View view;
    private MapLocation[][] terrain;
    private List<Unit> entities;
    private Draw draw;

    private MapLocation selectedMapLocation;

    private List<Unit> unitsInAttackRange = new ArrayList<>(); //Attackable units in range of selected map location
    private List<MapLocation> tilesInRange = new ArrayList<>(); //Tiles in range of selected map location

    /**
     * Instantiates a new Grid component for drawing on.
     */
    GridComponent(View view) {
        this.view = view;
        setLayout(new BorderLayout());
        setPreferredSize(GRID_COMPONENT_SIZE);
        draw = new Draw(view);
    }

    /**
     * Gets a layered grid panel.
     *
     * @author Brandon Scott-Hill
     * @return the grid
     */
    JLayeredPane getGrid() {
        JLayeredPane p = new JLayeredPane();
        p.setLayout(new BorderLayout());
        p.setPreferredSize(GRID_COMPONENT_SIZE);
        return p;
    }

    @Override
    protected void paintComponent(Graphics g) {
        updateHexSize();
        paintBackground(g);
        paintTerrain(g, view.getSeenTerrain());
        paintSelection(g, selectedMapLocation);
        paintUnits(g, entities);
        paintFogTerrain(g, terrain);

    }

    /**
     * Updates the hex size to fit in grid size.
     *
     * @author Brandon Scott-Hill
     */
    private void updateHexSize() {
        int screenWidth = getWidth();
        int size = view.model.getWorld().getMap().getDiameter();
        size += Math.round(size/2.0);
        HEX_SIZE = screenWidth / size;
    }

    /**
     * Draws the background image
     *
     * @author Brandon Scott-Hill
     * @param g the Graphics
     */
    private void paintBackground(Graphics g) {
        g.drawImage(Draw.backgroundImage, 0, 0, null);
    }


    /**
     * Draws all units in a collection
     *
     * @author Brandon Scott-Hill
     * @param g the Graphics
     * @param entities the Units to draw
     */
    private void paintUnits(Graphics g, List<Unit> entities) {
        for (Unit unit : entities) {
            if (!view.getSeenTerrain().contains(unit.getPosition())) {
                continue;
            }
            Point p = new Point();
            p.x = unit.getPosition().getX();
            p.y = unit.getPosition().getY();
            draw.drawUnit(unit, p, g);
        }
    }

    /**
     * Draws all visible tiles
     *
     * @author Brandon Scott-Hill
     * @param g the Graphics
     * @param visibleTiles the visibleTiles to draw
     */
    private void paintTerrain(Graphics g, List<MapLocation> visibleTiles) {
        for (MapLocation tile : visibleTiles) {
            if (tile.getTerrain() == null) continue;
            Point p = new Point();
            p.x = tile.getX();
            p.y = tile.getY();
            draw.drawTerrain(tile.getTerrain(), p, g);
        }
    }

    /**
     * Draws all terrain as fog if they have not being seen
     *
     * @author Brandon Scott-Hill
     * @param g the Graphics
     * @param terrain the visibleTiles to draw
     */
    private void paintFogTerrain(Graphics g, MapLocation[][] terrain) {
        for (int y = 0; y < terrain.length; y++) {
            for (int x = 0; x < terrain[0].length; x++) {
                if (view.getSeenTerrain().contains(terrain[y][x])) continue;
                Point p = new Point();
                p.x = terrain[y][x].getX();
                p.y = terrain[y][x].getY();
                draw.drawFog(p, g);
            }
        }
    }

    /**
     * Draws all the selection of the user
     *
     * @author Brandon Scott-Hill
     * @param g the Graphics
     * @param selectedMapLocation the selected location the board
     */
    private void paintSelection(Graphics g, MapLocation selectedMapLocation) {
        if (selectedMapLocation == null) return;
        Point p = new Point();
        for (MapLocation m : tilesInRange) {
            p.x = m.getX();
            p.y = m.getY();

            draw.drawTerrainSelection(m.getTerrain(), p, SELECTION_MOVE_COLOR,  SELECTION_STROKE_SIZE, g);
        }
        for (Unit u : unitsInAttackRange) {
            MapLocation m = u.getPosition();
            p.x = m.getX();
            p.y = m.getY();

            draw.drawTerrainSelection(m.getTerrain(), p, SELECTION_ATTACK_COLOR,  SELECTION_STROKE_SIZE, g);

        }
        MapLocation m = selectedMapLocation;
        p.x = m.getX();
        p.y = m.getY();
        draw.drawTerrainSelection(m.getTerrain(), p, SELECTION_COLOR,  SELECTION_STROKE_SIZE, g);
    }

    /**
     * Sets entities.
     *
     * @author Brandon Scott-Hill
     * @param entities the entities
     */
    public void setEntities(List<Unit> entities) {
        this.entities = entities;
    }

    /**
     * Gets entities.
     *
     * @author Brandon Scott-Hill
     * @return the entities
     */
    List<Unit> getEntities() {
        return entities;
    }

    /**
     * Sets terrain.
     *
     * @author Brandon Scott-Hill
     * @param terrain the terrain
     */
    public void setTerrain(MapLocation[][] terrain) {
        this.terrain = terrain;
//        revealMap();
    }


    /**
     * Sets selection.
     *
     * @author Brandon Scott-Hill
     * @param selectedMapLocation the selected map location
     * @param unitsInAttackRange  the units in attack range
     * @param tilesInRange        the tiles in range
     */
    public void setSelection(MapLocation selectedMapLocation, List<Unit> unitsInAttackRange, List<MapLocation> tilesInRange) {
        this.selectedMapLocation = selectedMapLocation;
        this.unitsInAttackRange = unitsInAttackRange;
        this.tilesInRange = tilesInRange;
    }

    /**
     * Sets selection.
     *
     * @author Brandon Scott-Hill
     * @param selectedMapLocation the selected map location
     */
    public void setSelection(MapLocation selectedMapLocation) {
        this.selectedMapLocation = selectedMapLocation;
        unitsInAttackRange = new ArrayList<>();
        tilesInRange = new ArrayList<>();
    }

    /**
     * Reveal map.
     * @author Brandon Scott-Hill
     */
    void revealMap() {
         for (MapLocation[] aTerrain : terrain) {
             for (int x = 0; x < terrain[0].length; x++) {
                 view.getSeenTerrain().add(aTerrain[x]);
             }
         }

    }
}
