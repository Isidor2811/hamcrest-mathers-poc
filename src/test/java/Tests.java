import models.User;
import org.hamcrest.Matcher;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static models.User.Gender.F;
import static models.User.Gender.M;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Tests {

    // http://hamcrest.org/JavaHamcrest/javadoc/2.2/org/hamcrest/Matchers

    List<User> users = new ArrayList<>();
    List<String> animals = new ArrayList<>();

    @BeforeClass
    public void generateUsers() {
        users.add(new User(1, "Oleg", "Datsiuk", M, new BigDecimal("1445.11"), 1.85));
        users.add(new User(2, "Ivan", "Ivanov", M, new BigDecimal("2455.99"), 1.91));
        users.add(new User(4, "Petro", "Petrov", M, new BigDecimal("1234.5"), 1.78));
        users.add(new User(3, "Maria", "Ivara", F, new BigDecimal("6563.46"), 1.68));

        animals.add("Zebra");
        animals.add("Lion");
        animals.add("Cat");
        animals.add("Dog");
        animals.add("Duck");
    }

    // Creates a matcher that matches if the examined object matches ALL of the specified matchers. For example:
    @Test
    public void allOfTest() {
        User user = users.get(0);

        assertThat(user, allOf(
                instanceOf(User.class),
                hasProperty("firstName"),
                hasProperty("isSingle")
        ));
    }

    @Test
    public void allOfTest2() {
        User user = new User(55, "Maria", "Ivara", F, new BigDecimal("6563.46"), 1.68);

        assertThat(users, allOf(
                iterableWithSize(4),
                hasItem(user)
        ));
    }

    // Creates a matcher that matches if the examined object matches ANY of the specified matchers. For example:
    @Test
    public void anyOfTest() {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User(77, "Maria", "Ivara", F, new BigDecimal("6563.46"), 1.68));

        assertThat(users, anyOf(
                not(containsInAnyOrder(expectedUsers)),
                emptyIterable()
        ));
    }

    //Creates a matcher that matches when both of the specified matchers match the examined object. For example:
    @Test
    public void bothTest() {
        assertThat(users.get(0).getSalary(),
                both(greaterThan(new BigDecimal("1500")))
                        .and(lessThan(new BigDecimal("3000"))));
    }

    //Creates a matcher that matches when either of the specified matchers match the examined object. For example:
    @Test
    public void eitherTest() {
        assertThat(users.get(0).getSalary(),
                either(greaterThan(new BigDecimal("1500")))
                        .or(lessThan(new BigDecimal("3000"))));
    }

    //Wraps an existing matcher, overriding its description with that specified.
    @Test
    public void withDescribeAsTest() {
        User user = users.get(0);
        BigDecimal expectedMax = new BigDecimal("5000");
        Matcher<BigDecimal> matcher = describedAs("expected that user %0 should get salary more than %1, but actual is %2",
                greaterThan(expectedMax), user, expectedMax, user.getSalary());
        assertThat(user.getSalary(), matcher);
    }

    // Creates a matcher for Iterables that only matches when a single pass over the examined Iterable yields items that are all matched by the specified itemMatcher. For example:
    @Test
    public void everyItemTest() {
        assertThat(animals, everyItem(isA(String.class)));
    }


    //    Creates a matcher that matches only when the examined object is the same instance as the specified target object.
    @Test
    public void sameInstanceTest() {
        assertThat(animals.get(0), sameInstance(animals.get(1)));
    }

}
