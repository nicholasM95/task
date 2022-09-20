package com.example.task.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "auth error")
public class AuthenticationNotFoundException extends RuntimeException{
}
