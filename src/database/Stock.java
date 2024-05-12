package database;

public class Stock {
    private int article_id;
    private int stock_amount;
    private int warehouse_id;

    public Stock(int article_id, int stock_amount, int warehouse_id) {
        this.article_id = article_id;
        this.stock_amount = stock_amount;
        this.warehouse_id = warehouse_id;
    }

}
