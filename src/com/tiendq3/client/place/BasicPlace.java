package com.tiendq3.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public /* abstract */ class BasicPlace extends Place {
	protected String token = "";

	public BasicPlace() {
	}

	public BasicPlace(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public static class Tokenizer implements PlaceTokenizer<BasicPlace> {

		@Override
		public BasicPlace getPlace(String token) {
			return new BasicPlace(token);
		}

		@Override
		public String getToken(BasicPlace place) {
			return place.getToken();
		}

	}
}
