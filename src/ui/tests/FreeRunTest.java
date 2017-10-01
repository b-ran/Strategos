import model.*;
import strategos.GameCollections;
import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.terrain.Terrain;
import strategos.ui.Ui;
import strategos.units.*;
import terrain.*;
import units.*;

import java.util.ArrayList;
import java.util.List;

public class FreeRunTest {
    public static void main(String[] args) {
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


        MapLocation[][] map = new MapLocation[terrain.length][terrain[0].length];

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                map[y][x] = new MapLocationTestObj(x, y);
                map[y][x].setTerrain(terrain[y][x]);
            }
        }

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

        ModelTestObj model = new ModelTestObj();

        GameBoardTestObj gameBoardTestObj = new GameBoardTestObj();
        GameCollectionTestObj gameCollectionTestObj = new GameCollectionTestObj();

        gameBoardTestObj.setData(map);
        gameCollectionTestObj.setMap(gameBoardTestObj);
        model.setWorld(gameCollectionTestObj);

        ArrayList<UnitOwner> unitOwnerTestObjs = new ArrayList<>();
        unitOwnerTestObjs.add(owner);
        gameCollectionTestObj.setAllUnits(owner.getUnits());

        model.setPlayers(unitOwnerTestObjs);


        Ui ui = new Ui(model);
    }
}
