import strategos.terrain.Terrain;
import strategos.ui.Ui;
import strategos.units.Unit;
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

        entities.add(new ArchersTestObj());
        entities.add(new CavalryTestObj());
        entities.add(new EliteTestObj());
        entities.add(new SpearmenTestObj());

        Ui ui = new Ui(entities,terrain);
    }
}
