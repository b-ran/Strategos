package gamelogic;

import java.util.ArrayList;
import java.util.List;

import strategos.model.Player;

public class GameRunner {
	private List<Player> players = new ArrayList<>();
	private Player turn;
	
	public GameRunner(List<Player> players) {
		this.players = players;
		turn = players.get(0);
	}
	
	public void nextTurn() {
		turn = players.get((players.indexOf(turn)) + 1 % players.size());
	}
	
}
