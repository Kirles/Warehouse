package database;

public class Article {
    private String name;
    private float weight;
    private String manufacture;
    private int article_type_id;
    
    public Article(String name, float weight, String manufacture, int article_type_id) {
        this.name = name;
        this.weight = weight;
        this.manufacture = manufacture;
        this.article_type_id = article_type_id;
    }

}
