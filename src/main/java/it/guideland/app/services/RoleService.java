package it.guideland.app.services;

import it.guideland.app.model.Role;

public interface RoleService {

	Role persistRoleAndFlush(Role adminRole);

	Role findByRolename(String role);

}
