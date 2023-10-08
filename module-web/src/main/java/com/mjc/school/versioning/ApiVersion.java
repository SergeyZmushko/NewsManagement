package com.mjc.school.versioning;

import java.lang.annotation.*;


@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {
    int value() default 1;
}
