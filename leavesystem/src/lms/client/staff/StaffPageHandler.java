package lms.client.staff;

import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialNavBar;
import gwt.material.design.client.ui.MaterialNavBrand;
import gwt.material.design.client.ui.MaterialNavSection;
import lms.client.admin.pages.CompanyListPage;
import lms.client.admin.pages.CompanyPage;
import lms.client.admin.pages.DepartmentListPage;
import lms.client.admin.pages.DepartmentPage;
import lms.client.admin.pages.EmployeeListPage;
import lms.client.admin.pages.EmployeePage;
import lms.client.admin.pages.LeaveRequestListPage;
import lms.client.clientservices.UserServiceClient;
import lms.client.clientservices.UserServiceClientAsync;
import lms.client.security.LoginView;
import lms.client.staff.pages.LeaveRequestListPageStaff;
import lms.client.staff.pages.LeaveRequestPage;
import gwt.material.design.client.ui.MaterialLink;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StaffPageHandler extends VerticalPanel {
	
	UserServiceClientAsync servClient = GWT.create(UserServiceClient.class);
	VerticalPanel fullbarPanel = new VerticalPanel();
    VerticalPanel navBarPanel = new VerticalPanel();
    MaterialNavBar navBar = new MaterialNavBar();
    HorizontalPanel changePanel =new HorizontalPanel();

    public void createStaffDashboard() {

        navBar.addStyleName("nav-bar"); 
        
       
//        MaterialLink homeLink = new MaterialLink("Home");
        MaterialLink applyLeaveLink = new MaterialLink("Apply Leave");
        MaterialLink messageLink = new MaterialLink("Messages");
        MaterialLink previousRequests = new MaterialLink("Previous Requests");
        MaterialLink logoutLink = new MaterialLink("Logout");
        
        //navBar.add(homeLink);
       
        navBar.add(applyLeaveLink);

        navBar.add(messageLink);
       
        navBar.add(previousRequests);
        
        navBar.add(logoutLink);
        
        navBarPanel.add(navBar);
        
        logoutLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				servClient.logoutUser(new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					@Override
					public void onSuccess(String result) {
						
						RootPanel.get().clear();
						History.replaceItem("");
						RootPanel.get().addStyleName("login-page");
						
						LoginView jslogin = new LoginView();
					    jslogin.addStyleName("material-card-login");
					    jslogin.getUsernameTextBox().addStyleName("material-textbox");
					    jslogin.getPasswordTextBox().addStyleName("material-textbox");
					    jslogin.getLoginButton().addStyleName("material-button");
					    
						RootPanel.get().add(jslogin);
					}

				});
				
				
			}
        	
        	
        	
        });
        
        applyLeaveLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				LeaveRequestPage cmp = new LeaveRequestPage();
			    cmp.addStyleName("material-card");
			    cmp.getUserIdBox().addStyleName("materil-textbox");
			    cmp.getDescriptionBox().addStyleName("material-textbox");
			    cmp.getCreateButton().addStyleName("material-button");
			    
			    changePanel.clear();
			    changePanel.add(cmp);
			    
				
			}
        });
        
        previousRequests.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
//				LeaveRequestListPageStaff lrpage = new LeaveRequestListPageStaff();
//			    lrpage.addStyleName("material-card-request");
//			    
//			    changePanel.clear();
//				changePanel.add(lrpage);
			}

        });
        
        
        fullbarPanel.setSpacing(10);
        
        changePanel.setSpacing(75);
        fullbarPanel.add(navBarPanel);
        fullbarPanel.add(changePanel);
        RootPanel.get().add(fullbarPanel);
        

    }
}