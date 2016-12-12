package bookStore.liuhang.service;

import java.sql.SQLException;

import bookStore.liuhang.dao.UserDao;
import bookStore.liuhang.domain.User;
import bookStore.liuhang.exception.UserException;
import bookStore.liuhang.util.EmailUtil;

/**
 * Created by liuhang on 2016/12/8.
 */
public class UserService {
    private UserDao userDao = new UserDao();

    //用户登陆
    public User userLogin(String username, String password) throws UserException {
        User user = null;
        try {
            user = userDao.findUserByUsernameAndPassword(username, password);
            if (user == null) {
                throw new UserException("用户名或者密码错误!");
            }
            if (user.getState() == 0) {
                throw new UserException("用户未激活");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("用户名或者密码错误!");
        }
        return user;
    }

    //用户注册
    public void userRegister(User user) throws UserException {
        try {
            userDao.addUser(user);//添加用户
            String emailMessage = "注册成功," +
                    "请<a href=\"http://localhost:8080/active?activeCode=" + user.getActiveCode() + "\">激活</a>后登录";
            EmailUtil.sendMail(user.getEmail(), emailMessage);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("注册失败");
        }
    }

    //用户激活
    public void userActive(String activeCode) throws UserException {
        try {
            User user = userDao.findUserByActiveCode(activeCode);
            if (user == null) {
                throw new UserException("激活失败");
            }
            userDao.updateActiveState(activeCode);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("激活失败");
        }
    }

    //根据id查找用户
    public User findUserById(int id) throws UserException {
        try {
            return userDao.findUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("修改用户信息");
        }
    }
}
