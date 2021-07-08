package kl.socialnetwork.validations.serviceValidation.servicesImpl;

import kl.socialnetwork.domain.entities.UserRole;
import kl.socialnetwork.validations.serviceValidation.services.RoleValidationService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RoleValidationServiceImpl implements RoleValidationService {

    @Override
    public boolean isValid(UserRole role) {
        return role != null;
    }
}
