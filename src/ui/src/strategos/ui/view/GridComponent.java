package strategos.ui.view;

import javax.swing.*;

class GridComponent extends JComponent {

    private View view = new View();

    protected GridComponent(View view) {
        this.view = view;
    }
}
