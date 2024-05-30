package com.tiendq3.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.tiendq3.client.place.BasicPlace;
import com.tiendq3.client.place.HomePlace;
import com.tiendq3.client.place.PersonDetailPlace;
import com.tiendq3.client.place.PlaceConfig;

public class Mvp implements EntryPoint {

	private SimplePanel appWidget = new SimplePanel();

	@Override
	public void onModuleLoad() {
		ClientFactory clientFactory = GWT.create(ClientFactoryImpl.class);
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();

		// cấu hình bộ xử lý lịch sử PlaceHistoryHandler
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(new AppPlaceHistoryMapper() {

			@Override
			public Place getPlace(String token) {
				// Dựa vào token, trả về một Place cụ thể
				if (token.equals("") || token == null || token.equals(PlaceConfig.HOME_PLACE)) {
					return new HomePlace();
				} else if (token.contains(PlaceConfig.PERSON_DEATAIL_PLACE)) {
					return new PersonDetailPlace(token);
				}
				return null;
			}

			@Override
			public String getToken(Place place) {
				if (place instanceof HomePlace) {
					return ((HomePlace) place).getToken();
				} else if (place instanceof PersonDetailPlace)
					return ((PersonDetailPlace) place).getToken();
				return "";
			}
		});

		// đăng kí với bộ xử lý lịch sử với place đầu tiền xuất hiện
		BasicPlace homePlace = new HomePlace();
		historyHandler.register(placeController, eventBus, homePlace);

		// sau khi đã xử lý Place
		// giờ muốn biết place nào sẽ tương ứng với activity nào -> activityMapper ->
		// activityManager
		ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		
		activityManager.setDisplay(appWidget);
		RootPanel.get("container").add(appWidget);

		historyHandler.handleCurrentHistory();
	}

}