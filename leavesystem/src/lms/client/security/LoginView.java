package lms.client.security;


import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import lms.client.admin.AdminEntryPoint;
import lms.client.clientservices.UserServiceClient;
import lms.client.clientservices.UserServiceClientAsync;
import lms.client.staff.StaffEntryPoint;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.Filter.Result;

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
	 
	
    private MaterialTextBox usernameTextBox;
    private MaterialTextBox passwordTextBox;
    private MaterialButton loginButton;
    
    private MaterialButton userSeeButton;
    private MaterialButton saveUserButton;
    private MaterialButton getRoleButton;
    private MaterialButton getUserButton;
    
 
    
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
		
		
		
	        
    
        
        loginButton = new MaterialButton(ButtonType.RAISED, "Submit", new MaterialIcon(IconType.SEND));
        
        userSeeButton = new MaterialButton(ButtonType.RAISED, "testlogin", new MaterialIcon(IconType.SEND));
       saveUserButton = new MaterialButton(ButtonType.RAISED, "testusersave", new MaterialIcon(IconType.SEND));
        getRoleButton = new MaterialButton(ButtonType.RAISED, "getRole", new MaterialIcon(IconType.SEND));
        
       getUserButton = new MaterialButton(ButtonType.RAISED, "getUser", new MaterialIcon(IconType.SEND));

        loginButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				username = usernameTextBox.getText();
				password = passwordTextBox.getText();
				String credentials = username + ":" + password;
				encoded = encodeBase64(credentials);
				//Window.alert(encoded);
				makeRequestToLogin(encoded);
											
			}
        	
        });
        
        userSeeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				servclient.getLoggedInUser(new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("failure near loggged in user------"+caught.toString());
					}

					@Override
					public void onSuccess(String result) {
						Window.alert(result);
					}
					
					
				});
				
				
			}
        });
        
        saveUserButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				List<Permission> permlist = new ArrayList<Permission>();
				permlist.add(new Permission("staffpermission1","staff Permission"));
				permlist.add(new Permission("staffpermission2","thisstaffpermi"));
				permlist.add(new Permission("stafffboy","ahassstaffpermissionn"));
				
				Role hrrole = new Role("ROLE_STAFF",permlist);
			
				
				User newUser = new User("staff","staff",hrrole);
				
				
				
				servclient.saveUser(newUser, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Threw error while saving ------" + caught.toString());
					}

					@Override
					public void onSuccess(String result) {
						Window.alert(result);
					}
					
				});
			
				
			}
        	
        });
        
        
        getRoleButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				servclient.getRole(new AsyncCallback<Role>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString()+"error near getting role");
					}

					@Override
					public void onSuccess(Role result) {
						Window.alert(result.getRoleName()+result.getPermissions().toString());
					}
					
					
				});
			}
        	
        	
        });
        getUserButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				servclient.getUser(usernameTextBox.getText(),new AsyncCallback<User>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					@Override
					public void onSuccess(User result) {
						Window.alert(result.getUsername()+result.getRole().getRoleName()+ result.getRole().getPermissions().get(0).getPermissionName());
					}
					
					
				});
				
				
			}
        });
        
        
        
        
        
        
        forgotPassword.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				
				ResetPasswordPage rsp = new ResetPasswordPage();
				rsp.addStyleName("material-card");
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
        card.add(userSeeButton);
        card.add(saveUserButton);
        card.add(getRoleButton);
        card.add(getUserButton);

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
                        System.out.println("Response Data: " + responseData);
                        
                        checkAuthenticationAndGoToNextPage();
                        
                    } else {
                        System.err.println("HTTP Request Failed with Status Code: " + response.getStatusCode());
                    }
                    
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    System.err.println("Request Error: " + exception.getMessage());
                }
            });
        } catch (RequestException e) {
            System.err.println("Request Exception: " + e.getMessage());
        }
 	     
    }
    
    public void checkAuthenticationAndGoToNextPage() {
    	
//    	servclient.isAuthenticated(new AsyncCallback<Boolean>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert(caught.toString());
//			}
//
//			@Override
//			public void onSuccess(Boolean result) {
//				if(result) {
//					
//					servclient.getUser(username, new AsyncCallback<User>() {
//
//						@Override
//						public void onFailure(Throwable caught) {
//							Window.alert(caught.toString());
//						}
//
//						@Override
//						public void onSuccess(User result) {
//							
//							loggedInUser = result;
//							if(result.getRole().getRoleName().equals("ROLE_ADMIN")) {
//								History.newItem("loggedIn");
//							}
//							
//						}
//			    	});
//				}
//			}
//    	});
    	
    	servclient.getAuthenticatedUser(new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
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


}
