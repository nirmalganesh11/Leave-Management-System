package lms.client.ui.admin.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import lms.client.asyncservices.EmployeeServiceClient;
import lms.client.asyncservices.EmployeeServiceClientAsync;
import lms.client.asyncservices.MessageServiceClient;
import lms.client.asyncservices.MessageServiceClientAsync;
import lms.client.asyncservices.UserServiceClient;
import lms.client.asyncservices.UserServiceClientAsync;
import lms.shared.Employee;
import lms.shared.User;
import lms.shared.security.Role;
import lms.shared.utility.Message;

public class MessageSendPage extends Composite {
	
	UserServiceClientAsync userServ = GWT.create(UserServiceClient.class);
	EmployeeServiceClientAsync empServ = GWT.create(EmployeeServiceClient.class);
	MessageServiceClientAsync msgServ = GWT.create(MessageServiceClient.class);
	
	VerticalPanel vp = new VerticalPanel();
	
	private MaterialTextBox messageSubjectBox;
    private TextArea descriptionArea;
    private MaterialButton createButton;
    
    private User user;
    private Employee employee;
    private Message newMessage = new Message();
    
    int clickCount =0;
    
	public MessageSendPage() {
		
        MaterialCard card = new MaterialCard();
        
        HTML heading = new HTML("<center><h3>Send Message To All Employees</h3><center>");
        
        MaterialLabel messageSubjectLabel = new MaterialLabel("Message Subject:");
        
        messageSubjectBox = new MaterialTextBox();
        
        MaterialLabel messageDescriptionLabel = new MaterialLabel("Message Description:");
 
        descriptionArea = new TextArea();
        
        descriptionArea.setSize("450px", "250px");
        
        createButton = new MaterialButton(ButtonType.RAISED, "Send", new MaterialIcon(IconType.SEND));
        
  
        
        
        createButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

			    userServ.getAuthenticatedUser(new AsyncCallback<User>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.toString());
						}

						@Override
						public void onSuccess(User result) {
							clickCount++;
							user = result;
							newMessage.setRole(user.getRole());
						}
			        	
			        	
			      });
			        
			        
			    empServ.getEmployee(user.getUserId(),new AsyncCallback<Employee>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.toString());
						}

						@Override
						public void onSuccess(Employee result) {
							clickCount++;
							employee = result;
							newMessage.setEmployee(employee);
						}
			     });
			        
				
				
				newMessage.setSubject(messageSubjectBox.getText());
				newMessage.setSubject(descriptionArea.getText());
				
				
				if (clickCount >= 2) {
					msgServ.saveMessage(newMessage, new AsyncCallback<String>() {

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
        	
        });
        
        vp.setSpacing(20);
        vp.add(messageSubjectLabel);
        vp.add(messageSubjectBox);
        vp.add(messageDescriptionLabel);
        vp.add(descriptionArea);
        
        vp.add(createButton);
        
        card.add(heading);
        card.add(vp);
        
       
        
        
        initWidget(card);
    }
	
	
    
}
