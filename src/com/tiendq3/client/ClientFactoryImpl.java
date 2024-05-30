package com.tiendq3.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.tiendq3.client.view.HomeView;
import com.tiendq3.client.view.PersonDetailView;

public class ClientFactoryImpl implements ClientFactory {

	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(eventBus);
	private HomeView homeView = null;
	public ClientFactoryImpl() {

	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return this.placeController;
	}

	@Override
	public HomeView getHomeView() {
		if(homeView == null) {
			return new HomeView();
		}
		return homeView;
	}

	@Override
	public PersonDetailView getPersonDetailView() {
		return new PersonDetailView();
	}

}
