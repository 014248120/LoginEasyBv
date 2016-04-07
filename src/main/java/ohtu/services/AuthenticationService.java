package ohtu.services;

import ohtu.domain.User;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        return userDao.listAll().contains(new User(username, password));
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        return (invalidPassword(password) || invalidUsername(username));
    }
    
    private boolean invalidPassword(String password) {
        return (password.length()<8 || password.matches("[a-zA-Z]+"));
    }
    
    private boolean invalidUsername(String username) {
        return username.length()<3;
    }
}
