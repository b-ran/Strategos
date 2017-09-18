package strategos.ui.view;

import javax.swing.*;

class MenuComponent extends JComponent {

    private View view = new View();

    protected MenuComponent(View view) {
        this.view = view;
    }
}
