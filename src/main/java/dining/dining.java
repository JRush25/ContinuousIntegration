package dining;

public class dining {
    public static void main(String[] args) {
        diningExperienceManager manager = new diningExperienceManager();

        manager.addMealToMenu(new meal("Spaghetti Carbonara", 8.99, "Italian Food"));
        manager.addMealToMenu(new meal("Kung Pao Chicken", 7.49, "Chinese Food"));
        manager.addMealToMenu(new meal("Chocolate Cake", 4.99, "Pastries"));
        manager.addMealToMenu(new meal("Chef's Special Pasta", 10.99, "Chef's Specials"));
        manager.displayMenu();
        manager.selectMeals();
        
        
        if (!manager.checkAvailability()) {
            return;
        }

        
        double totalCost = manager.calculateTotalCost();
        System.out.println("Total cost: $" + totalCost);
        
        
        
        if (manager.confirmOrder()) {
            System.out.println("Order confirmed. Enjoy your dining experience!");
        } else {
            System.out.println("Order canceled. You can make changes to your selections.");
        }
    }
}
