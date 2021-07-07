package kl.socialnetwork.service.role;

import kl.socialnetwork.model.AppRole;
import kl.socialnetwork.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService implements IRoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public AppRole findRoleByName(String name) {
        return roleRepository.findAppRoleByName(name);
    }

    @Override
    public List<AppRole> findALl() {
        return roleRepository.findAll();
    }

    @Override
    public Page<AppRole> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public AppRole findById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public AppRole save(AppRole appRole) {
        return roleRepository.save(appRole);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
