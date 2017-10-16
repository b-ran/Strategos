import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import strategos.behaviour.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BaseBehaviourTest.class,
        BehaviourArchersTest.class,
        BehaviourCavalryTest.class,
        BehaviourEliteTest.class,
        BehaviourFactoryImpl.class,
        BehaviourSpearmenTest.class,
        BehaviourSwordsmenTest.class,
        UnitBehaviourTest.class
})
public class BehaviourTests {
}
