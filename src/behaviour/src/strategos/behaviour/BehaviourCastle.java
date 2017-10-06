package strategos.behaviour;


import strategos.GameState;
import strategos.behaviour.config.BehaviourConfig;
import strategos.units.Unit;


class BehaviourCastle extends UnitBehaviour {

    //TODO: Where is your javadoc?

    BehaviourCastle(GameState gameState) {
        super(gameState);
    }

    private BehaviourCastle(BehaviourCastle behaviourCastle) {
        super(behaviourCastle);
    }

    @Override
    public int getStrength(Unit unit) {
        return BehaviourConfig.CASTLE_STRENGTH;
    }

    @Override
    public int getToughness(Unit unit) {
        return BehaviourConfig.CASTLE_TOUGHNESS;
    }

    @Override
    public Behaviour copy() {
        return new BehaviourCastle(this);
    }

    @Override
    public void charge(Unit unit) {
        // Archers cannot charge, so this blank method overrides the default
        // behaviour
    }

    @Override
    public int attack(Unit unit, Unit enemy) {
        if (enemy == null) {
            throw new NullPointerException("Method attack() requires a non-null enemy");
        }

        if (!isAlive(unit) || !enemy.isAlive()) {
            return 0;
        }

        if (getActionPoints(unit) <= 0) {
            return 0;
        }

        setActionPoints(getActionPoints(unit) - 1);
        enemy.defend(unit);

        return 0;
    }

    @Override
    int getMaxActionPoints() {
        return 0;
    }

    @Override
    public int getAttackRange() {
        return BehaviourConfig.CASTLE_RANGE;
    }

    @Override
    public String toString() {
        return "BehaviourArchers{} " + super.toString();
    }
}
