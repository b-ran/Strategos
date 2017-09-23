package strategos.ui.view;

import javax.swing.*;

import java.awt.*;

import static strategos.ui.config.Config.*;

public class GridComponent extends JComponent {

    public GridComponent() {
        setLayout(new BorderLayout());
        setPreferredSize(GRID_COMPONENT_SIZE);
    }

    public JLayeredPane getGrid() {
        JLayeredPane p = new JLayeredPane();
        p.setLayout(new BorderLayout());
        p.setPreferredSize(GRID_COMPONENT_SIZE);
        return p;
    }
}
