import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import strategos.external.ExternalTests;
import strategos.hexgrid.HexGridTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExternalTests.class,
        HexGridTests.class,
        ModelTests.class
})
public class ModelTests {
}
