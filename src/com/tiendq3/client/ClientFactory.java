package com.tiendq3.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.tiendq3.client.view.HomeView;
import com.tiendq3.client.view.PersonDetailView;

public interface ClientFactory {
	EventBus getEventBus();

	PlaceController getPlaceController();

	HomeView getHomeView();

	PersonDetailView getPersonDetailView();
}
