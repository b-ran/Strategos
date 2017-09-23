package strategos.ui.view;

import javax.swing.*;

import java.awt.*;

import static strategos.ui.config.Config.*;

/**
 * The type Grid component.
 */
public class GridComponent extends JComponent {

    /**
     * Instantiates a new Grid component for drawing on.
     */
    public GridComponent() {
        setLayout(new BorderLayout());
        setPreferredSize(GRID_COMPONENT_SIZE);
    }

    /**
     * Gets grid panel.
     *
     * @return the grid
     */
    public JLayeredPane getGrid() {
        JLayeredPane p = new JLayeredPane();
        p.setLayout(new BorderLayout());
        p.setPreferredSize(GRID_COMPONENT_SIZE);
        return p;
    }
}
