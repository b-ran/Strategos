package strategos.mapGeneration.map.board;

import lombok.Data;
import strategos.util.Entity;

import java.util.ArrayList;

/**
 * Created as part of Strategos project
 * 28/07/2017.
 */
@Data
public class Board implements Entity {

	private ArrayList<Hex> hexes;

	public Board(ArrayList<Hex> hexes) {
		////TODO get real value
		if(hexes.size() < 25) throw new IllegalArgumentException("Cannot have less than 25 hexes");
		this.hexes = hexes;
	}

}
