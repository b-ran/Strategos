package strategos.behaviour;


import strategos.behaviour.util.MapLocation;


class BehaviourBase implements Behaviour {

    private MapLocation position;

    @Override public MapLocation getPosition() {
        assert position != null : "Position should never be null on retrieval.";
        return position;
    }

    @Override public void setPosition(MapLocation position) {
        assert position != null : "Position should never be set to null.";
        this.position = position;
    }
}
