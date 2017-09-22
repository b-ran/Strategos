package strategos.ui.view;

import javax.swing.*;

import java.awt.*;

import static strategos.ui.config.Config.*;

public class GridComponent extends JComponent {

    public JPanel getGrid() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setPreferredSize(GRID_COMPONENT_SIZE);
        return p;
    }
}
