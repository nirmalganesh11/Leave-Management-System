package lms.client.staff;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import lms.client.admin.AdminPageHandler;
import lms.client.clientservices.UserServiceClient;
import lms.client.clientservices.UserServiceClientAsync;

public class StaffEntryPoint implements EntryPoint{
	
	UserServiceClientAsync servclient = GWT.create(UserServiceClient.class);
	@Override
	public void onModuleLoad() {
		StaffPageHandler pg = new StaffPageHandler();
		pg.createStaffDashboard();
	}

}
