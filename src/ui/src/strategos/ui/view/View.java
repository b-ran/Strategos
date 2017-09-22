package strategos.ui.view;

import strategos.Graphical;
import strategos.hexgrid.Hex;
import strategos.model.units.Unit;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static strategos.util.Config.*;

public class View extends JComponent implements Observer {


    private JFrame frame; //Overall Frame

    protected List<Unit> entities;
    protected Hex[][] terrain;

    public View(List<Graphical> entities, Graphical[][] terrain) {
        this.entities = entities;
        this.terrain = terrain;
        frame = new JFrame(WINDOW_NAME);
        setMenu();
        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void update(Observable o, Object arg) {
        frame.repaint();
        requestFocus();
    }

    public void setMenu() {
        frame.dispose();
        frame.add(new MenuComponent().getMenu());
    }

    public void setEscapeMenu() {
        frame.dispose();
        frame.add(new MenuComponent().getEscapeMenu());
    }
}
