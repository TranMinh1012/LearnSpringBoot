package org.aibles.userservice.service;

import org.aibles.userservice.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUser(int id);
    //User updateUser(User user);
    //User updateUser(int id, User user);
    void deleteUser(int id);
    void deleteAllUser();
    List<User> getAllUsers();
}
