package com.tiendq3.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.tiendq3.shared.Person;;

public class PersonDetailView extends Composite implements IsWidget {

	private static PersonDetailWidgetUiBinder uiBinder = GWT.create(PersonDetailWidgetUiBinder.class);

	interface PersonDetailWidgetUiBinder extends UiBinder<Widget, PersonDetailView> {
	}

	@UiField
	Button saveButton;

	@UiField
	HTMLPanel container;

	@UiField
	TextBox name;

	@UiField
	TextBox age;

	@UiField
	TextBox address;

	@UiField
	ListBox gender;

	public PersonDetailView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public PersonDetailView(Person person) {
		initWidget(uiBinder.createAndBindUi(this));
		name.setText(person.getName());
		age.setText(person.getAge() + "");
		gender.setSelectedIndex(person.getGender());
		address.setText(person.getAddress());
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

	public HTMLPanel getContainer() {
		return container;
	}

	public void setContainer(HTMLPanel container) {
		this.container = container;
	}

	public TextBox getName() {
		return name;
	}

	public void setName(TextBox name) {
		this.name = name;
	}

	public TextBox getAge() {
		return age;
	}

	public void setAge(TextBox age) {
		this.age = age;
	}

	public TextBox getAddress() {
		return address;
	}

	public void setAddress(TextBox address) {
		this.address = address;
	}

	public ListBox getGender() {
		return gender;
	}

	public void setGender(ListBox gender) {
		this.gender = gender;
	}
}
