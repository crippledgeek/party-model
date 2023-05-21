package se.disabledsecurity.service;

import se.disabledsecurity.model.Person;

import java.util.Optional;

public interface PersonService {

	/**
	 * Saves a person in the service.
	 *
	 * @param person The person to be saved.
	 */
	void savePerson(Person person);

	/**
	 * Retrieves a person by their social security number.
	 *
	 * @param ssn The social security number of the person to retrieve.
	 * @return An @{@link java.util.Optional<se.disabledsecurity.model.Person>} containing the retrieved person, or empty if not found.
	 */
	Optional<Person> getPersonBySSN(String ssn);

	/**
	 * Retrieves the name of the oldest child associated with a parent's social security number.
	 *
	 * @param parentSocialSecurityNumber The social security number of the parent.
	 * @return A @{@link java.lang.String} containing the name of the oldest child, or null if no children or parent not found.
	 */
	String getOldestChildNameByParentSocialSecurityNumber(String parentSocialSecurityNumber);

	}
