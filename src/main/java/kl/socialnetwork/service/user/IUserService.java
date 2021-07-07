package kl.socialnetwork.service.user;

import kl.socialnetwork.model.AppUser;
import kl.socialnetwork.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends IService<AppUser>, UserDetailsService {
    AppUser findUserByUsername(String username);
    boolean existsUserByUsername(String username);
    AppUser getCurrentUser();
    List<AppUser> searchUserByName(String name);
}
