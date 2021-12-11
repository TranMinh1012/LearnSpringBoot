package org.aibles.userservice.service.iml;

import org.aibles.userservice.exception.UserNotFoundException;
import org.aibles.userservice.model.User;
import org.aibles.userservice.repository.UserRepository;
import org.aibles.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User getUser(int id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null ) {
            throw new UserNotFoundException();
        }
        else {
            return user;
        }
    }

    @Override
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
    public User updateUser(int id, User user) throws UserNotFoundException {
        User oldUser = userRepository.findById(id).orElse(null);
        if(oldUser != null){
            oldUser.setName(user.getName());
            oldUser.setAge(user.getAge());
            User userUpdated = userRepository.save(oldUser);
            return userUpdated;
        }
        else {
            throw new UserNotFoundException();
        }
    }
}
