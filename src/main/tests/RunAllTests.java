import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        IntegrationTests.class,
        UiIntegrationTests.class,
        MapGenerationTests.class,
        UiTests.class,
        BehaviourTests.class,
        AllModelTests.class
})


public class RunAllTests {
    //This runs all the tests for each library independently by simply calling the test classes in each library
	//Networking tests must be run independently
}
