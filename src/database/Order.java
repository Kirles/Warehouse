package database;

public class Order {
    private int order_type_id;
    private int user_id;
    private boolean completed;

    public Order(int order_type_id, int user_id, boolean completed) {
        this.order_type_id = order_type_id;
        this.user_id = user_id;
        this.completed = completed;
    }

}
