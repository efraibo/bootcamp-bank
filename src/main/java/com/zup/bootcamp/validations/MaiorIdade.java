package com.zup.bootcamp.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

//https://stackoverflow.com/questions/29637732/annotation-for-hibernate-validator-for-a-date-at-least-24-hours-in-the-future
@Target({FIELD, METHOD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaiorIdadeValidator.class)
@Documented
public @interface MaiorIdade {
    String message() default "{campo.data-nascimento.maior-idade}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
