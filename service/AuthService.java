package service;

import model.User;
import dao.UserDAO;

public class AuthService {
    private UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        return userDAO.authenticate(username, password);
    }

    public boolean hasRole(User user, String role) {
        return user != null && role.equals(user.getRole());
    }
}