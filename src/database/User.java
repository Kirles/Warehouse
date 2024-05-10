package database;

public class User {
    private int ID;
    private String name;
    private String email;
    private int user_type_id;

    public User(int ID, String name, String email, int user_type_id) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.user_type_id = user_type_id;
    }

}
