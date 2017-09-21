package strategos.behaviour;


import sun.reflect.generics.reflectiveObjects.*;


public class BehaviourFactoryImpl implements BehaviourFactory {

    @Override public Behaviour createBehaviourArchers() {
        return new BehaviourArchers();
    }

    @Override public Behaviour createBehaviourCavalry() {
        throw new NotImplementedException();
    }

    @Override public Behaviour createBehaviourElite() {
        throw new NotImplementedException();
    }

    @Override public Behaviour createBehaviourSpearmen() {
        throw new NotImplementedException();
    }

    @Override public Behaviour createBehaviourSwordsmen() {
        throw new NotImplementedException();
    }

    @Override public Behaviour createAiBehaviour(Behaviour baseBehaviour) {
        return new AiBehaviour(baseBehaviour);
    }
}
