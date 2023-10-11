package lms.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import lms.client.admin.AdminEntryPoint;
import lms.client.clientservices.UserServiceClient;
import lms.client.clientservices.UserServiceClientAsync;
import lms.client.security.LoginView;
import lms.client.staff.StaffEntryPoint;
import lms.shared.User;



public class LeaveManagementSystem implements EntryPoint{
	
	UserServiceClientAsync servClient = GWT.create(UserServiceClient.class);
	
	public void onModuleLoad() {
	
		//upon reload i dont want unauthenticated users to check and see the admin page 
		//, so i call the server side, it  handles that. if its anonymous user the server gives the anonyomous user
//		servClient.getAuthenticatedUser(new AsyncCallback<User>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert(caught.toString());
//			}
//
//			@Override
//			public void onSuccess(User result) {
//				String token = History.getToken();
//				if("admin".equals(token)) {
//					RootPanel.get().clear();
//					AdminEntryPoint adminep = new AdminEntryPoint();
//					adminep.onModuleLoad();
//				}
//				else if("staff".equals(token)) {
//					RootPanel.get().clear();
//					StaffEntryPoint staffep = new StaffEntryPoint();
//					staffep.onModuleLoad();
//				}
//				else {
//					showLoginView();
//				}
//			}
//    	});
		servClient.isAuthenticated(new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(Boolean result) {
				if(result) {
					String token =History.getToken();
					if(token.equals("admin")) {
						RootPanel.get().clear();
						AdminEntryPoint adminep = new AdminEntryPoint();
						adminep.onModuleLoad();
					}
					if("staff".equals(token)) {
						RootPanel.get().clear();
						StaffEntryPoint staffep = new StaffEntryPoint();
						staffep.onModuleLoad();
					}
					
				}
				else {
					showLoginView();
				}
			}
		
		});
	
		
		
		//showLoginView();
		
	}
	public void showLoginView() {
		
		
		
		RootPanel.get().addStyleName("login-page");
		
		

		
		
		LoginView jslogin = new LoginView();
	    jslogin.addStyleName("material-card-login");
	    jslogin.getUsernameTextBox().addStyleName("material-textbox");
	    jslogin.getPasswordTextBox().addStyleName("material-textbox");
	    jslogin.getLoginButton().addStyleName("material-button");
	    

		RootPanel.get().add(jslogin);
		
	}




}
