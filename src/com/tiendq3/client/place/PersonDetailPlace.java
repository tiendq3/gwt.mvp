package com.tiendq3.client.place;

import com.tiendq3.shared.Person;

public class PersonDetailPlace extends BasicPlace {
	Person person = null;

	public PersonDetailPlace() {
		this.token = PlaceConfig.PERSON_DEATAIL_PLACE;
	}

	public PersonDetailPlace(Person person) {
		this.token = PlaceConfig.PERSON_DEATAIL_PLACE;
		this.person = person;
	}

	public PersonDetailPlace(String token, Person person) {
		this.token = token;
		this.person = person;
	}

	public PersonDetailPlace(String token) {
		this.token = token;
	}

	public Person getPerson() {
		return person;
	}

}
