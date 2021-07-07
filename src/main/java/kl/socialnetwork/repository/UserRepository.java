package kl.socialnetwork.repository;

import kl.socialnetwork.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository  extends JpaRepository<AppUser,Long> {
    AppUser findAppUserByUsername(String username);
    boolean existsAppUserByUsername(String username);
    List<AppUser> findAppUsersByUsernameLike(String username);
    @Query(value = "select * from app_user where app_user.username like ?", nativeQuery = true)
    List<AppUser> findAppUserByName(String username);
}
