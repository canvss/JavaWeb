package com.canvs.book.service;

import com.canvs.book.pojo.User;

public interface UserService {
    User login(String uname,String pwd);
    User regist(User user);
}
