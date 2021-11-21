package cinema.app.service;

import cinema.app.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}
