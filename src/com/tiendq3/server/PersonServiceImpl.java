package com.tiendq3.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tiendq3.client.PersonService;
import com.tiendq3.shared.Person;

@SuppressWarnings("serial")
public class PersonServiceImpl extends RemoteServiceServlet implements PersonService {
	private ArrayList<Person> persons = new ArrayList<>(
			List.of(new Person("user1", 10, 0, "hn"), new Person("user2", 20, 1, "hn")));

	@Override
	public ArrayList<Person> getAllPerson() {
		return persons;
	}

	@Override
	public Person getPersonByName(String name) {
		for (Person person : persons) {
			if (person.getName().equals(name))
				return person;
		}
		return null;
	}

	@Override
	public void insertPerson(Person person) {
		persons.add(person);
	}

}
