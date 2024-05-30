package com.tiendq3.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tiendq3.shared.Person;

public interface PersonServiceAsync {

	void getAllPerson(AsyncCallback<ArrayList<Person>> async);

	void getPersonByName(String name, AsyncCallback<Person> async);

	void insertPerson(Person person, AsyncCallback<Void> async);
}
