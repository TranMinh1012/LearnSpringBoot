package org.aibles.userservice.controller;

import org.aibles.userservice.model.User;
import org.aibles.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") int id){
        User user = userService.getUser(id);
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        else {
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User newUser){
        User user = userService.getUser(newUser.getId());
        if(user != null){
            return new ResponseEntity<>( "Id is already exists. Please try again",HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            userService.createUser(newUser);
            return ResponseEntity.ok(newUser);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User userUpdated){
//        User user = userService.getUser(id);
//        if(user == null){
//            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
//        }
//        if(user.getId() == userUpdated.getId()){
//            userService.updateUser(userUpdated.getId(), userUpdated);
//            return ResponseEntity.ok(userUpdated);
//        }
//        else {
//            return new ResponseEntity<>("ID does not match with the ID on the path",HttpStatus.NOT_ACCEPTABLE);
//        }
        userService.createUser(userUpdated);
        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
        User user = userService.getUser(id);
        if(user != null){
            userService.deleteUser(id);
            return new ResponseEntity<>("Delete user successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAll(){
        userService.deleteAllUser();
        return new ResponseEntity<>("Delete all user successfully",HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
