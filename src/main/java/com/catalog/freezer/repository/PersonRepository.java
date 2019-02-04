
package com.catalog.freezer.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.catalog.freezer.model.Person;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	/**
	 * Find person by email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	Optional<Person> findPersonByEmail(String email);
}
