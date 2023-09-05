package com.canvs.book.service.impl;

import com.canvs.book.dao.UserDAO;
import com.canvs.book.pojo.User;
import com.canvs.book.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    @Override
    public User login(String uname,String pwd) {
        return userDAO.getUser(uname,pwd);
    }

    @Override
    public User regist(User user) {
        return userDAO.addUser(user);
    }
}
