package model;

import strategos.model.GameBoard;
import strategos.model.GameCollections;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;

public class GameCollectionTestObj implements GameCollections {

    GameBoard gameBoard;
    List<Unit> allUnits = new ArrayList<>();

    @Override
    public GameBoard getMap() {
        return gameBoard;
    }

    @Override
    public List<Unit> getAllUnits() {
        return allUnits;
    }

    @Override
    public void setMap(GameBoard map) {
        gameBoard = map;
    }

    @Override
    public void setAllUnits(List<Unit> allUnits) {
        this.allUnits = allUnits;
    }

}

