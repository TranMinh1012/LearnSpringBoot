package org.aibles.userservice.service.iml;

import liquibase.pro.packaged.U;
import org.aibles.userservice.exception.UserNotFound;
import org.aibles.userservice.model.User;
import org.aibles.userservice.repository.UserRepository;
import org.aibles.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public User getUser(int id) throws UserNotFound {
        User user = userRepository.findById(id).orElse(null);
        if (user == null ) {
            throw new UserNotFound();
        }
        else {
            return user;
        }
    }

    @Override
    public void deleteUser(int id) throws UserNotFound {
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            throw new UserNotFound();
        }
        else {
            userRepository.deleteById(id);
        }
    }

    @Override
    public User updateUser(int id, User user) throws UserNotFound{
        User userUpdated = userRepository.findById(id).orElse(null);
        if(userUpdated == null){
            throw new UserNotFound();
        }
        else {
            userUpdated.setName(user.getName());
            userUpdated.setAge(user.getAge());
            userRepository.save(userUpdated);
            return userUpdated;
        }
    }


}
