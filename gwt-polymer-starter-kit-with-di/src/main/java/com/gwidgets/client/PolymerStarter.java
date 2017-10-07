package com.gwidgets.client;

import java.util.Arrays;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
import com.gwidgets.places.HomePlace;
import com.gwidgets.ui.MainPage;
import com.vaadin.polymer.Polymer;
import com.vaadin.polymer.elemental.Function;


public class PolymerStarter implements EntryPoint {
	private HomePlace homePlace = new HomePlace("home");
	private SimplePanel appWidget = new SimplePanel();
	
  public void onModuleLoad() {
	  
	  //don't worry of the IDE complains about DaggerAppComponent, it is generated by the dagger comppiler
	  AppComponent component = DaggerAppComponent.builder()
				.mVPModule(new MVPModule())
				.build();

			MyActivityMapper mainMapper = component.mainActivityMapper();
			ActivityManager mainManager = new ActivityManager(mainMapper, component.eventBus());
			mainManager.setDisplay(appWidget);

			RootPanel.get().add(appWidget);
			component.getMainPage().initializeEvents(component.getPlaceController());

			component.getHistoryHandler().handleCurrentHistory();		        
  }
}