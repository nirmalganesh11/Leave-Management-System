package lms.client.ui.security;

import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.client.Window;

import com.google.gwt.user.client.ui.*;

public class ResetPasswordPage extends Composite {
	
	
    private MaterialTextBox userIdTextBox;
    private MaterialTextBox emailTextBox;
    private MaterialButton resetPasswordButton;
    
    
    private String userId;
    private String email;
    
    
    

	public ResetPasswordPage() {
		
		
    	
        MaterialCard card = new MaterialCard();
        
       
        HTML heading = new HTML("<center><h3>System Password Reset</h3><center>");
        
        MaterialLabel userIdLabel = new MaterialLabel("UserID:");
        // Create widgets for the login form
        userIdTextBox = new MaterialTextBox();
        
        MaterialLabel emailLabel = new MaterialLabel("Email:");
        emailTextBox = new MaterialTextBox();
        
        Button test = new Button("test");
        
        //MaterialLink forgotPassword = new MaterialLink("forgot your password?");
        
       resetPasswordButton = new MaterialButton(ButtonType.RAISED, "Reset Password", new MaterialIcon(IconType.SEND));
        
        MaterialLink backToLoginButton = new MaterialLink("Back to Login!");
        resetPasswordButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				userId = userIdTextBox.getText();
				email =	emailTextBox.getText();
				Window.alert(userId+"--"+email);
				//authenticate(userId,email);
											
			}
        	
        });
        
        backToLoginButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				
		    	RootPanel.get().addStyleName("login-page");
		    	
		    	LoginView loginView = new LoginView();
		        loginView.addStyleName("material-card-login");
		        loginView.getUsernameTextBox().addStyleName("material-textbox");
		        loginView.getPasswordTextBox().addStyleName("material-textbox");
		        loginView.getLoginButton().addStyleName("material-button");

		        RootPanel.get().add(loginView);
			}
        	
        	
        });
        

        card.add(heading);
        card.add(userIdLabel);
        card.add(userIdTextBox);
        card.add(emailLabel);
        card.add(emailTextBox);
        //card.add(forgotPassword);
        card.add(backToLoginButton);
        card.add(resetPasswordButton);
        card.add(test);

        initWidget(card);
    }
    
    public void authenticate(String userid,String email) {
    	
//    	fullUserServ.authenticateUserIdMail(userid, email, new AsyncCallback<Boolean>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Found error");
//			}
//
//			@Override
//			public void onSuccess(Boolean result) {
//				if(result) {
//					Window.alert("found the goddaman thing");
//				}
//				
//				
//			}
//    		
//    	});
    	
    }

    
    
    public MaterialTextBox getUserIdTextBox() {
		return userIdTextBox;
	}

	public void setUserIdTextBox(MaterialTextBox userIdTextBox) {
		this.userIdTextBox = userIdTextBox;
	}

	public MaterialTextBox getEmailTextBox() {
		return emailTextBox;
	}

	public void setEmailTextBox(MaterialTextBox emailTextBox) {
		this.emailTextBox = emailTextBox;
	}

	public MaterialButton getResetPasswordButton() {
		return resetPasswordButton;
	}

	public void setResetPasswordButton(MaterialButton resetPasswordButton) {
		this.resetPasswordButton = resetPasswordButton;
	}
    

}
