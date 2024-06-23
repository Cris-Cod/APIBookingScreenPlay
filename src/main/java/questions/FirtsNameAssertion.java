package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class FirtsNameAssertion implements Question {

    private final String actual;
    private final String expected;

    public FirtsNameAssertion(String actual, String expected) {
        this.actual = actual;
        this.expected = expected;
    }


    @Override
    public Object answeredBy(Actor actor) {
        return actual.equalsIgnoreCase(expected);
    }

    public static FirtsNameAssertion areEqual(String actual, String expected) {
        return new FirtsNameAssertion(actual, expected);
    }
}
