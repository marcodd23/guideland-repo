package it.guideland.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.guideland.app.model.Role;
import it.guideland.app.repositories.RoleRepository;
import it.guideland.app.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepo;
	
	@Override
	@Transactional
	public Role persistRoleAndFlush(Role adminRole) {
		return roleRepo.saveAndFlush(adminRole);
		
	}

	@Override
	public Role findByRolename(String role) {
		return roleRepo.findByRolename(role);
	}

}
