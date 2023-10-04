package lms.client.admin;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import lms.client.admin.pages.CompanyPage;
import lms.client.clientservices.UserServiceClient;
import lms.client.clientservices.UserServiceClientAsync;

public class AdminEntryPoint implements EntryPoint {

	UserServiceClientAsync servclient = GWT.create(UserServiceClient.class);
	
	@Override
	public void onModuleLoad() {
		
		//AdminNavbar anbar = new AdminNavbar();
		
		AdminPageHandler pg = new AdminPageHandler();
		pg.createAdminDashboard();
		
		
		
		
		
		
//		CompanyPage jslogin = new CompanyPage();
//		 jslogin.addStyleName("material-card");
//		    jslogin.getUsernameTextBox().addStyleName("material-textbox");
//		    jslogin.getPasswordTextBox().addStyleName("material-textbox");
//		    jslogin.getLoginButton().addStyleName("material-button");
//	    RootPanel.get().add(jslogin);
		//RootPanel.get().add(pg);
	}

}
