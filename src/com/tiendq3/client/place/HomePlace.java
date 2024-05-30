package com.tiendq3.client.place;

public class HomePlace extends BasicPlace {

	public HomePlace() {
		this.token = PlaceConfig.HOME_PLACE;
	}

	public HomePlace(String token) {
		super(token);
	}

}
