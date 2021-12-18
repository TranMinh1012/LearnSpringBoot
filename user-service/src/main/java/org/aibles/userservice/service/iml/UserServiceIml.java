package org.aibles.userservice.service.iml;

import org.aibles.userservice.exception.UserNotFoundException;
import org.aibles.userservice.model.User;
import org.aibles.userservice.repository.UserRepository;
import org.aibles.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIml implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceIml.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceIml(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        log.info("Call getAllUsers from DB");
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        log.info("add user with id - {}", user.getId());
        return userRepository.save(user);
//        User userCreated  = userRepository.save(user);
//        return userCreated;
    }

    @Override
    @Cacheable(cacheNames = "users", key = "#id")
    public User getUser(int id){
        log.info("fetching user from db");
        User user = userRepository.findById(id).orElse(null);
        if (user == null ) {
            throw new UserNotFoundException();
        }
        else {
            return user;
        }
    }

    @Override
    @CacheEvict(cacheNames = "users", key = "#id")
    public void deleteUser(int id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            throw new UserNotFoundException();
        }
        else {
            userRepository.deleteById(id);
        }
    }

    @Override
    @CachePut(cacheNames = "users")
    public User updateUser(int id, User user) throws UserNotFoundException {
        User oldUser = userRepository.findById(id).orElse(null);
        if(oldUser != null){
            oldUser.setName(user.getName());
            oldUser.setAge(user.getAge());
            User userUpdated = userRepository.save(oldUser);
            log.info("user updated with new name");
            return userUpdated;
        }
        else {
            throw new UserNotFoundException();
        }
//        userRepository.updateAddress(user.getId(), user.getName());
//        log.info("user updated with new name");
//        return user;
    }

}
