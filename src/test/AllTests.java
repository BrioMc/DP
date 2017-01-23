package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Run all Test
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({ KeyTest.class, MapTest.class, PjTest.class, SquareTest.class, ThroneDoorTest.class })
public class AllTests {

}
