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
                {new ForestTestObj(),   new ForestTestObj(),    new ForestTestObj(),    new ForestTestObj()},
                {new HillTestObj(),     new HillTestObj(),      new HillTestObj(),      new HillTestObj(),},
                {new MountainTestObj(), new MountainTestObj(),  new MountainTestObj(),  new MountainTestObj()},
                {new PlainsTestObj(),   new PlainsTestObj(),    new PlainsTestObj(),    new PlainsTestObj()},
                {new RiverTestObj(),    new RiverTestObj(),     new RiverTestObj(),     new RiverTestObj()}
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

        a.setPosition(new MapLocationTestObj(0,0));
        c.setPosition(new MapLocationTestObj(1,1));
        e.setPosition(new MapLocationTestObj(2,2));
        s.setPosition(new MapLocationTestObj(3,3));


        entities.add(a);
        entities.add(c);
        entities.add(e);
        entities.add(s);

        Ui ui = new Ui(entities,terrain);
    }
}
