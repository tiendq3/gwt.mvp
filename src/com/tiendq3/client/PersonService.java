package com.tiendq3.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tiendq3.shared.Person;

@RemoteServiceRelativePath("persons")
public interface PersonService extends RemoteService {

	ArrayList<Person> getAllPerson();
	
	Person getPersonByName(String name);

	void insertPerson(Person person);
}
