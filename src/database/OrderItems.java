package database;

public class OrderItems {
    private int ID;
    private int order_id;
    private int article_id;
    private int amount;

    public OrderItems(int ID, int order_id, int article_id, int amount) {
        this.ID = ID;
        this.order_id = order_id;
        this.article_id = article_id;
        this.amount = amount;
    }

}
