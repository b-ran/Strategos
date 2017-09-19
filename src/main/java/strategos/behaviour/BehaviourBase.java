package strategos.behaviour;


import strategos.model.*;


class BehaviourBase implements Behaviour {

    private Hex position;

    @Override public Hex getPosition() {
        assert position != null : "Position should never be null on retrieval.";
        return position;
    }

    @Override public void setPosition(Hex position) {
        assert position != null : "Position should never be set to null.";
        this.position = position;
    }
}
