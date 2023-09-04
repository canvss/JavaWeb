package com.canvs.book.dao;

import com.canvs.book.pojo.User;

public interface UserDAO {
    User getUser(String uname, String pwd);
}
