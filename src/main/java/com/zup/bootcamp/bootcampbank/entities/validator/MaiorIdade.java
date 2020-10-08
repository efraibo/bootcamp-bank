package com.zup.bootcamp.bootcampbank.entities.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

//https://stackoverflow.com/questions/29637732/annotation-for-hibernate-validator-for-a-date-at-least-24-hours-in-the-future
@Target({ FIELD, METHOD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaiorIdadeValidator.class)
@Documented
public @interface MaiorIdade {
    String message() default "{MaiorIdade.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
