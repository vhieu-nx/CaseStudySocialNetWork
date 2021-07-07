package kl.socialnetwork.service.role;

import kl.socialnetwork.model.AppRole;
import kl.socialnetwork.service.IService;

public interface IRoleService extends IService<AppRole> {
    AppRole findRoleByName(String name);
}
