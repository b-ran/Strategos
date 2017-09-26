import mapgenerationtests.MapTester;
import noisegenerationtests.NoiseTester;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        NoiseTester.class,
        MapTester.class})
public class TestAll {

}
