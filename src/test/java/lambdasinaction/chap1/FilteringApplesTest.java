package lambdasinaction.chap1;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import lambdasinaction.chap1.FilteringApples.Apple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FilteringApplesTest {

    private final List<Apple> inventory = Arrays.asList(
            new Apple(80, "green"),
            new Apple(155, "green"),
            new Apple(120, "red"));

    @Test
    public void filterGreenApplesReturnsOnlyGreen() {
        List<Apple> green = FilteringApples.filterGreenApples(inventory);
        assertEquals(2, green.size());
        assertTrue(green.stream().allMatch(a -> "green".equals(a.getColor())));
    }

    @Test
    public void filterHeavyApplesReturnsApplesOver150() {
        List<Apple> heavy = FilteringApples.filterHeavyApples(inventory);
        assertEquals(1, heavy.size());
        assertEquals(Integer.valueOf(155), heavy.get(0).getWeight());
    }

    @Test
    public void filterApplesWithPredicate() {
        List<Apple> redApples = FilteringApples.filterApples(inventory, a -> "red".equals(a.getColor()));
        assertEquals(1, redApples.size());
        assertEquals("red", redApples.get(0).getColor());
    }

    @Test
    public void isGreenAppleAndIsHeavyApple() {
        assertTrue(FilteringApples.isGreenApple(new Apple(50, "green")));
        assertFalse(FilteringApples.isGreenApple(new Apple(50, "red")));
        assertTrue(FilteringApples.isHeavyApple(new Apple(200, "red")));
        assertFalse(FilteringApples.isHeavyApple(new Apple(100, "red")));
    }
}
