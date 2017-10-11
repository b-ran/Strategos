package strategos.ui.controller;


import strategos.MapLocation;
import strategos.units.Unit;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;


class AttackListener extends Controller implements MouseListener, KeyListener {

    private Controller controller;

    AttackListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        /*if (controller.getSelectedMapLocation() == null) return;

        Point p = getHexPos(e.getX(),e.getY());
        Unit oldSelectedUnit = model.getUnitAt(controller.getSelectedMapLocation());
        Unit newSelectedUnit = model.getUnitAt(board.get(p.x,p.y));

        if (oldSelectedUnit == null || newSelectedUnit == null) return;

        List<Unit> units = model.getUnitsInAttackRange(oldSelectedUnit);

        if (units.contains(newSelectedUnit)) {
            System.out.println("send attack command");
            model.attack(oldSelectedUnit, newSelectedUnit.getPosition());
        }*/
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
