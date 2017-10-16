import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import strategos.ui.controller.ExitListenerTest;
import strategos.ui.view.GridComponentTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Tests.class,
        FreeRunTest.class,
        ExitListenerTest.class,
        GridComponentTest.class
})

public class UiTests {
}
