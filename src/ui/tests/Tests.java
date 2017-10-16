import model.*;
import org.junit.jupiter.api.Test;
import strategos.model.MapLocation;
import strategos.terrain.Terrain;
import strategos.ui.Ui;
import strategos.units.Unit;
import terrain.*;
import units.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tests extends JComponent {

    private JFrame frame = new JFrame();
    private String text = "";
    private Ui ui;

    private int screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();

    public Tests() {
        frame.add(this);
        frame.setTitle("Debug View");
        frame.setPreferredSize(new Dimension(800,200));
        frame.setLocation(0, screenHeight -300);
        frame.pack();
        frame.setVisible(true);
    }

    private void setText(String correctOutput) {
        text = correctOutput;
        frame.repaint();
    }

    private void waitToClose() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ui.exit();
        frame.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF,1,16));
        g.drawString(text,50,50);
    }

    @Test
    public void test01_display_menu() {
        List<Unit> entities = new ArrayList<>();
        Terrain[][] terrain = {
                {new MountainTestObj(), new MountainTestObj()},
                {new MountainTestObj(), new MountainTestObj()}
        };
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        setText("You should be able to see a menu");
        ui = new Ui(setupModel(entities,terrain,owner));
        ui.disableInput();
        ui.revealMap();
        waitToClose();
    }

    @Test
    public void test02_display_hexagon() {
        List<Unit> entities = new ArrayList<>();
        Terrain[][] terrain = {
                {new MountainTestObj()}
        };
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        setText("You should be able to see a hexagon");
        ui = new Ui(setupModel(entities,terrain,owner));
        ui.disableInput();
        ui.skipMenu();
        ui.revealMap();
        waitToClose();
    }

    @Test
    public void test03_display_archer_player() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        ArchersTestObj unit = new ArchersTestObj(owner);
        createAUnit(unit, owner, owner,"Should be able to see a archer belonging to client's player");
    }

    @Test
    public void test04_display_cavalry_player() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        CavalryTestObj unit = new CavalryTestObj(owner);
        createAUnit(unit, owner, owner,"Should be able to see a cavalry belonging to client's player");
    }

    @Test
    public void test05_display_elite_player() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        EliteTestObj unit = new EliteTestObj(owner);
        createAUnit(unit, owner, owner,"You should be able to see a elite belonging to client's player");
    }

    @Test
    public void test06_display_spearmen_player() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        SpearmenTestObj unit = new SpearmenTestObj(owner);
        createAUnit(unit, owner, owner,"You should be able to see a spearmen belonging to client's player");
    }

    @Test
    public void test07_display_swordmen_player() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        SwordsmenTestObj unit = new SwordsmenTestObj(owner);
        createAUnit(unit, owner, owner,"You should be able to see a swordmen belonging to client's player");
    }

    @Test
    public void test08_display_archer_player() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        ArchersTestObj unit = new ArchersTestObj(owner);
        createAUnit(unit, owner, owner, new MapLocationTestObj(3,1), "Should be able to see a archer belonging to client's player at 3,1");
    }

    @Test
    public void test09_display_cavalry_player() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        CavalryTestObj unit = new CavalryTestObj(owner);
        createAUnit(unit, owner, owner, new MapLocationTestObj(3,2),"Should be able to see a cavalry belonging to client's player at 3,2");
    }

    @Test
    public void test10_display_elite_player_pos() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        EliteTestObj unit = new EliteTestObj(owner);
        createAUnit(unit, owner, owner, new MapLocationTestObj(2,2), "Should be able to see a elite belonging to client's player at 2,2");
    }

    @Test
    public void test11_display_spearmen_player_pos() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        SpearmenTestObj unit = new SpearmenTestObj(owner);
        createAUnit(unit, owner, owner, new MapLocationTestObj(1,1),"Should be able to see a spearmen belonging to client's player at 1,1 ");
    }

    @Test
    public void test12_display_swordmen_player_pos() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        SwordsmenTestObj unit = new SwordsmenTestObj(owner);
        createAUnit(unit, owner, owner, new MapLocationTestObj(0,1),"Should be able to see a swordmen belonging to client's player at 1,0");
    }

    @Test
    public void test13_display_archer_npc() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        UnitOwnerTestObj uiOwner = new UnitOwnerTestObj();
        owner.setAsNpc();
        ArchersTestObj unit = new ArchersTestObj(owner);
        createAUnit(unit, owner, uiOwner,"Should be able to see a archer belonging to npc");
    }

    @Test
    public void test14_display_cavalry_npc() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        UnitOwnerTestObj uiOwner = new UnitOwnerTestObj();
        owner.setAsNpc();
        CavalryTestObj unit = new CavalryTestObj(owner);
        createAUnit(unit, owner, uiOwner, "Should be able to see a cavalry belonging to npc");
    }

    @Test
    public void test15_display_elite_npc() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        UnitOwnerTestObj uiOwner = new UnitOwnerTestObj();
        owner.setAsNpc();
        EliteTestObj unit = new EliteTestObj(owner);
        createAUnit(unit, owner, uiOwner,"You should be able to see a elite belonging to npc");
    }

    @Test
    public void test16_display_spearmen_npc() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        UnitOwnerTestObj uiOwner = new UnitOwnerTestObj();
        owner.setAsNpc();
        SpearmenTestObj unit = new SpearmenTestObj(owner);
        createAUnit(unit, owner, uiOwner,"You should be able to see a spearmen belonging to npc");
    }

    @Test
    public void test17_display_swordmen_npc() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        UnitOwnerTestObj uiOwner = new UnitOwnerTestObj();
        owner.setAsNpc();
        SwordsmenTestObj unit = new SwordsmenTestObj(owner);
        createAUnit(unit, owner, uiOwner, "You should be able to see a swordmen belonging to npc");
    }

    @Test
    public void test13_display_archer_otherPlayer() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        ArchersTestObj unit = new ArchersTestObj(owner);
        createAOwnerlessUnit(unit, owner, "Should be able to see a archer belonging to other player");
    }

    @Test
    public void test14_display_cavalry_otherPlayer() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        CavalryTestObj unit = new CavalryTestObj(owner);
        createAOwnerlessUnit(unit, owner, "Should be able to see a cavalry belonging to other player");
    }

    @Test
    public void test15_display_elite_otherPlayer() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        EliteTestObj unit = new EliteTestObj(owner);
        createAOwnerlessUnit(unit, owner, "You should be able to see a elite belonging to other player");
    }

    @Test
    public void test16_display_spearmen_otherPlayer() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        SpearmenTestObj unit = new SpearmenTestObj(owner);
        createAOwnerlessUnit(unit, owner, "You should be able to see a spearmen belonging to other player");
    }

    @Test
    public void test17_display_swordmen_otherPlayer() {
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        SwordsmenTestObj unit = new SwordsmenTestObj(owner);
        createAOwnerlessUnit(unit, owner, "You should be able to see a swordmen belonging to other player");
    }

    @Test
    public void test18_display_gameview() {
        List<Unit> entities = new ArrayList<>();
        Terrain[][] terrain = {
                {new MountainTestObj(), new MountainTestObj(), new MountainTestObj(),  new MountainTestObj(),  new MountainTestObj(),  new MountainTestObj(),   new MountainTestObj(),  new MountainTestObj(),  new MountainTestObj()},
                {new MountainTestObj(), new MountainTestObj(), new ForestTestObj(),    new ForestTestObj(),    new ForestTestObj(),    new ForestTestObj(),     new MountainTestObj(),  new MountainTestObj(),  new MountainTestObj()},
                {new MountainTestObj(), new MountainTestObj(), new ForestTestObj(),    new ForestTestObj(),    new ForestTestObj(),    new ForestTestObj(),     new ForestTestObj(),    new MountainTestObj(),  new MountainTestObj()},
                {new MountainTestObj(), new HillTestObj(),     new HillTestObj(),      new HillTestObj(),      new HillTestObj(),      new RiverTestObj(),      new HillTestObj(),      new MountainTestObj(),  new MountainTestObj()},
                {new MountainTestObj(), new HillTestObj(),     new HillTestObj(),      new HillTestObj(),      new HillTestObj(),      new HillTestObj(),       new HillTestObj(),      new HillTestObj(),      new MountainTestObj()},
                {new MountainTestObj(), new RiverTestObj(),    new RiverTestObj(),     new HillTestObj(),      new RiverTestObj(),      new HillTestObj(),      new HillTestObj(),      new MountainTestObj(),  new MountainTestObj()},
                {new MountainTestObj(), new MountainTestObj(), new HillTestObj(),      new HillTestObj(),      new HillTestObj(),      new HillTestObj(),       new HillTestObj(),      new MountainTestObj(),  new MountainTestObj()},
                {new MountainTestObj(), new MountainTestObj(), new PlainsTestObj(),    new PlainsTestObj(),    new PlainsTestObj(),    new PlainsTestObj(),     new MountainTestObj(),  new MountainTestObj(),  new MountainTestObj()},
                {new MountainTestObj(), new MountainTestObj(), new MountainTestObj(),  new MountainTestObj(),  new MountainTestObj(),  new MountainTestObj(),   new MountainTestObj(),  new MountainTestObj(),  new MountainTestObj()},
        };

        UnitOwnerTestObj owner = new UnitOwnerTestObj();

        ArchersTestObj a = new ArchersTestObj(owner);
        CavalryTestObj c = new CavalryTestObj(owner);
        EliteTestObj e = new EliteTestObj(owner);
        SpearmenTestObj s = new SpearmenTestObj(owner);

        owner.addUnit(a);
        owner.addUnit(c);
        owner.addUnit(e);
        owner.addUnit(s);

        a.setPosition(new MapLocationTestObj(5,1));
        c.setPosition(new MapLocationTestObj(2,1));
        e.setPosition(new MapLocationTestObj(2,2));
        s.setPosition(new MapLocationTestObj(3,3));

        entities.add(a);
        entities.add(c);
        entities.add(e);
        entities.add(s);
        setText("Should able to game view with a of grid hexgons and 4 units");
        ui = new Ui(setupModel(entities,terrain,owner));
        ui.skipMenu();
        ui.revealMap();

        waitToClose();
    }

    @Test
    public void test19_display_terrain_forest() {
        Terrain t = new ForestTestObj();
        createATerrainTile(t, "Should able to see forest terrain tile");
    }

    @Test
    public void test20_display_terrain_hill() {
        Terrain t = new HillTestObj();
        createATerrainTile(t, "Should able to see hill terrain tile");
    }

    @Test
    public void test21_display_terrain_mountain() {
        Terrain t = new MountainTestObj();
        createATerrainTile(t, "Should able to see mountain terrain tile");
    }

    @Test
    public void test22_display_terrain_plains() {
        Terrain t = new PlainsTestObj();
        createATerrainTile(t, "Should able to see plains terrain tile");
    }

    @Test
    public void test23_display_terrain_river() {
        Terrain t = new RiverTestObj();
        createATerrainTile(t, "Should able to see river terrain tile");
    }

    @Test
    public void test24_display_model_change() {
        Terrain[][] terrain = {
                {new PlainsTestObj()}
        };
        List<Unit> entities = new ArrayList<>();
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        setText("Should be able to see river terrain tile");
        ModelTestObj model = setupModel(entities,terrain,owner);
        ui = new Ui(model);

        MapLocation[][] map = new MapLocation[terrain.length][terrain[0].length];


        MapLocationTestObj m = new MapLocationTestObj(0,0);
        m.setTerrain(new RiverTestObj());
        map[0][0] = m;

        GameBoardTestObj gameBoardTestObj = new GameBoardTestObj();
        GameCollectionTestObj gameCollectionTestObj = new GameCollectionTestObj();

        gameBoardTestObj.setData(map);
        gameCollectionTestObj.setMap(gameBoardTestObj);
        gameCollectionTestObj.setAllUnits(entities);

        model.setWorld(gameCollectionTestObj);

        ui.disableInput();
        ui.skipMenu();
        ui.revealMap();
        waitToClose();
    }

    private void createATerrainTile(Terrain t, String text) {
        List<Unit> entities = new ArrayList<>();
        Terrain[][] terrain = {
                {t}
        };
        setText(text);
        UnitOwnerTestObj owner = new UnitOwnerTestObj();
        ui = new Ui(setupModel(entities,terrain,owner));
        ui.disableInput();
        ui.skipMenu();
        ui.revealMap();
        waitToClose();
    }

    private ModelTestObj setupModel(List<Unit> entities, Terrain[][] terrain, UnitOwnerTestObj owner) {
        ModelTestObj modelTestObj = new ModelTestObj();
        GameBoardTestObj gameBoardTestObj = new GameBoardTestObj();
        GameCollectionTestObj gameCollectionTestObj = new GameCollectionTestObj();
        MapLocation[][] map = new MapLocation[terrain.length][terrain[0].length];

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                map[y][x] = new MapLocationTestObj(x, y);
                map[y][x].setTerrain(terrain[y][x]);
            }
        }

        gameBoardTestObj.setData(map);
        gameCollectionTestObj.setMap(gameBoardTestObj);
        modelTestObj.setWorld(gameCollectionTestObj);

        gameCollectionTestObj.setAllUnits(entities);

        modelTestObj.setThisInstancePlayer(owner);

        return  modelTestObj;
    }

    private void createAUnit(Unit unit, UnitOwnerTestObj owner, UnitOwnerTestObj uiOwner, MapLocationTestObj pos, String text) {
        List<Unit> entities = new ArrayList<>();
        Terrain[][] terrain = {
                {new PlainsTestObj(), new PlainsTestObj(), new PlainsTestObj(), new PlainsTestObj()},
                {new PlainsTestObj(), new PlainsTestObj(), new PlainsTestObj(), new PlainsTestObj()},
                {new PlainsTestObj(), new PlainsTestObj(), new PlainsTestObj(), new PlainsTestObj()},
                {new PlainsTestObj(), new PlainsTestObj(), new PlainsTestObj(), new PlainsTestObj()}
        };
        owner.addUnit(unit);
        unit.setPosition(pos);
        entities.add(unit);
        setText(text);
        ui = new Ui(setupModel(entities,terrain,uiOwner));
        ui.disableInput();
        ui.skipMenu();
        ui.revealMap();
        waitToClose();
    }

    private void createAUnit(Unit unit, UnitOwnerTestObj owner, UnitOwnerTestObj uiOwner, String text) {
        List<Unit> entities = new ArrayList<>();
        Terrain[][] terrain = {
                {new PlainsTestObj(), new PlainsTestObj()},
                {new PlainsTestObj(), new PlainsTestObj()}
        };
        owner.addUnit(unit);
        unit.setPosition(new MapLocationTestObj(0,0));
        entities.add(unit);
        setText(text);
        ui = new Ui(setupModel(entities,terrain,uiOwner));
        ui.disableInput();
        ui.skipMenu();
        ui.revealMap();
        waitToClose();
    }

    private void createAOwnerlessUnit(Unit unit, UnitOwnerTestObj owner, String text) {
        List<Unit> entities = new ArrayList<>();
        Terrain[][] terrain = {
                {new PlainsTestObj(), new PlainsTestObj()},
                {new PlainsTestObj(), new PlainsTestObj()}
        };
        unit.setPosition(new MapLocationTestObj(0,0));
        entities.add(unit);
        setText(text);
        ui = new Ui(setupModel(entities,terrain,null));
        ui.disableInput();
        ui.skipMenu();
        ui.revealMap();
        waitToClose();
    }

}
