package bookStore.liuhang.service;

import bookStore.liuhang.dao.UserDao;
import bookStore.liuhang.domain.User;
import bookStore.liuhang.exception.UserException;

import java.sql.SQLException;

/**
 * Created by liuhang on 2016/12/8.
 */
public class UserService {
    private UserDao userDao = new UserDao();

    public User userLogin(String username, String password) throws UserException{
        User user = null;
        try {
            user = userDao.findUserByUsernameAndPassword(username, password);
            if(user==null){
                throw new UserException("用户名或者密码错误!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("用户名或者密码错误!");
        }
        return user;
    }

    public void userRegister(User user) {
        try {
            userDao.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
