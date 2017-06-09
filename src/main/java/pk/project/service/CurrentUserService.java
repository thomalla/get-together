package pk.project.service;

import pk.project.model.CurrentUser;

public interface CurrentUserService
{
    boolean canAccessUser(CurrentUser currentUser, Long userId);

}
