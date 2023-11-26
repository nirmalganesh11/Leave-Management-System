package lms.client.ui.security;


import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import lms.client.asyncservices.EmployeeServiceClient;
import lms.client.asyncservices.EmployeeServiceClientAsync;
import lms.client.asyncservices.UserServiceClient;
import lms.client.asyncservices.UserServiceClientAsync;
import lms.client.ui.admin.AdminEntryPoint;
import lms.client.ui.staff.StaffEntryPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import lms.shared.*;
import lms.shared.heirarchy.Company;
import lms.shared.heirarchy.Department;
import lms.shared.security.Permission;
import lms.shared.security.Role;



public class LoginView extends Composite implements ValueChangeHandler<String> {
	
	UserServiceClientAsync servclient = GWT.create(UserServiceClient.class);
    EmployeeServiceClientAsync empServ = GWT.create(EmployeeServiceClient.class);
	private static final Logger logger = Logger.getLogger(LoginView.class.getName());
	
	
    private MaterialTextBox usernameTextBox;
    private MaterialTextBox passwordTextBox;
    private MaterialButton loginButton;  
    
    
    private String username;
    private String password;
    private String encoded;
    
    private User loggedInUser;
    
    
    public LoginView() {
    	
        MaterialCard card = new MaterialCard();
        
        HTML heading = new HTML("<center><h3>System Login</h3><center>");
        
        MaterialLabel usernameLabel = new MaterialLabel("Username:");
        // Create widgets for the login form
        usernameTextBox = new MaterialTextBox();
        
        MaterialLabel passwordLabel = new MaterialLabel("Password:");
        passwordTextBox = new MaterialTextBox();
        
        MaterialLink forgotPassword = new MaterialLink("forgot your password?");
        
        
        History.addValueChangeHandler(this);
		onValueChange(null);
		

	

        loginButton = new MaterialButton(ButtonType.RAISED, "Login", new MaterialIcon(IconType.SEND));
        

        loginButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				
				    
				
				username = usernameTextBox.getText();
				password = passwordTextBox.getText();
				String credentials = username + ":" + password;
				encoded = encodeBase64(credentials);
				
				makeRequestToLogin(encoded);
											
			}
        	
        });
        

        forgotPassword.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				
				ResetPasswordPage rsp = new ResetPasswordPage();
				rsp.addStyleName("material-card-login");
		        rsp.getUserIdTextBox().addStyleName("material-textbox");
		        rsp.getEmailTextBox().addStyleName("material-textbox");
		        rsp.getResetPasswordButton().addStyleName("material-button");
				RootPanel.get().add(rsp);
			}
        });
        
        
        card.add(heading);
        card.add(usernameLabel);
        card.add(usernameTextBox);
        card.add(passwordLabel);
        card.add(passwordTextBox);
        card.add(forgotPassword);
        card.add(loginButton);


        initWidget(card);
    }

    public MaterialTextBox getUsernameTextBox() {
		return usernameTextBox;
	}

	public void setUsernameTextBox(MaterialTextBox usernameTextBox) {
		this.usernameTextBox = usernameTextBox;
	}

	public MaterialTextBox getPasswordTextBox() {
		return passwordTextBox;
	}

	public void setPasswordTextBox(MaterialTextBox passwordTextBox) {
		this.passwordTextBox = passwordTextBox;
	}

	public void setLoginButton(MaterialButton loginButton) {
		this.loginButton = loginButton;
	}

	// You can provide getters for the form fields to access their values.
    public String getUsername() {
        return usernameTextBox.getText();
    }

    public String getPassword() {
        return passwordTextBox.getText();
    }

    public MaterialButton getLoginButton() {
        return loginButton;
    }
    
    
    
    public void makeRequestToLogin(String encoded) {
 	   
        String url = "/leavemanagementsystem/auth";
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url);
        requestBuilder.setHeader("Authorization", "Basic " + encoded);

        try {
     	   
            requestBuilder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
             	   
                    if (response.getStatusCode() == 200) {
                        String responseData = response.getText();
                        logger.info("Initialized MyGWTClass");
                        checkAuthenticationAndGoToNextPage();
                        
                    } else {
                    	logger.info("HTTP Request Failed with Status Code: " + response.getStatusCode());
                    }
                    
                }

                @Override
                public void onError(Request request, Throwable exception) {
                	logger.info("Request Error: " + exception.getMessage());
                	//logger.e
                }
            });
        } catch (RequestException e) {
        	logger.info("Request Exception: " + e.getMessage());
        }
 	     
    }
    
    public void checkAuthenticationAndGoToNextPage() {

    	
    	servclient.getAuthenticatedUser(new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				logger.severe("This is a severe message.");
			}

			@Override
			public void onSuccess(User result) {
				
				loggedInUser = result;
				if(result.getRole().getRoleName().equals("ROLE_ADMIN")) {
					History.newItem("admin");
				}
				else if(result.getRole().getRoleName().equals("ROLE_STAFF")) {
					History.newItem("staff");
				}
			}

    	});

    }
   
    private native String encodeBase64(String input) /*-{
    return btoa(input);
 	}-*/;
    
    
    @Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = History.getToken();
		if("admin".equals(token)) {
			RootPanel.get().clear();
			AdminEntryPoint adminep = new AdminEntryPoint();
			adminep.onModuleLoad();
		}
		else if("staff".equals(token)) {
			RootPanel.get().clear();
			StaffEntryPoint staffep = new StaffEntryPoint();
			staffep.onModuleLoad();
		}
		
	}
    
    public void saveEmployee() {
    	List<Permission> permList = new ArrayList<Permission>();
    	Permission p1 = new Permission("adminPermission");
    	permList.add(p1);
    	
    	Role newRole = new Role("ROLE_STAFF",permList);
    	
    	Company newCompany = new Company("second company","second company description");
    	
    	Department dp = new Department("second department","second department description",newCompany);
    	
    	Employee emp = new Employee();
    	
    	emp.setUsername("staff");
    	emp.setPassword("staff");
    	emp.setRole(newRole);
    	emp.setFirstName("staff first name");
    	emp.setCompany(newCompany);
    	emp.setDepartment(dp);
    	emp.setPosition("staff");
    	
    	empServ.saveEmployee(emp, new AsyncCallback<String>() {

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

}
