package booking;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Test1_CreateToken.class,
        Test2_CreateBookingTest.class,
        Test3_UpdateBookingTest.class
})
public class TestSuite {
}
