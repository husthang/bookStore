package bookStore.liuhang.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

import bookStore.liuhang.domain.User;
import bookStore.liuhang.util.C3p0Util;
import bookStore.liuhang.util.MD5Util;

/**
 * Created by liuhang on 2016/12/5.
 * UserDao  CRUD操作Create、Read、Update、Delete
 */
public class UserDao {
    public void addUser(User user) throws SQLException {
        /**
         * Apache:commons-dbutils,对jdbc的简单封装
         */
        QueryRunner queryRunner = new QueryRunner(C3p0Util.getDataSource());
        String sql = "insert into user(username,password,gender,email,telephone,introduce," +
                "activeCode,state,registerTime) values(?,?,?,?,?,?,?,?,?)";
        queryRunner.update(sql, user.getUsername(), MD5Util.md5(user.getPassword()), user.getGender(), user
                .getEmail(), user.getTelephone(), user.getIntroduce(), user.getActiveCode(), user
                .getState(), user.getRegisterTime());
    }

    public User findUserByUsernameAndPassword(String username, String password) throws
            SQLException {
        QueryRunner queryRunner = new QueryRunner(C3p0Util.getDataSource());
        String sql = "select * from user where username=? and password=?";
        /**
         * BeanHandler(Class claxx)
         *   * 实现了ResultSetHandler接口
         *   * 该类完成把表中的数据放置到结果集中第一行记录放置到javaBean,
         *   * new BeanHandler(Emp.class);
         *      *  该类构造方法的参数Emp.class,表示要把表中的记录封装到哪个javaBean中
         *   * 要求:
         *     * 使用 BeanHandler类时,javaBean中的属性必须和表中的字段名要一致,不区分大小写
         *     * 底层代码在查找是,就是通过表中的字段和javaBean的属性的名称一致对应
         *     new BeanHandler<>(User.class),编译器可自动推断出new 返回一个User类型,<>中间可省略 泛型框架,用法需要看一下!
         *     Class<T>进行类型匹配
         */
        /**
         * //这样也可以,此处存疑
         * return (User) queryRunner.query(sql, new BeanHandler(User.class), username, password);
         */
        return queryRunner.query(sql, new BeanHandler<>(User.class), username, password);
    }

    public User findUserByActiveCode(String activeCode) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3p0Util.getDataSource());
        return queryRunner.query("select * from user where activeCode=?", new BeanHandler<>(User
                .class), activeCode);
    }

    public void updateActiveState(String activeCode) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3p0Util.getDataSource());
        queryRunner.update("update user set state=1 where activeCode=?", activeCode);
    }
}
