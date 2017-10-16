import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import strategos.networking.Tests;
import strategos.networking.external_testing.ExternalTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Tests.class,
        ExternalTests.class
})

public class NetworkingTests {
}
