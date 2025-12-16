import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai");
        restaurant.openingTime = openingTime;
        restaurant.closingTime = closingTime;
        
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    // >>>>>>>>>>>>>>>>>>>>>>>OPENING/CLOSING TESTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        // Spy to mock getCurrentTime()
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        LocalTime mockTime = LocalTime.parse("12:00:00");
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(mockTime);
        
        assertTrue(spyRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        // Spy to mock getCurrentTime()
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        LocalTime mockTime = LocalTime.parse("23:00:00");
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(mockTime);
        
        assertFalse(spyRestaurant.isRestaurantOpen());
    }

    // >>>>>>>>>>>>>>>>>>>>>>>MENU TESTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }

    // >>>>>>>>>>>>>>>>>>>>>>>PART 3: ORDER TOTAL TESTS (TDD)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void calculating_order_total_for_single_item_should_return_item_price() {
        List<String> selectedItems = Arrays.asList("Sweet corn soup");
        double total = restaurant.calculateOrderTotal(selectedItems);
        assertEquals(119, total, 0.0);
    }

    @Test
    public void calculating_order_total_for_multiple_items_should_return_sum_of_prices() {
        List<String> selectedItems = Arrays.asList("Sweet corn soup", "Vegetable lasagne");
        double total = restaurant.calculateOrderTotal(selectedItems);
        assertEquals(388, total, 0.0); // 119 + 269
    }

    @Test
    public void calculating_order_total_for_all_items_should_return_total_menu_price() {
        restaurant.addToMenu("Sizzling brownie", 319);
        restaurant.addToMenu("Pasta", 199);
        
        List<String> selectedItems = Arrays.asList(
            "Sweet corn soup", 
            "Vegetable lasagne", 
            "Sizzling brownie", 
            "Pasta"
        );
        double total = restaurant.calculateOrderTotal(selectedItems);
        assertEquals(906, total, 0.0); // 119 + 269 + 319 + 199
    }

    @Test
    public void calculating_order_total_for_empty_list_should_return_zero() {
        List<String> selectedItems = Arrays.asList();
        double total = restaurant.calculateOrderTotal(selectedItems);
        assertEquals(0, total, 0.0);
    }

    @Test
    public void calculating_order_total_with_duplicate_items_should_count_each_occurrence() {
        List<String> selectedItems = Arrays.asList(
            "Sweet corn soup", 
            "Sweet corn soup", 
            "Vegetable lasagne"
        );
        double total = restaurant.calculateOrderTotal(selectedItems);
        assertEquals(507, total, 0.0); // 119 + 119 + 269
    }
}
