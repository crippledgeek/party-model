package se.disabledsecurity.service;

import se.disabledsecurity.model.Person;
import se.disabledsecurity.model.Child;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryPersonService implements PersonService {
	private final Map<String, Person> personMap;

	public InMemoryPersonService() {
		personMap = new HashMap<>();
	}

	public void savePerson(Person person) {
		personMap.put(person.getSocialSecurityNumber(), person);
	}

	public Optional<Person> getPersonBySSN(String ssn) {
		return Optional.ofNullable(personMap.get(ssn));
	}

	public String getOldestChildNameByParentSocialSecurityNumber(String parentSocialSecurityNumber) {
		return personMap.values().stream()
						.filter(person -> person.getSocialSecurityNumber().equals(parentSocialSecurityNumber))
						.flatMap(person -> person.getChildren().stream())
						.max(Comparator.comparingInt(Child::getAge))
						.map(Child::getName)
				.orElse(null);
	}
}
