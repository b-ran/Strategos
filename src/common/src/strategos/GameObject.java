package strategos;

/**
 * @author Devon Mortimer
 * @since 22/09/17
 */
public interface GameObject {

    public void accept(GameObjectVisitor gameObjectVisitor);

}
