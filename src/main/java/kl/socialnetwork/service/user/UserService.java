package kl.socialnetwork.service.user;

import kl.socialnetwork.model.AppRole;
import kl.socialnetwork.model.AppUser;
import kl.socialnetwork.repository.UserRepository;
import kl.socialnetwork.service.relationship.RelationshipService;
import kl.socialnetwork.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private RelationshipService relationshipService;

    @Override
    public List<AppUser> findALl() {
        return userRepository.findAll();
    }

    @Override
    public Page<AppUser> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public AppUser findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public AppUser save(AppUser appUser) {
        if (appUser.getRoles() == null) {
            AppRole role = roleService.findRoleByName("ROLE_USER");
            Set<AppRole> roles = new HashSet<>();
            roles.add(role);
            appUser.setRoles(roles);
        }
        return userRepository.save(appUser);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findAppUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
//        AppUser user = userRepository.findAppUserByUsername(username);
//        if (user == null){
//            throw new UsernameNotFoundException(username);
//        }
//        return UserPrinciple.build(user);
        return null;
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return userRepository.existsAppUserByUsername(username);
    }

    @Override
    public AppUser getCurrentUser() {
        AppUser user;
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        user = this.findUserByUsername(userName);
        return user;
    }

    @Override
    public List<AppUser> searchUserByName(String name) {
        return userRepository.findAppUserByName(name);
    }
//
//    public List<AppUser> showAllNoFriend(){
//        List<AppUser> listUserNoFriend = new ArrayList<>();
//        listUserNoFriend = userRepository.findAll();
//        for (int i = 0; i < listUserNoFriend.size(); i++) {
//            if(this.checkFriend(getCurrentUser().getId(), listUserNoFriend.get(i).getId()) == true){
//             listUserNoFriend.remove(listUserNoFriend.get(i));
//            }
//        }
//        return listUserNoFriend;
//    }
//    public boolean checkFriend(Long userSendId, Long userReceiveId) {
//        boolean isFriend = false;
//        if (relationshipService.findRelationshipByUserSendIdAndUserReceiveId(userSendId, userReceiveId).getStatus().getId() == 2) {
//            isFriend = true;
//        } else if (relationshipService.findRelationshipByUserSendIdAndUserReceiveId(userReceiveId, userSendId).getStatus().getId() == 2) {
//            isFriend = true;
//        } else isFriend = false;
//        return isFriend;
//    }
}
