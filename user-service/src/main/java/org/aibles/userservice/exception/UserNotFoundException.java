package org.aibles.userservice.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AbstractException{
    public UserNotFoundException(){
        super("user not found exception message", HttpStatus.NOT_FOUND);
    }
}
