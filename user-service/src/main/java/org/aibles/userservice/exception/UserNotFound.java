package org.aibles.userservice.exception;

import org.springframework.http.HttpStatus;

public class UserNotFound extends AbstractException{
    public UserNotFound(){
        super("user not found exception message", HttpStatus.NOT_FOUND);
    }
}
