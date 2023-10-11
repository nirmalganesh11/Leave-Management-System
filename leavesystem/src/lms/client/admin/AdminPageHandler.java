package lms.client.admin;


import gwt.material.design.client.ui.MaterialNavBar;
import lms.client.admin.pages.CompanyListPage;
import lms.client.admin.pages.CompanyPage;
import lms.client.admin.pages.DepartmentListPage;
import lms.client.admin.pages.DepartmentPage;
import lms.client.admin.pages.EmployeeListPage;
import lms.client.admin.pages.EmployeePage;
import lms.client.admin.pages.HolidayListPage;
import lms.client.admin.pages.HolidayPage;
import lms.client.admin.pages.LeaveTypeListPage;
import lms.client.admin.pages.LeaveTypePage;
import lms.client.clientservices.UserServiceClient;
import lms.client.clientservices.UserServiceClientAsync;
import lms.client.security.LoginView;
import lms.shared.User;
import gwt.material.design.client.ui.MaterialLink;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AdminPageHandler extends VerticalPanel {
	
	UserServiceClientAsync servClient = GWT.create(UserServiceClient.class);
	private User loggedInUser;
	 
	VerticalPanel fullbarPanel = new VerticalPanel();
    VerticalPanel navBarPanel = new VerticalPanel();
    MaterialNavBar navBar = new MaterialNavBar();
    HorizontalPanel changePanel =new HorizontalPanel();

    public void createAdminDashboard() {
    	
    	
        navBar.addStyleName("nav-bar"); 
        
       //MaterialLink usernameDisplay = new MaterialLink(loggedInUser.getUsername());
       
        MaterialLink companyLink = new MaterialLink("Company");
        MaterialLink departmentLink = new MaterialLink("Department");
        MaterialLink leavesLink = new MaterialLink("Leave Requests");
        MaterialLink leaveTypesLink = new MaterialLink("Leave Types");
        MaterialLink employeeLink = new MaterialLink("Employees");
        MaterialLink holidaysLink = new MaterialLink("Holidays");
        MaterialLink messageLink = new MaterialLink("Message");
        MaterialLink previousRequests = new MaterialLink("Previous Requests");
        MaterialLink logoutLink = new MaterialLink("Logout");
       

       
        navBar.add(companyLink);
       
        navBar.add(departmentLink);
        
        navBar.add(employeeLink);
       
        navBar.add(leavesLink);
        
        navBar.add(leaveTypesLink);
        
        navBar.add(holidaysLink);
       
      
        navBar.add(messageLink);
       
        navBar.add(previousRequests);

        navBar.add(logoutLink);
        
        navBarPanel.add(navBar);
        
        
        companyLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				CompanyPage cmp = new CompanyPage();
			    cmp.addStyleName("material-card");
			    cmp.getCompanyNameBox().addStyleName("material-textbox");
			    cmp.getCompanyDescBox().addStyleName("material-textbox");
			    cmp.getCreateButton().addStyleName("material-button");
			    
			    
			    CompanyListPage clpage = new CompanyListPage();
			    clpage.addStyleName("material-card-scrollpanel");
			    
			    
			    changePanel.clear();
			    changePanel.add(cmp);
			    changePanel.add(clpage);
			    
			    
			}
        });
        
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
        
        
        
        departmentLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				DepartmentPage dbp = new DepartmentPage();
				dbp.addStyleName("material-card");
				dbp.getDepartmentNameBox().addStyleName("material-textbox");
			    dbp.getDepartmentDescBox().addStyleName("material-textbox");
			    dbp.getCreateButton().addStyleName("material-button");
				
			    
			    DepartmentListPage dppage = new DepartmentListPage();
			    dppage.addStyleName("material-card-scrollpanel");
			    
			    
			    changePanel.clear();
			    changePanel.add(dbp);
			    changePanel.add(dppage);
			    
			}
        });
        
        employeeLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				EmployeePage ep = new EmployeePage();
				ep.addStyleName("material-card");
				ep.getUsernameBox().addStyleName("material-textbox");
			    ep.getPasswordBox().addStyleName("material-textbox");
			    ep.getEmployeeFirstNameBox().addStyleName("material-textbox");
			    ep.getEmployeeLastNameBox().addStyleName("material-textbox");
			    ep.getEmployeePositionBox().addStyleName("material-textbox");
			    ep.getEmployeeEmailBox().addStyleName("material-textbox");
			    ep.getCreateButton().addStyleName("material-button");
			    
			    
			    
			    EmployeeListPage eppage = new EmployeeListPage();
			    eppage.addStyleName("material-card-employee");
			    
			    changePanel.clear();
			    changePanel.add(ep);
			    changePanel.add(eppage);
			    
			}
        	
        	
        	
        });
        
        holidaysLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				HolidayPage hp = new HolidayPage();
				hp.addStyleName("material-card");
				hp.getHolidayNameBox().addStyleName("material-textbox");
			    hp.getHolidayDateBox().addStyleName("material-textbox");
			    hp.getCreateButton().addStyleName("material-button");
				
			    HolidayListPage hpage = new HolidayListPage();
			    hpage.addStyleName("material-card-holiday");
			    
			    changePanel.clear();
			    changePanel.add(hp);
			    changePanel.add(hpage);
			
			}
        	
        });
        
        leaveTypesLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				LeaveTypePage ltp = new LeaveTypePage();
				ltp.addStyleName("material-card");
				ltp.getLeaveTypeNameBox().addStyleName("material-textbox");
			    ltp.getLeaveTypeDescBox().addStyleName("material-textbox");
			    ltp.getDaysBox().addStyleName("material-textbox");
			    ltp.getCreateButton().addStyleName("material-button");
				
			    
			    LeaveTypeListPage ltlistpage = new LeaveTypeListPage();
			    ltlistpage.addStyleName("material-card-leavetype");
			    
			    
				changePanel.clear();
				changePanel.add(ltp);
				changePanel.add(ltlistpage);
				
			}
			
        });
        
        
        fullbarPanel.setSpacing(10);
        
        changePanel.setSpacing(75);
        fullbarPanel.add(navBarPanel);
        fullbarPanel.add(changePanel);
        RootPanel.get().add(fullbarPanel);
        
      
        // Set the responsive sideNav menu for mobile
        // navbar.setNavBrand(brand);
        //navbar.setSidenav(GWT.create(SideNav.class));
    }
}
