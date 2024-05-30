package com.tiendq3.shared;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private int age;

	private int gender;

	private String address;

	public Person() {
	}

	public Person(String name, int age, int gender, String address) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", gender=" + gender + ", address=" + address + "]";
	}

	public static Person personStringToObject(String personStr) {
		personStr = personStr.substring(7, personStr.length() - 1);
		String[] keyValuePairs = personStr.split(", ");
		String[] result = new String[keyValuePairs.length];

		for (int i = 0; i < keyValuePairs.length; i++) {
			String[] parts = keyValuePairs[i].split("=");
			if (parts.length == 2) {
				result[i] = parts[1];
			}
		}

		return new Person(result[0], Integer.valueOf(result[1]), Integer.valueOf(result[2]), result[3]);
	}
}
