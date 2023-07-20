package dining;

public class mealOrder {
    private meal meal;
    private int quantity;

    public mealOrder(meal meal, int quantity) {
        this.meal = meal;
        this.quantity = quantity;
    }

    public meal getMeal() {
        return meal;
    }

    public int getQuantity() {
        return quantity;
    }
}
