package com.tiendq3.client.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.tiendq3.client.ClientFactory;
import com.tiendq3.client.PersonService;
import com.tiendq3.client.PersonServiceAsync;
import com.tiendq3.client.place.BasicPlace;
import com.tiendq3.client.place.HomePlace;
import com.tiendq3.client.place.PersonDetailPlace;
import com.tiendq3.client.view.PersonDetailView;
import com.tiendq3.shared.Person;

public class PersonDetailActivity implements Activity {

	private ClientFactory clientFactory;
	private Person person;
	private PersonDetailPlace personDetailPlace;
	private PersonDetailView personDetailView;

	public PersonDetailActivity(BasicPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.personDetailPlace = (PersonDetailPlace) place;
		this.person = personDetailPlace.getPerson();

	}

	@Override
	public void start(AcceptsOneWidget container, EventBus eventBus) {
		loadData(container);
	}

	void loadData(AcceptsOneWidget container) {
		if (person != null) {
			personDetailView = new PersonDetailView(this.person);
			init(personDetailView);
			container.setWidget(personDetailView);
			return;
		} else if (personDetailPlace.getToken().contains("?name=")) {
			String name = personDetailPlace.getToken().substring(19);
			GWT.log(name);
			PersonServiceAsync personServiceAsync = GWT.create(PersonService.class);
			personServiceAsync.getPersonByName(name, new AsyncCallback<Person>() {

				@Override
				public void onSuccess(Person result) {
					Window.alert(result.toString());
					personDetailView = new PersonDetailView(result);
					init(personDetailView);
					container.setWidget(personDetailView);
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("khong tim thay thong tin yeu cau");
				}
			});
		} else {
			personDetailView = new PersonDetailView();
			init(personDetailView);
			container.setWidget(personDetailView);
		}
	}

	public void init(PersonDetailView personDetailView) {
		personDetailView.getSaveButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clientFactory.getPlaceController().goTo(new HomePlace("home"));
			}
		});
	}

	@Override
	public String mayStop() {
		return null;
	}

	@Override
	public void onCancel() {

	}

	@Override
	public void onStop() {

	}

}
