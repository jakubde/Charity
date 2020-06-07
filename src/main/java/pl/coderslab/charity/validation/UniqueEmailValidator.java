package pl.coderslab.charity.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.repositories.UserRepository;
import pl.coderslab.charity.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueEmail constraint) {
    }

    @Override
    public boolean isValid(String inputEmail, ConstraintValidatorContext context) {

        UserDto userDto = userService.findUserByEmail(inputEmail);

        if (userDto != null) {
            return !userDto.getEnabled();
        } else {
            return true;
        }

    }
}
