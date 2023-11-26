package lms.server.service;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import lms.server.api.UserService;
import lms.server.dao.UserDaoImpl;
import lms.shared.User;

public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao;
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public User authenticate(String username, String password) {
       
        return null;
    }

    public String saveUser(User saved) {
        try {
            return userDao.saveUser(saved);
        } catch (IOException e) {
            logger.error("Failed to save user: " + e.getMessage(), e);
            return "Failed to save user: " + e.getMessage();
        } catch (DataAccessException e) {
            logger.error("Failed to save user: " + e.getMessage(), e);
            return "Failed to save user: " + e.getMessage();
        }
    }

    public User getUser(String username) {
        try {
            return userDao.getUser(username);
        } catch (SQLException e) {
            logger.error("Failed to retrieve user with username " + username + ": " + e.getMessage(), e);
            return null; 
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve user with username " + username + ": " + e.getMessage(), e);
            return null;
        }
    }
}
