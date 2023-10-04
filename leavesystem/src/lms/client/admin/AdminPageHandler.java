package lms.client.admin;

import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialNavBar;
import gwt.material.design.client.ui.MaterialNavBrand;
import gwt.material.design.client.ui.MaterialNavSection;
import lms.client.admin.pages.CompanyListPage;
import lms.client.admin.pages.CompanyPage;
import lms.client.security.LoginView;
import gwt.material.design.client.ui.MaterialLink;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AdminPageHandler extends VerticalPanel {
	
	VerticalPanel fullbarPanel = new VerticalPanel();
    VerticalPanel navBarPanel = new VerticalPanel();
    MaterialNavBar navBar = new MaterialNavBar();
    HorizontalPanel changePanel =new HorizontalPanel();

    public void createAdminDashboard() {

    	
        navBar.addStyleName("nav-bar"); 
        
       
        MaterialLink homeLink = new MaterialLink("Home");
        MaterialLink companyLink = new MaterialLink("Company");
        MaterialLink departmentLink = new MaterialLink("Department");
        MaterialLink leavesLink = new MaterialLink("Leave Requests");
        MaterialLink employeeLink = new MaterialLink("Employees");
        MaterialLink messageLink = new MaterialLink("Message");
        MaterialLink previousRequests = new MaterialLink("Previous Requests");
        MaterialLink logoutLink = new MaterialLink("Logout");
        
        navBar.add(homeLink);
       
        navBar.add(companyLink);
       
        navBar.add(departmentLink);
       
        navBar.add(leavesLink);
       
        navBar.add(employeeLink);
      
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
			    clpage.addStyleName("material-card");
			    
			    
			    changePanel.clear();
			    changePanel.add(cmp);
			    changePanel.add(clpage);
			    
			    
			}
        });
        
        homeLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				History.replaceItem("");
				RootPanel.get().addStyleName("login-page");
				
				LoginView jslogin = new LoginView();
			    jslogin.addStyleName("material-card");
			    jslogin.getUsernameTextBox().addStyleName("material-textbox");
			    jslogin.getPasswordTextBox().addStyleName("material-textbox");
			    jslogin.getLoginButton().addStyleName("material-button");
			    
				RootPanel.get().add(jslogin);
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
