package strategos.ui.view;


import strategos.GameObject;
import strategos.GameObjectVisitor;
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

/**
 * Creates the Draw Object.
 *
 * @author Brandon Scott-Hill
 */
public class Draw implements GameObjectVisitor {

    private static boolean loadImages = true; //Toggle if loading images need to be done

    private static BufferedImage forestImage = null;
    private static BufferedImage hillImage = null;
    private static BufferedImage mountainsImage = null;
    private static BufferedImage plainsImage = null;
    private static BufferedImage riverImage = null;
    private static BufferedImage fogImage = null;


    private static BufferedImage[] bridgeImage = new BufferedImage[3];
    private static BufferedImage[] archersImage = new BufferedImage[3];
    private static BufferedImage[] cavalryImage = new BufferedImage[3];
    private static BufferedImage[] eliteImage = new BufferedImage[3];
    private static BufferedImage[] spearmenImage = new BufferedImage[3];
    private static BufferedImage[] swordsmenImage = new BufferedImage[3];
    private static BufferedImage healthPotionImage = null;

    /**
     * The Background image.
     */
    static BufferedImage backgroundImage = null;

    private View view = null;

    private Graphics2D g2d = null;
    private Point p = null;
    private Color selectionColor = null;
    private float selectionStrokeSize = 0;
    private Point hexPoint = null;


    /**
     * Instantiates a new Draw Object.
     *
     * @author Brandon Scott-Hill
     *
     * @param view the view
     */
    Draw(View view) {
        this.view = view;
        loadImages();
    }


    /**
     * Loads all the images.
     *
     * @author Brandon Scott-Hill
     *
     */
    private void loadImages() {
        if (!loadImages) return;
        bridgeImage = loadUnitImage(BRIDGE_IMAGE_PATH);
        forestImage = loadImage(FOREST_IMAGE_PATH);
        hillImage = loadImage(HILL_IMAGE_PATH);
        mountainsImage = loadImage(MOUNTAINS_IMAGE_PATH);
        plainsImage = loadImage(PLAINS_IMAGE_PATH);
        riverImage = loadImage(RIVER_IMAGE_PATH);
        fogImage = loadImage(FOG_IMAGE_PATH);

        archersImage = loadUnitImage(ARCHERS_IMAGE_PATH);
        cavalryImage = loadUnitImage(CAVALRY_IMAGE_PATH);
        eliteImage = loadUnitImage(ELITE_IMAGE_PATH);
        spearmenImage = loadUnitImage(SPEARMEN_IMAGE_PATH);
        swordsmenImage = loadUnitImage(SWORDSMEN_IMAGE_PATH);
        healthPotionImage = loadImage(HEALTH_POTION_IMAGE_PATH);

        backgroundImage = loadImage(BACKGROUND_IMAGE_PATH);
        loadImages = false;
    }

    /**
     * Load the different images related to a unit type.
     *
     * @author Brandon Scott-Hill
     *
     * @param path the file path to the unit images
     */
    private BufferedImage[] loadUnitImage(String path) {
        BufferedImage[] image = new BufferedImage[3];
        for (int i = 0; i < image.length; i++) {
            image[i] = loadImage(String.format(path, UNIT_TAGS[i]));
        }
        return image;
    }

    /**
     * Get the correct unit image based on the ownership of the unit.
     *
     * @author Brandon Scott-Hill
     *
     * @param u the unit to get the image for
     * @param images the images to pick from
     */
    private BufferedImage getUnitOwnerImage(Unit u, BufferedImage[] images) {
        if (u.getOwner().equals(view.getUiOwner())) {
            return images[0];
        } else if (u.getOwner().isNPC()) {
            return images[2];
        } else {
            return images[1];
        }
    }

    /**
     * Loads a image from a path.
     *
     * @author Brandon Scott-Hill
     *
     * @param path the file path to load the image from
     */
    private BufferedImage loadImage(String path) {
        if (!loadImages) return null;
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(path);
        BufferedImage image = null;
        try {
            image = ImageIO.read(stream);
        } catch (IOException e) {
            System.out.println(path);
            System.out.println(stream.toString());
        }
        return image;
    }


    /**
     * Draws a terrain object on the grid.
     *
     * @author Brandon Scott-Hill
     *
     * @param t the terrain to draw
     * @param p the point on the grid to draw to
     * @param g the graphics to draw on
     */
    void drawTerrain(Terrain t, Point p, Graphics g) {
        g2d = (Graphics2D) g;
        this.p = getTerrainGridPos(p);
        ((GameObject) t).accept(this);
    }

    /**
     * Draw a unit object on the grid.
     *
     * @author Brandon Scott-Hill
     *
     * @param u the unit to draw
     * @param p the point on the grid to draw to
     * @param g the graphics to draw on
     */
    void drawUnit(Unit u, Point p, Graphics g) {
        g2d = (Graphics2D) g;
        this.hexPoint = p;
        this.p = getUnitGridPos(p);
        ((GameObject) u).accept(this);
        drawHealth(u);
    }

    /**
     * Draw a the health of a unit object on the grid.
     *
     * @author Brandon Scott-Hill
     *
     * @param u the unit health to draw
     */
    private void drawHealth(Unit u) {
        int health = u.getHitpoints();

        Double d = (((double) health) / 100d)*HEX_SIZE/3;
        Integer height = d.intValue();

        Color c = HEALTH_HIGH_COLOR;

        if (health <= HEALTH_LOW_PERCENTAGE) {
            c = HEALTH_LOW_COLOR;
        } else if (health <= HEALTH_MID_PERCENTAGE) {
            c = HEALTH_MID_COLOR;
        }

        g2d.setColor(c);
        g2d.fillRect(p.x+HEALTH_BAR_RELATIVE_POSITION.x, p.y+HEX_SIZE/3, HEALTH_BAR_SIZE.width, height);
    }

    /**
     * Draws a terrain object non bound to the grid.
     *
     * @author Brandon Scott-Hill
     *
     * @param t the terrain to draw
     * @param p the point on the graphics to draw to
     * @param g the graphics to draw on
     */
    void drawTerrainNonGrid(Terrain t, Point p, Graphics g) {
        g2d = (Graphics2D) g;
        this.p = p;
        ((GameObject) t).accept(this);
    }

    /**
     * Draw a unit object  non bound to the grid.
     *
     * @author Brandon Scott-Hill
     *
     * @param u the unit to draw
     * @param p the point on the graphics to draw to
     * @param g the graphics to draw on
     */
    void drawUnitNonGrid(Unit u, Point p, Graphics g) {
        g2d = (Graphics2D) g;
        this.p = p;
        ((GameObject) u).accept(this);
    }

    /**
     * Draws the terrain selection.
     *
     * @author Brandon Scott-Hill
     *
     * @param t the selected terrain
     * @param p the point of selection
     * @param c the colour to draw the selection
     * @param s the size of the stroke
     * @param g the graphics to draw on
     */
    void drawTerrainSelection(Terrain t, Point p, Color c, Float s, Graphics g) {
        g2d = (Graphics2D) g;
        this.p = getTerrainGridPos(p);
        selectionColor = c;
        selectionStrokeSize = s;
        ((GameObject) t).accept(this);
        selectionColor = Color.BLACK;
        selectionStrokeSize = 0;
    }

    /**
     * Draws the fog.
     *
     * @author Brandon Scott-Hill
     *
     * @param point the point to draw the fog
     * @param g     the graphics to draw on
     */
    void drawFog(Point point, Graphics g) {
        g2d = (Graphics2D) g;
        this.p = getTerrainGridPos(point);
        g2d.drawImage(getTexturedImage(fogImage, getHexagon(p)), p.x, p.y , null);
    }



    /**
     * Gets a clipped hexagon image.
     *
     * @author Brandon Scott-Hill
     *
     * @param image the image to clip
     * @param hex   the hexagon shape to clip the texture to
     */
    //Credit for solution drawing inside a hexagon https://stackoverflow.com/questions/21632411/texture-background-image-for-polygon
    private BufferedImage getTexturedImage(BufferedImage image, Shape hex) {
        Rectangle bounds = hex.getBounds();
        BufferedImage texture = new BufferedImage(bounds.width+2, bounds.height+2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = texture.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        AffineTransform centerTransform = AffineTransform.getTranslateInstance(-bounds.x+1, -bounds.y+1); //Moves image to centre of hexagon
        g.setTransform(centerTransform);
        g.setClip(hex); //Clips the graphics to the hexagon

        image.getScaledInstance(bounds.width, bounds.height, Image.SCALE_DEFAULT);

        if (selectionStrokeSize <= 0) {
            g.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height, null);
        }
        g.setClip(null);


        g.setColor(selectionColor);
        if (selectionStrokeSize > 0) {
            g.setStroke(new BasicStroke(selectionStrokeSize));
            g.draw(hex);
        }

        g.dispose();

        return texture;
    }

    /**
     * Gets a hexagons at a point
     *
     * @author Brandon Scott-Hill
     *
     * @param p the point to offset the hexagon
     */
    private Shape getHexagon(Point p) {
        int nPoints = 6;
        int[] xPoints = {p.x, p.x+HEX_SIZE/2, p.x+HEX_SIZE, p.x+HEX_SIZE, p.x+HEX_SIZE/2, p.x, p.x};
        int[] yPoints = {p.y+HEX_SIZE/4, p.y, p.y+HEX_SIZE/4, p.y+HEX_SIZE/4*3, p.y+HEX_SIZE, p.y+HEX_SIZE/4*3, p.y+HEX_SIZE/4};
        Shape hex = new Polygon(xPoints,yPoints,nPoints);
        return hex;
    }


    @Override
    public void visit(Forest forest) {
        g2d.drawImage(getTexturedImage(forestImage, getHexagon(p)), p.x, p.y , null);
    }

    @Override
    public void visit(Hill hill) {
        g2d.drawImage(getTexturedImage(hillImage, getHexagon(p)), p.x, p.y , null);
    }

    @Override
    public void visit(Mountain Mountain) {
        g2d.drawImage(getTexturedImage(mountainsImage, getHexagon(p)), p.x, p.y , null);
    }

    @Override
    public void visit(Plains plains) {
        g2d.drawImage(getTexturedImage(plainsImage, getHexagon(p)), p.x, p.y , null);
    }

    @Override
    public void visit(River river) {
        g2d.drawImage(getTexturedImage(riverImage, getHexagon(p)), p.x, p.y , null);
    }

    @Override
    public void visit(Archers archers) {
        g2d.drawImage(getTexturedImage(getUnitOwnerImage(archers,archersImage), getHexagon(p)), p.x, p.y, null);
    }

    @Override
    public void visit(Bridge bridge) {
        g2d.drawImage(getTexturedImage(getUnitOwnerImage(bridge, bridgeImage), getHexagon(p)), p.x, p.y, null);
    }

    @Override
    public void visit(Cavalry cavalry) {
        g2d.drawImage(getTexturedImage(getUnitOwnerImage(cavalry,cavalryImage), getHexagon(p)), p.x, p.y , null);
    }

    @Override
    public void visit(Elite elite) {
        g2d.drawImage(getTexturedImage(getUnitOwnerImage(elite, eliteImage), getHexagon(p)), p.x, p.y , null);
    }

    @Override
    public void visit(HealthPotion healthPotion) {
        g2d.drawImage(getTexturedImage(healthPotionImage, getHexagon(p)), p.x, p.y , null);
    }

    @Override
    public void visit(Spearmen spearmen) {
        g2d.drawImage(getTexturedImage(getUnitOwnerImage(spearmen, spearmenImage), getHexagon(p)), p.x, p.y , null);
    }

    @Override
    public void visit(Swordsmen swordsmen) {
        g2d.drawImage(getTexturedImage(getUnitOwnerImage(swordsmen, swordsmenImage), getHexagon(p)), p.x, p.y , null);
    }

    //Credit: https://www.redblobgames.com/grids/hexagons/#hex-to-pixel for logic of hex to pixels
    private int getGridY(int y) {
        return HEX_SIZE/2 * 3/2 * y;
    }

    private int getGridX(int x) {
        return x * HEX_SIZE + HEX_SIZE;
    }

    private Point getUnitGridPos(Point p) {
        int y = getGridY(p.y);
        int  x = getGridX(p.x)+p.y*HEX_SIZE/2;
        return new Point(x,y);
    }

    private Point getTerrainGridPos(Point p) {
        int y = getGridY(p.y);
        int x = getGridX(p.x)+(p.y*HEX_SIZE/2);
        return new Point(x,y);
    }

}
