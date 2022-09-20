package com.example.task.utils;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(
        factory = WithMockCustomUserSecurityContextFactory.class
)
public @interface WithCustomUser {

    String id() default "";
    String name() default "";
    String role() default "";
}
