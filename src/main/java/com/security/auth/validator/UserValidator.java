package com.security.auth.validator;

import com.security.auth.model.User;
import com.security.auth.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "userform.username.empty");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "userform.username.size");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "userform.username.duplicate");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "userForm.password.size");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "userForm.passwordConfirm.difference");
        }
    }
}
