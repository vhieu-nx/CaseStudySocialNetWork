package kl.socialnetwork.repository;

import kl.socialnetwork.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findAppRoleByName(String name);
}
