package com.catalog.freezer.service;

import com.catalog.freezer.model.Role;
import com.catalog.freezer.model.RoleName;

/**
 * The Interface RoleService.
 */
public interface RoleService {

	/**
	 * Find role by name.
	 *
	 * @param authority the authority
	 * @return the role
	 */
	Role findRoleByName(RoleName authority);
}
