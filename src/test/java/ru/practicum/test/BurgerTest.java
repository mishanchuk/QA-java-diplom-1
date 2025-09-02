package ru.practicum.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import praktikum.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetReceipt_withoutIngredients() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("Test Bun");
        when(bun.getPrice()).thenReturn(7.0f);
        burger.setBuns(bun);

        // Receipt при пустом списке ингредиентов
        String expected = String.format("(==== %s ====)%n", "Test Bun");
        expected += String.format("(==== %s ====)%n", "Test Bun");
        expected += String.format("%nPrice: %f%n", burger.getPrice());

        String actual = burger.getReceipt();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetReceipt_withIngredients_formatAndOrder() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("White Bun");
        when(bun.getPrice()).thenReturn(2.0f);
        burger.setBuns(bun);

        // Создаём два стаб-ингредиента с типами и именами
        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("Ketchup");
        when(sauce.getPrice()).thenReturn(0.5f);

        Ingredient filling = mock(Ingredient.class);
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("Beef Patty");
        when(filling.getPrice()).thenReturn(3.0f);

        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        StringBuilder expected = new StringBuilder(String.format("(==== %s ====)%n", "White Bun"));
        expected.append(String.format("= %s %s =%n", "sauce", "Ketchup"));
        expected.append(String.format("= %s %s =%n", "filling", "Beef Patty"));
        expected.append(String.format("(==== %s ====)%n", "White Bun"));
        expected.append(String.format("%nPrice: %f%n", burger.getPrice()));

        assertEquals(expected.toString(), burger.getReceipt());
    }

    @Test
    public void testAddRemoveAndMoveIngredients_behaviour() {
        Burger burger = new Burger();

        Ingredient a = mock(Ingredient.class);
        Ingredient b = mock(Ingredient.class);
        Ingredient c = mock(Ingredient.class);

        // добавляем три ингредиента
        burger.addIngredient(a);
        burger.addIngredient(b);
        burger.addIngredient(c);

        assertEquals(3, burger.ingredients.size());
        assertSame(a, burger.ingredients.get(0));
        assertSame(b, burger.ingredients.get(1));
        assertSame(c, burger.ingredients.get(2));

        // удаление по индексу 1 (b)
        burger.removeIngredient(1);
        assertEquals(2, burger.ingredients.size());
        assertSame(a, burger.ingredients.get(0));
        assertSame(c, burger.ingredients.get(1));

        // перекидываем текущий индекс 1 -> 0
        burger.moveIngredient(1, 0);
        assertEquals(2, burger.ingredients.size());
        assertSame(c, burger.ingredients.get(0));
        assertSame(a, burger.ingredients.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredient_throwsWhenIndexInvalid() {
        Burger burger = new Burger();
        // Пытаемся удалить из пустого списка -> IndexOutOfBoundsException
        burger.removeIngredient(0);
    }
}