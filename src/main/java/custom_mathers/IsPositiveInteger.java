package custom_mathers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsPositiveInteger extends TypeSafeMatcher<Integer> {

    @Override
    protected boolean matchesSafely(Integer integer) {
        return integer > 0;
    }

    public static Matcher<Integer> isPositiveInteger() {
        return new IsPositiveInteger();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("positive integer (number>0)");
    }
}
