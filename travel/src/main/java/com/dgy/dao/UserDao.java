package com.dgy.dao;

import com.dgy.domain.User;
import com.dgy.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Date: 2019/7/28
 * Time: 18:52
 * Author: vincent-Dou
 * Description：
 */
public class UserDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据用户名查询用户对象
     */
    public User findUserByUserName(String username){
        User user = null;
        String sql = "select * from tab_user where username = ?";
        user =jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        return user;
    }

    /**
     * 添加用户
     */
    public void addUser(User user){
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getName(),
                                 user.getBirthday(), user.getSex(), user.getTelephone(),
                                 user.getEmail(), user.getStatus(), user.getCode()
                );
    }

    /**
     * 根据激活码查询用户对象
     */
    public User findUserByCode(String code){
        String sql = "select * from tab_user where code = ?";
        User user = null;
        user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), code);
        return user;
    }

    public static void main(String[] args) {
        User user = new UserDao().findUserByCode("10c7ab10b3e84534beec37398f7e967d");
//        User user = new UserDao().findUserByUserName("douguangyao");
        System.out.println(user);
    }
}
