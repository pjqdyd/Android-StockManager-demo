package com.pengjiqing.stockmanagerapp.db;

public interface IUserDao {

    boolean login(String username,String pwd);

    boolean isUserName(String username);

    long addUser(String username,String pwd);
}
