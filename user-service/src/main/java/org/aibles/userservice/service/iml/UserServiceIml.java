package org.aibles.userservice.service.iml;

import liquibase.pro.packaged.U;
import org.aibles.userservice.model.User;
import org.aibles.userservice.repository.UserRepository;
import org.aibles.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIml implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceIml(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        User userCreated  = userRepository.save(user);
        return userCreated;
    }

    @Override
    public User getUser(int id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

//    @Override
//    public User updateUser(User user) {
//        User userUpdated  = userRepository.save(user);
//        return userUpdated;
//    }

//    @Override
//    public User updateUser(int id, User user) {
//        User userUpdate = userRepository.save(user);
//        return userUpdate;
//    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAllUser() {
        userRepository.deleteAll();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
