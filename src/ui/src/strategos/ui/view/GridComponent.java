package strategos.ui.view;

import strategos.MapLocation;
import strategos.terrain.*;
import strategos.units.*;

import javax.swing.*;

import java.awt.*;
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
        paintTerrain(g, seenTerrain);
        paintUnits(g, entities);
        paintSelection((Graphics2D) g, selectedMapLocation);

    }

    private void paintUnits(Graphics g, List<Unit> entities) {
        for (Unit unit : entities) {
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
                if (y % 2 == 0) {
                    drawEntity.fillHexagon(g, drawEntity.getGridX(x), drawEntity.getGridY(y), Color.BLACK);
                } else {
                    drawEntity.fillHexagon(g, drawEntity.getGridX(x)+HEX_SIZE/2, drawEntity.getGridY(y), Color.BLACK);
                }
            }
        }
    }

    private void paintSelection(Graphics2D g, MapLocation selectedMapLocation) {
        if (selectedMapLocation == null) return;
        Point p = drawEntity.getTerrainGridPos(selectedMapLocation);
        drawEntity.drawHexagon((Graphics2D) g, p.x, p.y,SELECTION_COLOR, SELECTION_STROKE_SIZE);
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

    public void setSelectedMapLocation(MapLocation selectedMapLocation) {
        this.selectedMapLocation = selectedMapLocation;
    }
}
