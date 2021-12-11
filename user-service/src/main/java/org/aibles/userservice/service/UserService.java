package org.aibles.userservice.service;

import org.aibles.userservice.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUser(int id);
    void deleteUser(int id);
    User updateUser(int id, User user);
}
