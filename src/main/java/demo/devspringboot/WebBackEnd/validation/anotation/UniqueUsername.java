package demo.devspringboot.WebBackEnd.validation.anotation;

import demo.devspringboot.WebBackEnd.validation.validator.UniqueUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUsername {

    String message() default "Username is existed.";
    Class<?>[] groups() default {};
    Class <? extends Payload>[] payload() default {};

}
