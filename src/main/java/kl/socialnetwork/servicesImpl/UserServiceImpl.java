package kl.socialnetwork.servicesImpl;

import kl.socialnetwork.domain.entities.User;
import kl.socialnetwork.domain.models.serviceModels.UserServiceModel;
import kl.socialnetwork.domain.models.viewModels.user.UserCreateViewModel;
import kl.socialnetwork.domain.models.viewModels.user.UserDetailsViewModel;
import kl.socialnetwork.domain.models.viewModels.user.UserEditViewModel;
import kl.socialnetwork.repositories.RoleRepository;
import kl.socialnetwork.repositories.UserRepository;
import kl.socialnetwork.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
//    private final ModelMapper modelMapper;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final UserValidationService userValidation;
//    private final LoggerService loggerService;


//    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, UserValidationService userValidation, LoggerService loggerService) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.modelMapper = modelMapper;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        this.userValidation = userValidation;
//        this.loggerService = loggerService;
//    }

    @Override
    public UserCreateViewModel createUser(UserServiceModel userRegisterBindingModel) {
        return null;
    }

    @Override
    public boolean updateUser(UserServiceModel userUpdateBindingModel, String loggedInUserId) throws Exception {
        return false;
    }

    @Override
    public UserServiceModel updateUserOnlineStatus(String userName, boolean changeToOnline) throws Exception {
        return null;
    }

    @Override
    public UserDetailsViewModel getById(String id) throws Exception {
        return null;
    }

    @Override
    public UserEditViewModel editById(String id) throws Exception {
        return null;
    }

    @Override
    public User getByEmailValidation(String email) {
        return null;
    }

    @Override
    public User getByUsernameValidation(String username) {
        return null;
    }

    @Override
    public List<UserServiceModel> getAllUsers(String userId) throws Exception {
        return null;
    }

    @Override
    public boolean promoteUser(String id) throws Exception {
        return false;
    }

    @Override
    public boolean demoteUser(String id) throws Exception {
        return false;
    }

    @Override
    public boolean deleteUserById(String id) throws Exception {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
