package com.tiendq3.client.view;

import java.util.ArrayList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.tiendq3.shared.Person;

public class HomeView extends Composite implements IsWidget {

	private static HomeWidgetUiBinder uiBinder = GWT.create(HomeWidgetUiBinder.class);

	interface HomeWidgetUiBinder extends UiBinder<Widget, HomeView> {
	}

	@UiField
	FlexTable personTable;

	@UiField
	org.gwtbootstrap3.client.ui.Button saveButton;

	@UiField
	org.gwtbootstrap3.client.ui.TextBox name;

	@UiField
	org.gwtbootstrap3.client.ui.TextBox age;

	@UiField
	org.gwtbootstrap3.client.ui.TextBox address;

	@UiField
	org.gwtbootstrap3.client.ui.ListBox gender;

	public HomeView() {
		initWidget(uiBinder.createAndBindUi(this));
		personTable.setText(0, 0, "STT");
		personTable.setText(0, 1, "Tên");
		personTable.setText(0, 2, "Tuổi");
		personTable.getCellFormatter().addStyleName(0, 0, "header-cell");
		personTable.getCellFormatter().addStyleName(0, 1, "header-cell");
		personTable.getCellFormatter().addStyleName(0, 2, "header-cell");

		this.name.addStyleName("gwtb-textBox");
		this.age.addStyleName("gwtb-textBox");
		this.gender.addStyleName("gwtb-textBox");
		this.saveButton.addStyleName("saveButton");
//		this.name.addStyleName("gwtb-textBox");
	}

	public void showPersons(ArrayList<Person> persons) {
		for (Person person : persons) {
			int rowCount = personTable.getRowCount();
			personTable.setText(rowCount, 0, rowCount + "");
			personTable.setText(rowCount, 1, person.getName());
			personTable.setText(rowCount, 2, person.getAge() + "");
			personTable.getCellFormatter().setStylePrimaryName(rowCount, 0, "data-cell");
			personTable.getCellFormatter().setStylePrimaryName(rowCount, 1, "data-cell");
			personTable.getCellFormatter().setStylePrimaryName(rowCount, 2, "data-cell");
		}
	}

	public void addPerson(Person newPerson, ArrayList<Person> personListBD) {
		FlexTable personTable = this.getPersonTable();
		int rowCount = personTable.getRowCount();
		personTable.setText(rowCount, 0, rowCount + "");
		personTable.setText(rowCount, 1, newPerson.getName());
		personTable.setText(rowCount, 2, newPerson.getAge() + "");
		personTable.getCellFormatter().setStylePrimaryName(rowCount, 0, "data-cell");
		personTable.getCellFormatter().setStylePrimaryName(rowCount, 1, "data-cell");
		personTable.getCellFormatter().setStylePrimaryName(rowCount, 2, "data-cell");
		personListBD.add(newPerson);
	}

	// validate success -> insert to server -> re-render ui
	public Person validatePerson(ArrayList<Person> personListBD) {
		String name = this.name.getValue();
		String age = this.age.getValue();
		int gender = this.gender.getSelectedIndex();
		String address = this.address.getValue();
		Person newPerson = new Person(name, Integer.valueOf(age), gender, address);
		if (name.equals("") || age.equals("") || address.equals("")) {
			Window.alert("nhap thong tin day du");
			return null;
		}
		if (checkExistName(name, personListBD))
			return null;
		return newPerson;
	}

	boolean checkExistName(String name, ArrayList<Person> personListBD) {
		GWT.log(personListBD.toString());
		for (Person person : personListBD) {
			if (person.getName().equals(name)) {
				Window.alert("ten trung");
				return true;
			}
		}
		return false;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	public HasClickHandlers getRowClick() {
		return address;
	}

	public FlexTable getPersonTable() {
		return personTable;
	}

	public void setPersonTable(FlexTable personTable) {
		this.personTable = personTable;
	}

	public org.gwtbootstrap3.client.ui.TextBox getName() {
		return name;
	}

	public void setName(org.gwtbootstrap3.client.ui.TextBox name) {
		this.name = name;
	}

	public org.gwtbootstrap3.client.ui.TextBox getAge() {
		return age;
	}

	public void setAge(org.gwtbootstrap3.client.ui.TextBox age) {
		this.age = age;
	}

	public org.gwtbootstrap3.client.ui.TextBox getAddress() {
		return address;
	}

	public void setAddress(org.gwtbootstrap3.client.ui.TextBox address) {
		this.address = address;
	}

	public org.gwtbootstrap3.client.ui.ListBox getGender() {
		return gender;
	}

	public void setGender(org.gwtbootstrap3.client.ui.ListBox gender) {
		this.gender = gender;
	}

	public void setSaveButton(org.gwtbootstrap3.client.ui.Button saveButton) {
		this.saveButton = saveButton;
	}
}
