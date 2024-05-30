package com.tiendq3.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.tiendq3.client.activity.HomeActivity;
import com.tiendq3.client.activity.PersonDetailActivity;
import com.tiendq3.client.place.HomePlace;
import com.tiendq3.client.place.PersonDetailPlace;

public class AppActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;

	/**
	 * AppActivityMapper associates each Place with its corresponding
	 * {@link Activity}
	 * 
	 * @param clientFactory Factory to be passed to activities
	 */
	public AppActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	/**
	 * Map each Place to its corresponding Activity. This would be a great use for
	 * GIN.
	 */
	@Override
	public Activity getActivity(Place place) {
		// This is begging for GIN
		if (place instanceof HomePlace)
			return new HomeActivity((HomePlace) place, clientFactory);
		else if (place instanceof PersonDetailPlace)
			return new PersonDetailActivity((PersonDetailPlace) place, clientFactory);

		return null;
	}

}
