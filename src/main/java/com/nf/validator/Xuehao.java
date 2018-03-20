package com.nf.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = XuehaoValidator.class)
public @interface Xuehao {

    String message() default "学号的格式不正确";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
