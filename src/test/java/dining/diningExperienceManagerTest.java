package dining;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class diningExperienceManagerTest {


   @Test
    public void testAddMealToMenu() {
        diningExperienceManager manager = new diningExperienceManager();
        meal meal = new meal("Spaghetti Carbonara", 8.99, "Italian Food");
        manager.addMealToMenu(meal);
        assertTrue(manager.getMenu().contains(meal));
    }

    @Test
    public void testSelectMeals() {
    	
        diningExperienceManager manager = new diningExperienceManager();
        manager.addMealToMenu(new meal("Spaghetti Carbonara", 8.99, "Italian Food"));
        manager.addMealToMenu(new meal("Kung Pao Chicken", 7.49, "Chinese Food"));
        manager.addMealToMenu(new meal("Chocolate Cake", 4.99, "Pastries"));
        
        String input = "1 2 3\n2\n3\n1\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        System.out.print("Hola mundo");
        manager.selectMeals();
        List<mealOrder> selectedMeals = manager.getSelectedMeals();
        System.out.print(selectedMeals);
        assertEquals("Spaghetti Carbonara", selectedMeals.get(0).getMeal().getName());
        assertEquals(2, selectedMeals.get(0).getQuantity());
        assertEquals("Kung Pao Chicken", selectedMeals.get(1).getMeal().getName());
        assertEquals(3, selectedMeals.get(1).getQuantity());
        assertEquals("Chocolate Cake", selectedMeals.get(2).getMeal().getName());
        assertEquals(1, selectedMeals.get(2).getQuantity());
        System.setIn(stdin);
    }

    
    
    
    
   @Test
    public void testCalculateTotalCost() {
        diningExperienceManager manager = new diningExperienceManager();

        // Agregar comidas al men�
        manager.addMealToMenu(new meal("Spaghetti Carbonara", 8.99, "Italian Food"));
        manager.addMealToMenu(new meal("Kung Pao Chicken", 7.49, "Chinese Food"));
        manager.addMealToMenu(new meal("Chocolate Cake", 4.99, "Pastries"));

        // Seleccionar comidas y cantidades
        
        InputStream stdin = System.in;
        manager.displayMenu();
        System.setIn(new ByteArrayInputStream("1 2 3\\n2\\n3\\n1\\n".getBytes()));
        manager.selectMeals();

        // Calcular el costo total
        double totalCost = manager.calculateTotalCost();

        // Verificar que el costo total sea el esperado
        assertEquals(8.99 * 2 + 7.49 * 3 + 4.99, totalCost, 0.001);
    }

   @Test
    public void testCheckAvailability() {
        diningExperienceManager manager = new diningExperienceManager();

        // Agregar comidas al men�
       meal meal1 = new meal("Spaghetti Carbonara", 8.99, "Italian Food");
        manager.addMealToMenu(meal1);

        // Seleccionar comidas
        InputStream stdin = System.in;
        manager.displayMenu();
        System.setIn(new ByteArrayInputStream("1 2\\n1\\n".getBytes()));
   
        manager.selectMeals();

        // Verificar disponibilidad de las comidas seleccionadas
        assertTrue(manager.checkAvailability());

        // Agregar nueva comida al men�
        meal meal2 = new meal("Kung Pao Chicken", 7.49, "Chinese Food");
        manager.addMealToMenu(meal2);

        System.setIn(new ByteArrayInputStream("1 2\\n1\\n".getBytes()));
   
        manager.selectMeals();

        // Verificar disponibilidad de las comidas seleccionadas
        assertFalse(manager.checkAvailability());
    }
}
