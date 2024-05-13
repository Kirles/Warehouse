package database;

import java.sql.Date;

public class Order {
    private int order_type_id;
    private int user_id;
    private boolean completed;
    private Date date;

    public Order(int order_type_id, int user_id, boolean completed, Date date) {
        this.order_type_id = order_type_id;
        this.user_id = user_id;
        this.completed = completed;
        this.date = date;
    }

}
