package strategos.behaviour;


public interface BehaviourFactory {

    Behaviour createBehaviourArchers();

    Behaviour createBehaviourCavalry();

    Behaviour createBehaviourElite();

    Behaviour createBehaviourSpearmen();

    Behaviour createBehaviourSwordsmen();

    Behaviour createAiBehaviour(Behaviour baseBehaviour);
}
