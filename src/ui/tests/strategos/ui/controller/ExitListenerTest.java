package strategos.ui.controller;


import model.GameCollectionTestObj;
import model.ModelTestObj;
import org.junit.Before;
import org.junit.Test;
import strategos.model.GameState;
import strategos.ui.view.View;

import static org.junit.Assert.assertTrue;


/**
 * @author Devon Mortimer (external tester)
 */
public class ExitListenerTest {

    private MockView mockView;
    private ExitListener listener;

    @Before
    public void setUp() {
        View view = new MockView();
        this.mockView = (MockView) view;
        ModelTestObj model = new ModelTestObj();
        model.setWorld(new GameCollectionTestObj());
        this.listener = new ExitListener(new MockController(model, this.mockView));
        this.listener.view = view;
    }

    @Test
    public void actionPerformed_menu() {
        this.mockView.setStatus(true);
        this.listener.actionPerformed(null);
        assertTrue("If game is foreground then show menu", this.mockView.didMenu);
    }

    @Test
    public void actionPerformed_exit() {
        this.mockView.setStatus(false);
        this.listener.actionPerformed(null);
        assertTrue("If menu is foreground then exit", this.mockView.didExit);
    }

    private static class MockView extends View {

        private boolean status;
        private boolean didExit;
        private boolean didMenu;

        MockView() {
            super(null);
            this.status = false;
            this.didMenu = false;
            this.didExit = false;
        }

        @Override
        public void setMenu() {
            this.didMenu = true;
        }

        @Override
        public boolean status() {
            return this.status;
        }

        @Override
        public void exit() {
            this.didExit = true;
        }

        void setStatus(boolean status) {
            this.status = status;
        }
    }


    private class MockController extends Controller {

        MockController(GameState model, View view) {
            super(model, view, null);
            this.allInput = true;
        }
    }
}