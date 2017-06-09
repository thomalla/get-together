package pk.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pk.project.model.CurrentUser;
import pk.project.model.User;


@Service
public class CurrentUserDetailsService implements UserDetailsService
{
    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.getUserByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", login)));
        CurrentUser cu=new CurrentUser(user);
        System.out.println(cu.toString());
        return cu;
    }
}
