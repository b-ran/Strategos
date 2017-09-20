package strategos.behaviour.util;


import strategos.behaviour.Behaviour;

public interface HasBehaviour <T extends Behaviour> {

    T getBehaviour();

    void setBehaviour(T behaviour);
}
