package pk.project.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pk.project.form.RegisterForm;
import pk.project.service.UserService;

@Component
public class RegisterValidator implements Validator
{
    private final UserService userService;

    @Autowired
    public RegisterValidator(UserService userService)
    {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass)
    {
        return aClass.equals(RegisterForm.class);
    }

    @Override
    public void validate(Object o, Errors errors)
    {
        RegisterForm form = (RegisterForm) o;
        validatePasswords(errors, form);
        validateEmail(errors, form);
        validateLogin(errors,form);
    }

    private void validatePasswords(Errors errors, RegisterForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "Hasla nie zgadzaja sie");
        }
    }

    private void validateEmail(Errors errors, RegisterForm form) {
        if (userService.getUserByEmail(form.getEmail()).isPresent()) {
            errors.reject("email.exists", "Uzytkownik z takim mailem jest juz w bazie");
        }
    }

    private void validateLogin(Errors errors, RegisterForm form) {
        if (userService.getUserByLogin(form.getLogin()).isPresent()) {
            errors.reject("login.exists", "Uzytkownik z takim loginem jest juz w bazie");
        }
    }
}
