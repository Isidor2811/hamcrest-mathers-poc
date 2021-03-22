package custom_mathers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class InRange extends TypeSafeMatcher<Integer> {

    private int begin;
    private int end;

    public InRange(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }


    @Override
    protected boolean matchesSafely(Integer number) {
        return number > begin && number < end;
    }

    public static InRange inRange(int begin, int end) {
        return new InRange(begin, end);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(String.format("number in range from %s to %s", begin, end));
    }
}
