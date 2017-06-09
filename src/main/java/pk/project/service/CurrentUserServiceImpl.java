package pk.project.service;

import pk.project.model.CurrentUser;
import pk.project.util.Role;

public class CurrentUserServiceImpl implements CurrentUserService
{
    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
    }
}
