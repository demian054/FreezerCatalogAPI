package com.catalog.freezer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.catalog.freezer.model.Role;
import com.catalog.freezer.model.RoleName;


/**
 * The Interface RoleRepository.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	/**
	 * Find by role name.
	 *
	 * @param roleName the role name
	 * @return the optional
	 */
	Optional<Role> findByRoleName(RoleName roleName);
}
