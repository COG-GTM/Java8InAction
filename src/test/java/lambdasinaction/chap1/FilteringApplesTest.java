package lambdasinaction.chap1;

import lambdasinaction.chap1.FilteringApples.Apple;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FilteringApplesTest {

    private final List<Apple> inventory = Arrays.asList(
            new Apple(80, "green"),
            new Apple(155, "green"),
            new Apple(120, "red"));

    @Test
    public void filterGreenApples() {
        List<Apple> result = FilteringApples.filterGreenApples(inventory);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(a -> "green".equals(a.getColor())));
    }

    @Test
    public void filterHeavyApples() {
        List<Apple> result = FilteringApples.filterHeavyApples(inventory);
        assertEquals(1, result.size());
        assertTrue(result.get(0).getWeight() > 150);
    }

    @Test
    public void filterWithMethodReference() {
        List<Apple> greens = FilteringApples.filterApples(inventory, FilteringApples::isGreenApple);
        assertEquals(2, greens.size());

        List<Apple> heavies = FilteringApples.filterApples(inventory, FilteringApples::isHeavyApple);
        assertEquals(1, heavies.size());
    }

    @Test
    public void filterWithLambda() {
        List<Apple> greens = FilteringApples.filterApples(inventory, a -> "green".equals(a.getColor()));
        assertEquals(2, greens.size());

        List<Apple> heavies = FilteringApples.filterApples(inventory, a -> a.getWeight() > 150);
        assertEquals(1, heavies.size());
    }

    @Test
    public void filterWithComplexPredicate() {
        List<Apple> result = FilteringApples.filterApples(inventory,
                a -> a.getWeight() < 80 || "brown".equals(a.getColor()));
        assertTrue(result.isEmpty());
    }

    @Test
    public void appleToString() {
        Apple apple = new Apple(80, "green");
        String str = apple.toString();
        assertTrue(str.contains("green"));
        assertTrue(str.contains("80"));
    }
}
