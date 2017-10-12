package strategos.terrain;

import java.io.Serializable;

/**
 * Created by Shaun Sinclair on 09/2017
 * for Strategos
 */
public interface Terrain extends Serializable {
    String toString();
    boolean isPassable();
}
