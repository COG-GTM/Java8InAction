package lambdasinaction.chap4;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StreamBasicsTest {

    @Test
    public void dishMenuIsNotEmpty() {
        assertFalse(Dish.menu.isEmpty());
        assertEquals(9, Dish.menu.size());
    }

    @Test
    public void lowCaloricDishesJava7() {
        List<String> names = StreamBasic.getLowCaloricDishesNamesInJava7(Dish.menu);
        assertFalse(names.isEmpty());
        assertTrue(names.stream().allMatch(name ->
                Dish.menu.stream()
                        .filter(d -> d.getName().equals(name))
                        .findFirst()
                        .map(d -> d.getCalories() < 400)
                        .orElse(false)));
    }

    @Test
    public void lowCaloricDishesJava8() {
        List<String> names = StreamBasic.getLowCaloricDishesNamesInJava8(Dish.menu);
        assertFalse(names.isEmpty());
        assertTrue(names.stream().allMatch(name ->
                Dish.menu.stream()
                        .filter(d -> d.getName().equals(name))
                        .findFirst()
                        .map(d -> d.getCalories() < 400)
                        .orElse(false)));
    }

    @Test
    public void java7AndJava8ProduceSameResults() {
        List<String> java7 = StreamBasic.getLowCaloricDishesNamesInJava7(Dish.menu);
        List<String> java8 = StreamBasic.getLowCaloricDishesNamesInJava8(Dish.menu);
        assertEquals(java7, java8);
    }

    @Test
    public void dishTypes() {
        assertTrue(Dish.menu.stream().anyMatch(d -> d.getType() == Dish.Type.MEAT));
        assertTrue(Dish.menu.stream().anyMatch(d -> d.getType() == Dish.Type.FISH));
        assertTrue(Dish.menu.stream().anyMatch(d -> d.getType() == Dish.Type.OTHER));
    }

    @Test
    public void vegetarianDishesExist() {
        assertTrue(Dish.menu.stream().anyMatch(Dish::isVegetarian));
    }
}
