package bookStore.liuhang.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

import bookStore.liuhang.domain.User;
import bookStore.liuhang.util.C3p0Util;

/**
 * Created by liuhang on 2016/12/5.
 * UserDao  CRUD操作Create、Read、Update、Delete
 */
public class UserDao {
    public void addUser(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3p0Util.getDataSource());
        String sql = "insert into user(username,password,gender,email,telephone,introduce," +
                "activeCode,state,registerTime) values(?,?,?,?,?,?,?,?,?)";
        queryRunner.update(sql, user.getUsername(), user.getPassword(), user.getGender(), user
                .getEmail(), user.getTelephone(), user.getIntroduce(), user.getActiveCode(), user
                .getState(), user.getRegisterTime());
    }

    public User findUserByUsernameAndPassword(String username, String password) throws
            SQLException {
        QueryRunner queryRunner = new QueryRunner(C3p0Util.getDataSource());
        String sql = "select * from user where username=? and password=?";
        return queryRunner.query(sql, new BeanHandler<>(User.class), username, password);//此处存疑
    }
}
