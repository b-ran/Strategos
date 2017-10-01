package strategos.ui.view;

import strategos.MapLocation;
import strategos.terrain.*;
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



    private MapLocation[][] terrain;
    private MapLocation[][] seenTerrain;
    private List<Unit> entities;
    private DrawEntity drawEntity = new DrawEntity();
    private MapLocation selectedMapLocation;
    private List<Unit> selectedUnitsInRange = new ArrayList<>();
    private List<MapLocation> selectedTilesInRange = new ArrayList<>();

    /**
     * Instantiates a new Grid component for drawing on.
     */
     GridComponent() {
        setLayout(new BorderLayout());
        setPreferredSize(GRID_COMPONENT_SIZE);
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
        paintBlackTerrain(g, terrain);
        //TODO: view range UnitOwner.getVisibleTiles()
        paintTerrain(g, seenTerrain);
        paintUnits(g, entities);
        paintSelection(g, selectedMapLocation);

    }

    private void paintUnits(Graphics g, List<Unit> entities) {
        for (Unit unit : entities) {
            /*
            TODO - REVIEW: This could be made far neater by, when sprites are implemented, to change draw() to
            TODO            make draw() take an image and call unit.getSprite(). Then there is no need to use
            TODO            instanceof. This can be similarly neatened with paintTerrain()
             */
            if (unit instanceof Archers) {
                drawEntity.draw((Archers)unit, g);
            } else if (unit instanceof Cavalry) {
                drawEntity.draw((Cavalry)unit, g);
            } else if (unit instanceof Elite) {
                drawEntity.draw((Elite)unit, g);
            } else if (unit instanceof Spearmen) {
                drawEntity.draw((Spearmen)unit, g);
            } else if (unit instanceof Swordsmen) {
                drawEntity.draw((Swordsmen)unit, g);
            }
         }
    }

    private void paintTerrain(Graphics g, MapLocation[][] terrain) {
        for (int y = 0; y < terrain.length; y++) {
            for (int x = 0; x < terrain[0].length; x++) {
                Terrain t = terrain[y][x].getTerrain();
                if (t instanceof Forest) {
                    drawEntity.draw((Forest)t, g, x, y);
                } else if (t instanceof Hill) {
                    drawEntity.draw((Hill)t, g, x, y);
                } else if (t instanceof Mountain) {
                    drawEntity.draw((Mountain)t, g, x, y);
                } else if (t instanceof Plains) {
                    drawEntity.draw((Plains)t, g, x, y);
                } else if (t instanceof River) {
                    drawEntity.draw((River)t, g, x, y);
                }
            }
        }
    }

    private void paintBlackTerrain(Graphics g, MapLocation[][] terrain) {
        g.setColor(Color.BLACK);
        for (int y = 0; y < terrain.length; y++) {
            for (int x = 0; x < terrain[0].length; x++) {
                drawEntity.fillHexagon(g, drawEntity.getGridX(x)+ ((y % 2 == 0) ? 0 : HEX_SIZE/2), drawEntity.getGridY(y), Color.BLACK);
            }
        }
    }

    private void paintSelection(Graphics g, MapLocation selectedMapLocation) {
        if (selectedMapLocation == null) return;
        Point p;
        for (MapLocation m :selectedTilesInRange) {
            p = drawEntity.getTerrainGridPos(m);
            drawEntity.drawHexagon(g, p.x, p.y, SELECTION_MOVE_COLOR, SELECTION_STROKE_SIZE);
        }
        for (Unit u :selectedUnitsInRange) {
            MapLocation m = u.getPosition();
            p = drawEntity.getTerrainGridPos(m);
            drawEntity.drawHexagon(g, p.x, p.y, SELECTION_ATTACK_COLOR, SELECTION_STROKE_SIZE);
        }
        p = drawEntity.getTerrainGridPos(selectedMapLocation);
        drawEntity.drawHexagon(g, p.x, p.y, SELECTION_COLOR, SELECTION_STROKE_SIZE);
    }

    /**
     * Sets entities.
     *
     * @param entities the entities
     */
    public void setEntities(List<Unit> entities) {
        this.entities = entities;
    }

    /**
     * Sets terrain.
     *
     * @param terrain the terrain
     */
    public void setTerrain(MapLocation[][] terrain) {
        this.terrain = terrain;
        //seenTerrain = new Terrain[terrain.length][terrain[0].length];
        seenTerrain = terrain;
    }

    public void setSelection(MapLocation selectedMapLocation, List<Unit> selectedUnitsInRange, List<MapLocation> selectedTilesInRange) {
        this.selectedMapLocation = selectedMapLocation;
        this.selectedUnitsInRange = selectedUnitsInRange;
        this.selectedTilesInRange = selectedTilesInRange;
    }

    public void setSelection(MapLocation selectedMapLocation) {
        this.selectedMapLocation = selectedMapLocation;
        selectedUnitsInRange = new ArrayList<>();
        selectedTilesInRange = new ArrayList<>();
    }
}
