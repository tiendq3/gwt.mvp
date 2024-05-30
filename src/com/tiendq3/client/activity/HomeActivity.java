package com.tiendq3.client.activity;

import java.util.ArrayList;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.tiendq3.client.ClientFactory;
import com.tiendq3.client.PersonService;
import com.tiendq3.client.PersonServiceAsync;
import com.tiendq3.client.place.BasicPlace;
import com.tiendq3.client.place.PersonDetailPlace;
import com.tiendq3.client.place.PlaceConfig;
import com.tiendq3.client.view.HomeView;
import com.tiendq3.shared.Person;

public class HomeActivity implements Activity, AppPresenter {

	private ClientFactory clientFactory;

	private HomeView homeView;

	private ArrayList<Person> persons = new ArrayList<>();

	private PersonServiceAsync personServiceAsync = GWT.create(PersonService.class);

	public HomeActivity(BasicPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.homeView = clientFactory.getHomeView();
	}

	@Override
	public void start(AcceptsOneWidget container, EventBus eventBus) {
		// được gọi nhiều lần?
		loadData();
		init();
		container.setWidget(homeView);

	}

	private void loadData() {
		personServiceAsync.getAllPerson(new AsyncCallback<ArrayList<Person>>() {

			@Override
			public void onSuccess(ArrayList<Person> result) {
				persons = result;
				homeView.showPersons(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Lỗi load-data");
			}
		});

	}

	void init() {
		homeView.getSaveButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Person newPerson = homeView.validatePerson(persons);
				if (newPerson != null)
					personServiceAsync.insertPerson(newPerson, new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							Window.alert("them thanh cong");
							homeView.addPerson(newPerson, persons);
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("loi server");
						}
					});

			}
		});

		homeView.getPersonTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				handleClickRow(event, homeView.getPersonTable());
			}
		});
	}

	void handleClickRow(ClickEvent event, FlexTable personTable) {
		Cell cellClick = personTable.getCellForEvent(event);
		Person personSelect = persons.get(cellClick.getRowIndex() - 1);
//		Window.alert(personSelect.toString());
		
//		clientFactory.getPlaceController().goTo(new PersonDetailPlace(personSelect));
		clientFactory.getPlaceController().goTo(new PersonDetailPlace(
				PlaceConfig.PERSON_DEATAIL_PLACE + "?name=" + personSelect.getName(), personSelect));
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

	@Override
	public void go(EventBus eventBus) {

	}

}
