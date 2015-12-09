package it.guideland.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.Role;
import it.guideland.app.repositories.repositoriesCustom.RoleRepositoryCustom;

public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryCustom{

	Role findByRolename(String role);
	
}
