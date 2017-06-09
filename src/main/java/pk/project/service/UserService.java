package pk.project.service;


import pk.project.form.RegisterForm;
import pk.project.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService
{
    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByLogin(String login);

    Collection<User> getAllUsers();

    User register(RegisterForm form);

}
