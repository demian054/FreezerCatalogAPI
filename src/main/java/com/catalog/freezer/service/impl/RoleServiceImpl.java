package com.catalog.freezer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalog.freezer.model.Role;
import com.catalog.freezer.model.RoleName;
import com.catalog.freezer.repository.RoleRepository;
import com.catalog.freezer.service.RoleService;



/**
 * role service
 */
@Service
public class RoleServiceImpl implements RoleService {

	/** The role repository. */
	@Autowired
	private RoleRepository roleRepository;

	/*
	 * find role by RoleName
	 */
	@Override
	public Role findRoleByName(RoleName roleName) {
		return roleRepository.findByRoleName(roleName).orElse(null);
	}
}
