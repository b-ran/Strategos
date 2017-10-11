package strategos.mapGenerator;

import strategos.Paintable;

public interface Generator {

    Paintable[][] populateMap(Paintable[][] hexMap, int seed);

    Paintable[][] populateMap(Paintable[][] hexMap);
}
