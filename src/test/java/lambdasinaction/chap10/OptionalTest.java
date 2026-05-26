package lambdasinaction.chap10;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class OptionalTest {

    @Test
    public void getCarInsuranceNameWithEmptyPerson() {
        OptionalMain main = new OptionalMain();
        String result = main.getCarInsuranceName(Optional.empty());
        assertEquals("Unknown", result);
    }

    @Test
    public void optionalOfNullable() {
        Optional<String> opt = Optional.ofNullable(null);
        assertFalse(opt.isPresent());

        Optional<String> present = Optional.ofNullable("hello");
        assertTrue(present.isPresent());
        assertEquals("hello", present.get());
    }

    @Test
    public void optionalMap() {
        Optional<String> name = Optional.of("Java");
        Optional<Integer> len = name.map(String::length);
        assertEquals(Integer.valueOf(4), len.get());
    }

    @Test
    public void optionalFlatMap() {
        Optional<Optional<String>> nested = Optional.of(Optional.of("nested"));
        Optional<String> flat = nested.flatMap(x -> x);
        assertEquals("nested", flat.get());
    }

    @Test
    public void optionalOrElse() {
        Optional<String> empty = Optional.empty();
        assertEquals("default", empty.orElse("default"));

        Optional<String> present = Optional.of("value");
        assertEquals("value", present.orElse("default"));
    }

    @Test
    public void optionalFilter() {
        Optional<String> opt = Optional.of("hello");
        assertTrue(opt.filter(s -> s.startsWith("h")).isPresent());
        assertFalse(opt.filter(s -> s.startsWith("z")).isPresent());
    }

    @Test
    public void optionalIfPresent() {
        Optional<String> opt = Optional.of("test");
        StringBuilder sb = new StringBuilder();
        opt.ifPresent(sb::append);
        assertEquals("test", sb.toString());
    }

    @Test
    public void optionalStream() {
        Optional<String> present = Optional.of("value");
        long count = present.stream().count();
        assertEquals(1, count);

        Optional<String> empty = Optional.empty();
        long emptyCount = empty.stream().count();
        assertEquals(0, emptyCount);
    }

    @Test
    public void insuranceNameAccess() {
        Insurance insurance = new Insurance();
        assertNull(insurance.getName());
    }
}
