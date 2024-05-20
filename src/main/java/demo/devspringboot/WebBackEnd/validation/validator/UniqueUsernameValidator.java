package demo.devspringboot.WebBackEnd.validation.validator;

import demo.devspringboot.WebBackEnd.user.model.User;
import demo.devspringboot.WebBackEnd.user.repository.UserRepository;
import demo.devspringboot.WebBackEnd.validation.anotation.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private String message;
    UserRepository userRepository;

    public UniqueUsernameValidator(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        Optional <User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()){
            return true;
        }
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
