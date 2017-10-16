package strategos.ui.view;

import strategos.model.MapLocation;
import strategos.units.*;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static strategos.ui.config.Config.*;

/**
 * The type Grid component.
 */
public class GridComponent extends JComponent {

    private final View view;
    private MapLocation[][] terrain;
    private List<Unit> entities;
    private Draw draw;
    private MapLocation selectedMapLocation;
    private List<Unit> unitsInAttackRange = new ArrayList<>();
    private List<MapLocation> tilesInRange = new ArrayList<>();

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
     * Gets grid panel.
     *
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
        paintBackground(g);
        paintTerrain(g, view.getSeenTerrain());
        paintSelection(g, selectedMapLocation);
        paintUnits(g, entities);
        paintBlackTerrain(g, terrain);

    }

    private void paintBackground(Graphics g) {
        g.drawImage(Draw.backgroundImage, 0, 0, null);
    }

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

    private void paintTerrain(Graphics g, List<MapLocation> visibleTiles) {
        for (MapLocation tile : visibleTiles) {
            if (tile.getTerrain() == null) continue;
            Point p = new Point();
            p.x = tile.getX();
            p.y = tile.getY();
            draw.drawTerrain(tile.getTerrain(), p, g);
        }
    }

    private void paintBlackTerrain(Graphics g, MapLocation[][] terrain) {
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
     * @param entities the entities
     */
    public void setEntities(List<Unit> entities) {
        this.entities = entities;
    }

    public List<Unit> getEntities() {
        return entities;
    }

    /**
     * Sets terrain.
     *
     * @param terrain the terrain
     */
    public void setTerrain(MapLocation[][] terrain) {
        this.terrain = terrain;
        revealMap();
    }


    public void setSelection(MapLocation selectedMapLocation, List<Unit> unitsInAttackRange, List<MapLocation> tilesInRange) {
        this.selectedMapLocation = selectedMapLocation;
        this.unitsInAttackRange = unitsInAttackRange;
        this.tilesInRange = tilesInRange;
    }

    public void setSelection(MapLocation selectedMapLocation) {
        this.selectedMapLocation = selectedMapLocation;
        unitsInAttackRange = new ArrayList<>();
        tilesInRange = new ArrayList<>();
    }

    public void revealMap() {
        for (int y = 0; y < terrain.length; y++) {
            for (int x = 0; x < terrain[0].length; x++) {
                view.getSeenTerrain().add(terrain[y][x]);
            }
        }

    }
}
