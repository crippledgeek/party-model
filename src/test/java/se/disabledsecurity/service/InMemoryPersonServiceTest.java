package se.disabledsecurity.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.disabledsecurity.model.Child;
import se.disabledsecurity.model.Person;

import java.util.Arrays;
import java.util.Optional;

class InMemoryPersonServiceTest {

	private static PersonService personService;

	@BeforeAll
	public static void setup() {
		personService = new InMemoryPersonService();
	}

	@Test
	void testShouldReturnAValidPersonIfItExistsInDatabase() {
		// Create a person
		Person person = new Person();
		person.setName("John Doe");
		person.setSocialSecurityNumber("123-45-6789");

		// Save the person
		personService.savePerson(person);

		// Retrieve the person by social security number
		Optional<Person> retrievedPerson = personService.getPersonBySSN("123-45-6789");

		// Verify that the retrieved person is not empty and matches the saved person
		Assertions.assertTrue(retrievedPerson.isPresent());
		Assertions.assertEquals(person, retrievedPerson.get());
	}

	@Test
	void testShouldReturnAnEmptyOptionalIfPersonDontExistInDatabase() {
		Optional<Person> retrievedPerson = personService.getPersonBySSN("987-65-4321");
		Assertions.assertTrue(retrievedPerson.isEmpty());
	}

	@Test
	void testGetOldestChildName() {
		// Create a person
		Person person = new Person();
		person.setName("John Doe");
		person.setSocialSecurityNumber("123-45-6789");

		// Create children
		Child child1 = new Child("Alice", 10);
		Child child2 = new Child("Bob", 12);
		Child child3 = new Child("Charlie", 8);

		// Set children for the person
		person.setChildren(Arrays.asList(child1, child2, child3));

		// Save the person
		personService.savePerson(person);

		// Retrieve the oldest child's name for the person
		String oldestChildName = personService.getOldestChildNameByParentSocialSecurityNumber(person.getSocialSecurityNumber());

		// Verify that the oldest child's name matches the expected value
		Assertions.assertEquals("Bob", oldestChildName);
	}
}
