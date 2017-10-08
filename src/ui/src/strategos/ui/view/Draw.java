package strategos.ui.view;


import strategos.Graphical;
import strategos.GraphicalVisitor;
import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.terrain.*;
import strategos.units.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static strategos.ui.config.Config.*;
import static strategos.ui.config.ConfigImage.*;

public class Draw implements GraphicalVisitor{


    private static boolean loadImages = true;

    private static BufferedImage bridgeImage = null;
    private static BufferedImage forestImage = null;
    private static BufferedImage hillImage = null;
    private static BufferedImage mountainsImage = null;
    private static BufferedImage plainsImage = null;
    private static BufferedImage riverImage = null;
    private static BufferedImage fogImage = null;
    private View view;

    private Graphics2D g2d = null;
    private Point p = null;
    private Color selectionColor = null;
    private float selectionStrokeSize = 0;
    private Point hexPoint;


    public Draw(View view) {
        this.view = view;
        loadImages();
    }

    private void loadImages() {
        if (!loadImages) return;
        bridgeImage = loadImage(BRIDGE_IMAGE_PATH);
        forestImage = loadImage(FOREST_IMAGE_PATH);
        hillImage = loadImage(HILL_IMAGE_PATH);
        mountainsImage = loadImage(MOUNTAINS_IMAGE_PATH);
        plainsImage = loadImage(PLAINS_IMAGE_PATH);
        riverImage = loadImage(RIVER_IMAGE_PATH);
        fogImage = loadImage(FOG_IMAGE_PATH);
        loadImages = false;
    }


    private BufferedImage loadImage(String path) {
        if (!loadImages) return null;
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(path);
        BufferedImage image = null;
        try {
            image = ImageIO.read(stream);
        } catch (IOException e) {
            System.out.println(stream.toString());
        }
        return image;
    }

    void drawTerrain(Terrain t, Point p, Graphics g) {
        g2d = (Graphics2D) g;
        this.p = getTerrainGridPos(p);
        ((Graphical) t).draw(this);
    }

    void drawUnit(Unit u, Point p, Graphics g) {
        g2d = (Graphics2D) g;
        this.hexPoint = p;
        this.p = getUnitGridPos(p);
        ((Graphical) u).draw(this);
    }

    void drawTerrainNonGrid(Terrain t, Point p, Graphics g) {
        g2d = (Graphics2D) g;
        this.p = p;
        ((Graphical) t).draw(this);
    }

    void drawUnitNonGrid(Unit u, Point p, Graphics g) {
        g2d = (Graphics2D) g;
        this.p = p;
        ((Graphical) u).draw(this);
    }

    void drawTerrainSelection(Terrain t, Point p, Color c, Float s, Graphics g) {
        g2d = (Graphics2D) g;
        this.p = getTerrainGridPos(p);
        selectionColor = c;
        selectionStrokeSize = s;
        ((Graphical) t).draw(this);
        selectionColor = Color.BLACK;
        selectionStrokeSize = 0;
    }

    void drawFog(Point point, Graphics g) {
        g2d = (Graphics2D) g;
        this.p = getTerrainGridPos(point);
        g2d.drawImage(getTexturedImage(fogImage, getHexagon(p), p), p.x, p.y , null);
    }

    //Credit for solution drawing inside a hexagon https://stackoverflow.com/questions/21632411/texture-background-image-for-polygon
    private BufferedImage getTexturedImage(BufferedImage image, Shape hex, Point p) {
        Rectangle bounds = hex.getBounds();
        BufferedImage texture = new BufferedImage(bounds.width+2, bounds.height+2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = texture.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        AffineTransform centerTransform = AffineTransform.getTranslateInstance(-bounds.x+1, -bounds.y+1);
        g.setTransform(centerTransform);
        g.setClip(hex);

        image.getScaledInstance(bounds.width, bounds.height, Image.SCALE_DEFAULT);

        g.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height,null);
        g.setClip(null);


        g.setColor(selectionColor);
        if (selectionStrokeSize > 0) {
            g.setStroke(new BasicStroke(selectionStrokeSize));
            g.draw(hex);
        }

        g.dispose();

        return texture;
    }

    private Shape getHexagon(Point p) {
        int nPoints = 6;
        int[] xPoints = {p.x, p.x+HEX_SIZE/2, p.x+HEX_SIZE, p.x+HEX_SIZE, p.x+HEX_SIZE/2, p.x, p.x};
        int[] yPoints = {p.y+HEX_SIZE/4, p.y, p.y+HEX_SIZE/4, p.y+HEX_SIZE/4*3, p.y+HEX_SIZE, p.y+HEX_SIZE/4*3, p.y+HEX_SIZE/4};
        Shape hex = new Polygon(xPoints,yPoints,nPoints);
        return hex;
    }


    @Override
    public void visit(Forest forest) {
        g2d.drawImage(getTexturedImage(forestImage, getHexagon(p), p), p.x, p.y , null);
    }

    @Override
    public void visit(Hill hill) {
        g2d.drawImage(getTexturedImage(hillImage, getHexagon(p), p), p.x, p.y , null);
    }

    @Override
    public void visit(Mountain Mountain) {
        g2d.drawImage(getTexturedImage(mountainsImage, getHexagon(p), p), p.x, p.y , null);
    }

    @Override
    public void visit(Plains plains) {
        g2d.drawImage(getTexturedImage(plainsImage, getHexagon(p), p), p.x, p.y , null);
    }

    @Override
    public void visit(River river) {
        g2d.drawImage(getTexturedImage(riverImage, getHexagon(p), p), p.x, p.y , null);
    }

    @Override
    public void visit(Archers archers) {
        setUnitColor(archers);
        g2d.fillOval(p.x, p.y , HEX_SIZE/2, HEX_SIZE/2);
        g2d.setColor(UNIT_FONT_COLOR);
        g2d.drawString(UNIT_ARCHERS_LETTER, p.x, p.y);
    }

    @Override
    public void visit(Bridge bridge) {
        Point p = getTerrainGridPos((hexPoint == null) ? this.p : hexPoint);
        g2d.drawImage(getTexturedImage(bridgeImage, getHexagon(p), p), p.x, p.y, null);
        g2d.setColor(UNIT_FONT_COLOR);
        g2d.drawString(UNIT_BRIDGE_LETTER, p.x, p.y);
    }

    @Override
    public void visit(Cavalry cavalry) {
        setUnitColor(cavalry);
        g2d.fillOval(p.x, p.y , HEX_SIZE/2, HEX_SIZE/2);
        g2d.setColor(UNIT_FONT_COLOR);
        g2d.drawString(UNIT_CAVALRY_LETTER, p.x, p.y);
    }

    @Override
    public void visit(Elite elite) {
        setUnitColor(elite);
        g2d.fillOval(p.x, p.y , HEX_SIZE/2, HEX_SIZE/2);
        g2d.setColor(UNIT_FONT_COLOR);
        g2d.drawString(UNIT_ELITE_LETTER, p.x, p.y);
    }

    @Override
    public void visit(HealthPotion healthPotion) {

    }

    @Override
    public void visit(Spearmen spearmen) {
        setUnitColor(spearmen);
        g2d.fillOval(p.x, p.y , HEX_SIZE/2, HEX_SIZE/2);
        g2d.setColor(UNIT_FONT_COLOR);
        g2d.drawString(UNIT_SPEARMEN_LETTER, p.x, p.y);
    }

    @Override
    public void visit(Swordsmen swordsmen) {
        g2d.fillOval(p.x, p.y , HEX_SIZE/2, HEX_SIZE/2);
        g2d.setColor(UNIT_FONT_COLOR);
        g2d.drawString(UNIT_SWORDSMEN_LETTER, p.x, p.y);
    }

    private void setUnitColor(Unit unit) {
        UnitOwner owner = unit.getOwner();
        if (owner.isNPC()) {
            g2d.setColor(NPC_COLOR);
        } else if (view.getUiOwner().getUnits().contains(unit)) {
            g2d.setColor(PLAYER_COLOR);
        } else {
            g2d.setColor(OTHER_PLAYER_COLOR);
        }
    }

    //Credit: https://www.redblobgames.com/grids/hexagons/#hex-to-pixel for logic of hex to pixels
    private int getGridY(int y) {
        return HEX_SIZE/2 * 3/2 * y;
    }

    private int getGridX(int x) {
        return x * HEX_SIZE + HEX_SIZE;
    }

    private Point getUnitGridPos(Point p) {
        int y = getGridY(p.y)+HEX_SIZE/4;
        int x = getGridX(p.x)+HEX_SIZE/4;
        if (y != 0) {
            x = getGridX(p.x)+p.y*HEX_SIZE/2+HEX_SIZE/4;
        }
        return new Point(x,y);
    }

    private Point getTerrainGridPos(Point p) {
        int y = getGridY(p.y);
        int x = getGridX(p.x);
        if (y != 0) {
            x = getGridX(p.x)+(p.y*HEX_SIZE/2);
        }
        return new Point(x,y);
    }


}
