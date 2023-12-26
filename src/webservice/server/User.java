package webservice.server;

import lombok.Data;

@Data
public class User {
 
    private Integer id;
    private String username;
    private String birthday;

    public User() {}

    public User(Integer id, String username, String birthday) {
        this.id = id;
        this.username = username;
        this.birthday = birthday;
    }
}