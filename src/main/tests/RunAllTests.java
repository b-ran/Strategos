import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        IntegrationTests.class,
        UiIntegrationTests.class,
        MapGenerationTests.class,
        UiTests.class,
        BehaviourTests.class,
        NetworkingTests.class,
        ModelTests.class
})


public class RunAllTests {
}
