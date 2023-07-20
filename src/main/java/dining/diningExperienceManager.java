package dining;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class diningExperienceManager {
    private List<meal> menu;
    public List<meal> getMenu() {
		return menu;
	}

	public void setMenu(List<meal> menu) {
		this.menu = menu;
	}

	public List<mealOrder> getSelectedMeals() {
		return selectedMeals;
	}

	public void setSelectedMeals(List<mealOrder> selectedMeals) {
		this.selectedMeals = selectedMeals;
	}

	private List<mealOrder> selectedMeals;
    private Scanner scanner;

    public diningExperienceManager() {
        menu = new ArrayList<meal>();
        selectedMeals = new ArrayList<mealOrder>();
        scanner = new Scanner(System.in);
    }

    public void addMealToMenu(meal meal) {
        menu.add(meal);
    }

    public void displayMenu() {
        System.out.println("Menu:");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i).getName() + " - $" + menu.get(i).getBasePrice());
        }
        System.out.println("Select meals by entering their numbers (separated by spaces):");
    }

    public void selectMeals() {
       
        String input = scanner.nextLine();
        String[] mealNumbers = input.split(" ");

        for (String number : mealNumbers) {
            try {
                int mealIndex = Integer.parseInt(number) - 1;
                if (mealIndex >= 0 && mealIndex < menu.size()) {
                    meal selectedMeal = menu.get(mealIndex);
                    System.out.print("Enter quantity for " + selectedMeal.getName() + ": ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    if (quantity > 0 && quantity <= 100) {
                        selectedMeals.add(new mealOrder(selectedMeal, quantity));
                    } else {
                        System.out.println("Invalid quantity. Quantity must be a positive integer between 1 and 100.");
                        return;
                    }
                } else {
                    System.out.println("Invalid meal number. Please select a valid meal number from the menu.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input format. Please enter valid meal numbers separated by spaces.");
                return;
            }
        }
    }

    public double calculateTotalCost() {
        double totalCost = 0;

        for (mealOrder mealOrder : selectedMeals) {
            totalCost += mealOrder.getMeal().getBasePrice() * mealOrder.getQuantity();
        }

        int totalQuantity = getTotalQuantity();
        if (totalQuantity > 5 && totalQuantity <= 10) {
            totalCost *= 0.9;
        } else if (totalQuantity > 10) {
            totalCost *= 0.8;
        }

        for (mealOrder mealOrder : selectedMeals) {
            if (mealOrder.getMeal().getCategory().equals("Chef's Specials")) {
                totalCost *= 1.05;
                break;
            }
        }

        if (totalCost > 50 && totalCost <= 100) {
            totalCost -= 10;
        } else if (totalCost > 100) {
            totalCost -= 25;
        }

        return totalCost;
    }

    private int getTotalQuantity() {
        int totalQuantity = 0;
        for (mealOrder mealOrder : selectedMeals) {
            totalQuantity += mealOrder.getQuantity();
        }
        return totalQuantity;
    }

    public boolean checkAvailability() {
        for (mealOrder mealOrder : selectedMeals) {
            if (!menu.contains(mealOrder.getMeal())) {
                System.out.println(mealOrder.getMeal().getName() + " is not available on the menu.");
                return false;
            }
        }
        return true;
    }

    public boolean confirmOrder() {
        System.out.println("Confirm your order (Y/N):");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("Y");
    }
}
