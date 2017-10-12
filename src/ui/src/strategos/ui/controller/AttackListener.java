package strategos.ui.controller;


import strategos.model.MapLocation;
import strategos.units.Unit;

import java.awt.*;
import java.awt.event.*;
import java.util.List;


class AttackListener extends Controller implements MouseListener, MouseMotionListener {

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

        Unit selectedUnit = controller.getSelectedUnit();
        MapLocation selectedMapLocation = controller.getSelectedMapLocation();

        Point p = getHexPos(e.getX(),e.getY());

        if (selectedMapLocation == null || selectedUnit == null) return;
        if (selectedUnit.getOwner() != view.getUiOwner()) return;

        Unit target = model.getUnitAt(board.get(p.x,p.y));

        List<Unit> attackableUnits = model.getUnitsInAttackRange(selectedUnit);

        if (attackableUnits.contains(target)) {
            model.attack(selectedUnit, target.getPosition());
            controller.resetSection();
            view.repaint();
        }


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
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
