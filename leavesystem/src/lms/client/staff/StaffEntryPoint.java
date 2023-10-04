package lms.client.staff;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import lms.client.clientservices.UserServiceClient;
import lms.client.clientservices.UserServiceClientAsync;

public class StaffEntryPoint implements EntryPoint{
	
	UserServiceClientAsync servclient = GWT.create(UserServiceClient.class);
	@Override
	public void onModuleLoad() {
		RootPanel.get().clear();
		Button abbutton = new Button("staff entryPoint");
		Button test = new Button("Test");
		test.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				servclient.getLoggedInUser(new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					@Override
					public void onSuccess(String result) {
						Window.alert(result);
					}

				});
			}
			
			
		});
		RootPanel.get().add(abbutton);
		RootPanel.get().add(test);
	}

}
