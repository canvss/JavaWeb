package com.canvs.book.dao.impl;

import com.canvs.book.dao.UserDAO;
import com.canvs.book.pojo.User;
import com.canvs.ssm.base.dao.BaseDAO;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUser(String uname, String pwd) {
        String sql = "SELECT * FROM t_user WHERE uname=? AND pwd=?";
        return super.getBean(sql,uname,pwd);
    }

    @Override
    public User addUser(User user) {
        String sql = "INSERT INTO t_user(0,?,?,?,0)";
        int id = super.executeUpdate(sql, user.getUname(), user.getPwd(), user.getEmail());
        return new User(id);
    }
}
