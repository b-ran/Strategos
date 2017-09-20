package strategos.ui.view;

import javax.swing.*;

class SidePanelComponent extends JComponent {

    private View view = new View();

    protected SidePanelComponent(View view) {
        this.view = view;
    }
}
