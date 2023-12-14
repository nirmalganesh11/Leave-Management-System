package lms.client.ui.admin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialNavBar;
import lms.client.asyncservices.UserServiceClient;
import lms.client.asyncservices.UserServiceClientAsync;
import lms.client.ui.admin.pages.CompanyPage;
import lms.client.ui.admin.pages.DepartmentPage;
import lms.client.ui.admin.pages.EmployeeActionsPage;
import lms.client.ui.admin.pages.EmployeePage;
import lms.client.ui.admin.pages.HolidayPage;
import lms.client.ui.admin.pages.LeaveRequestActionsPage;
import lms.client.ui.admin.pages.LeaveTypePage;
import lms.client.ui.admin.pages.MessageActionsPage;
import lms.client.ui.admin.pages.MessageSendPage;
import lms.client.ui.admin.pages.listpages.CompanyListPage;
import lms.client.ui.admin.pages.listpages.DepartmentListPage;
import lms.client.ui.admin.pages.listpages.EmployeeListPage;
import lms.client.ui.admin.pages.listpages.HistoryRequests;
import lms.client.ui.admin.pages.listpages.HolidayListPage;
import lms.client.ui.admin.pages.listpages.LeaveRequestListPage;
import lms.client.ui.admin.pages.listpages.LeaveTypeListPage;
import lms.client.ui.admin.pages.listpages.OnLeaveListPage;
//import lms.shared.User;
import lms.client.ui.security.LoginView;
import lms.client.ui.staff.pages.listpages.LeaveRequestListPageStaff;

public class AdminPageHandler extends VerticalPanel {
	
	UserServiceClientAsync servClient = GWT.create(UserServiceClient.class);
	
	//private User loggedInUser;
	 
	VerticalPanel fullbarPanel = new VerticalPanel();
    VerticalPanel navBarPanel = new VerticalPanel();
    MaterialNavBar navBar = new MaterialNavBar();
    HorizontalPanel changePanel =new HorizontalPanel();
    VerticalPanel vertChangePanel = new VerticalPanel();
    
    
    
    EmployeePage ep = new EmployeePage();
    EmployeeActionsPage eapage = new EmployeeActionsPage();
    EmployeeListPage eppage = new EmployeeListPage();
    int panelCounter =0;
    MessageActionsPage mapage = new MessageActionsPage();
    
    
    LeaveRequestActionsPage lraPage = new LeaveRequestActionsPage();
    
    public void createAdminDashboard() {
    	
    	
        navBar.addStyleName("nav-bar");
        
       //MaterialLink usernameDisplay = new MaterialLink(loggedInUser.getUsername());
       
        MaterialLink onLeaveLink = new MaterialLink("On Leave");
        MaterialLink departmentLink = new MaterialLink("Department");
        MaterialLink leavesLink = new MaterialLink("Leave Requests");
        MaterialLink leaveTypesLink = new MaterialLink("Leave Types");
        MaterialLink employeeLink = new MaterialLink("Employees");
        MaterialLink holidaysLink = new MaterialLink("Holidays");
        MaterialLink messageLink = new MaterialLink("Message");
        MaterialLink previousRequests = new MaterialLink("Previous Requests");
        MaterialLink logoutLink = new MaterialLink("Logout");
       
        onLeaveLink.addStyleName("material-link-align");
        departmentLink.addStyleName("material-link-align");
        leavesLink.addStyleName("material-link-align");
        leaveTypesLink.addStyleName("material-link-align");
        employeeLink.addStyleName("material-link-align");
        holidaysLink.addStyleName("material-link-align");
        messageLink.addStyleName("material-link-align");
        previousRequests.addStyleName("material-link-align");
        logoutLink.addStyleName("material-link-align");
//        companyLink.addStyleName("material-link-align");
        // Set icons for each link
        onLeaveLink.setIconType(IconType.TIME_TO_LEAVE);
        departmentLink.setIconType(IconType.BUILD);
        leavesLink.setIconType(IconType.DATE_RANGE);
        leaveTypesLink.setIconType(IconType.STYLE);
        employeeLink.setIconType(IconType.WORK);
        holidaysLink.setIconType(IconType.EVENT);
        messageLink.setIconType(IconType.MESSAGE);
        previousRequests.setIconType(IconType.HISTORY);
        logoutLink.setIconType(IconType.EXIT_TO_APP);
        
        
        
        
    	ep.addStyleName("material-card-addemployee");
		ep.getUsernameBox().addStyleName("material-textbox");
	    ep.getPasswordBox().addStyleName("material-textbox");
	    ep.getEmployeeFirstNameBox().addStyleName("material-textbox");
	    ep.getEmployeeLastNameBox().addStyleName("material-textbox");
	    ep.getEmployeePositionBox().addStyleName("material-textbox");
	    ep.getEmployeeEmailBox().addStyleName("material-textbox");
	    ep.getCreateButton().addStyleName("material-button");

	    eapage.addStyleName("material-card-actions");
	    lraPage.addStyleName("material-card-actions");
	    mapage.addStyleName("material-card-actions");

	    eppage.addStyleName("material-card-employee");
	    
	    
	    navBar.add(leavesLink);
	    
	    navBar.add(previousRequests);
        
        navBar.add(onLeaveLink);
        
        navBar.add(employeeLink);

        navBar.add(messageLink);
        
        
        navBar.add(leaveTypesLink);
        
        navBar.add(holidaysLink);
       
        navBar.add(departmentLink);
        
       
        

        navBar.add(logoutLink);
        
        navBarPanel.add(navBar);
        
      
        
        
        
      
        onLeaveLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				changePanel.clear();
				OnLeaveListPage ol = new OnLeaveListPage();
				
			    ol.addStyleName("material-card-onleave");
				changePanel.add(ol);
			
			    
			   
			    
			    
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
				
				HorizontalPanel hpanel = new HorizontalPanel();
				HorizontalPanel hpanel2 = new HorizontalPanel();
				
				CompanyPage cmp = new CompanyPage();
			    cmp.addStyleName("material-card");
			    cmp.getCompanyNameBox().addStyleName("material-textbox");
			    cmp.getCompanyDescBox().addStyleName("material-textbox");
			    cmp.getCreateButton().addStyleName("material-button");
			    
			    
			    CompanyListPage clpage = new CompanyListPage();
			    clpage.addStyleName("material-card-scrollpanel");
			    
			    hpanel.add(cmp);
			    hpanel.add(clpage);
			    
			    
				DepartmentPage dbp = new DepartmentPage();
				dbp.addStyleName("material-card");
				dbp.getDepartmentNameBox().addStyleName("material-textbox");
			    dbp.getDepartmentDescBox().addStyleName("material-textbox");
			    dbp.getCreateButton().addStyleName("material-button");
				
			    
			    DepartmentListPage dppage = new DepartmentListPage();
			    dppage.addStyleName("material-card-scrollpanel");
			    
			    hpanel2.add(dbp);
			    hpanel2.add(dppage);
			    
			    VerticalPanel vp = new VerticalPanel();
			    vp.add(cmp);
			    vp.add(dbp);
			    
			    
			   hpanel.add(clpage);
			   hpanel.add(dppage);
			    
			    hpanel2.add(vp);
			    hpanel2.add(hpanel);
			    
			    
			    changePanel.clear();
			    changePanel.add(hpanel2);
//			    changePanel.add(cmp);
//			    changePanel.add(clpage);
//			    
//			    changePanel.add(dbp);
//			    changePanel.add(dppage);
			    
			}
        });
        
        employeeLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

			
			    
			    changePanel.clear();
			    changePanel.setSpacing(20);
			    changePanel.add(eapage);
			    eppage = new EmployeeListPage();
			    eppage.addStyleName("material-card-employee");
			    changePanel.add(eppage);
			    //changePanel.add(ep);
			    
			    eapage.getAddLink().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						changePanel.remove(1);
						changePanel.add(ep);
					}

			    });

			    ep.getCreateButton().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						//Window.alert("working");
//					    changePanel.clear();
//					    changePanel.setSpacing(20);
//					    changePanel.add(eapage);
//					    changePanel.add(eppage);
					    
//					    changePanel.clear();
					    changePanel.remove(1);
					    EmployeeListPage eppageInternal = new EmployeeListPage();
					    eppageInternal.addStyleName("material-card-employee");
					    changePanel.add(eppageInternal);
					    
					    
					}
			    });
			    
			    
			    eapage.getBackToTableLink().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						changePanel.clear();
					    changePanel.setSpacing(20);
					    changePanel.add(eapage);
					    changePanel.add(eppage);
					}
			    	
			    });
//			    vertChangePanel.clear();
//			    vertChangePanel.add(eppage);
//			    vertChangePanel.add(ep);
			    
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
        
        leavesLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				
				
				LeaveRequestListPage lrpage = new LeaveRequestListPage();
			    lrpage.addStyleName("material-card-request");
			    
			    changePanel.clear();
			    changePanel.setSpacing(10);
			    changePanel.add(lraPage);
				changePanel.add(lrpage);
			    

			    
			}
        	
        	
        });
        
        messageLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				changePanel.clear();
				
				changePanel.setSpacing(20);
				changePanel.add(mapage);
				 
				mapage.getSendLink().addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
	
							if(changePanel.getWidgetCount()<=1) {
								MessageSendPage sendPage = new MessageSendPage();
								sendPage.addStyleName("material-card-leavetype");
								changePanel.add(sendPage);
							}
							
					}
					
				});
				
				 
				
			}
        	
        	
        });
        
        previousRequests.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				HistoryRequests lrpage = new HistoryRequests();
			    lrpage.addStyleName("material-card-request");
			    
			    changePanel.clear();
				changePanel.add(lrpage);
			}
        	
        });
        
        fullbarPanel.setSpacing(10);
        
        
        
        

		LeaveRequestListPage lrpage = new LeaveRequestListPage();
	    lrpage.addStyleName("material-card-request");
	    
	    changePanel.clear();
	    changePanel.setSpacing(10);
	    changePanel.add(lraPage);
		changePanel.add(lrpage);
		
//        changePanel.setSpacing(75);
        fullbarPanel.add(navBarPanel);
//        LeaveRequestListPage lrpage = new LeaveRequestListPage();
//	    lrpage.addStyleName("material-card-request");
//	    changePanel.add(lrpage);
        //fullbarPanel.add(vertChangePanel);
        fullbarPanel.add(changePanel);
        RootPanel.get().add(fullbarPanel);
        
      
        // Set the responsive sideNav menu for mobile
        // navbar.setNavBrand(brand);
        //navbar.setSidenav(GWT.create(SideNav.class));
    }
}
