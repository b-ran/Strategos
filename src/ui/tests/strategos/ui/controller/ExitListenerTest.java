package strategos.ui.controller;


import model.*;
import org.junit.jupiter.api.*;
import strategos.*;
import strategos.ui.view.*;

import static org.junit.jupiter.api.Assertions.*;


class ExitListenerTest {

    private MockView     mockView;
    private ExitListener listener;

    @BeforeEach void setUp() {
        View view = new MockView();
        mockView = (MockView) view;
        ModelTestObj model = new ModelTestObj();
        model.setWorld(new GameCollectionTestObj());
        listener = new ExitListener(new MockController(model, mockView));
        listener.view = view;
    }

    @Test void actionPerformed_menu() {
        mockView.setStatus(true);
        listener.actionPerformed(null);
        assertTrue(mockView.didMenu, "If game is foreground then show menu");
    }

    @Test void actionPerformed_exit() {
        mockView.setStatus(false);
        listener.actionPerformed(null);
        assertTrue(mockView.didExit, "If menu is foreground then exit");
    }

    private static class MockView extends View {

        private boolean status;
        private boolean didExit;
        private boolean didMenu;

        MockView() {
            super(null, null);
            status = false;
            didMenu = false;
            didExit = false;
        }

        @Override public void setMenu() {
            didMenu = true;
        }

        @Override public boolean status() {
            return status;
        }

        @Override public void exit() {
            didExit = true;
        }

        void setStatus(boolean status) {
            this.status = status;
        }
    }


    private class MockController extends Controller {

        MockController(GameState model, View view) {
            super(model, view);
            allInput = true;
        }
    }
}