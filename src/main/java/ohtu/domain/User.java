package ohtu.domain;

public class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    
    public boolean equals(Object o) {
        if (!o.getClass().equals(User.class)) return false;
        
        User u = (User) o;
        return u.getUsername().equals(this.username) && u.getPassword().equals(this.password);
    }
}
