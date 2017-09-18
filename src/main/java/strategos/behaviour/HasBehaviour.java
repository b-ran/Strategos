package strategos.behaviour;


public interface HasBehaviour <T extends Behaviour> {

    T getBehaviour();

    void setBehaviour(T behaviour);
}
