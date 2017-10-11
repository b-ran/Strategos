package strategos;

import strategos.terrain.*;
import strategos.units.*;

public interface GameObjectVisitor {

    public void visit(Forest forest);

    public void visit(Hill hill);

    public void visit(Mountain Mountain);

    public void visit(Plains plains);

    public void visit(River river);

    public void visit(Archers archers);

    public void visit(Bridge bridge);

    public void visit(Cavalry cavalry);

    public void visit(Elite elite);

    public void visit(HealthPotion healthPotion);

    public void visit(Spearmen spearmen);

    public void visit(Swordsmen swordsmen);

}
