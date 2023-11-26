package lms.client.ui.admin.pages;

import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import lms.client.asyncservices.LeaveTypeServiceClient;
import lms.client.asyncservices.LeaveTypeServiceClientAsync;
import lms.shared.utility.LeaveType;



import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;


public class LeaveTypePage extends Composite {
	
	LeaveTypeServiceClientAsync typeServ = GWT.create(LeaveTypeServiceClient.class);
	
    private MaterialTextBox leaveTypeNameBox;
    private MaterialTextBox leaveTypeDescBox;
    private MaterialTextBox daysBox;
    private MaterialButton createButton;
    
    
    private String type_name;
    private String type_desc;
    private int days_count;
    
    
	public LeaveTypePage() {
		
		
    	
        MaterialCard card = new MaterialCard();
        
       
        HTML heading = new HTML("<center><h3>Create LeaveType</h3><center>");
        
        MaterialLabel leaveTypeNameLabel = new MaterialLabel("LeaveType Name:");
 
        leaveTypeNameBox = new MaterialTextBox();
        
        MaterialLabel leaveTypeDescLabel = new MaterialLabel("LeaveType Desc:");
        leaveTypeDescBox = new MaterialTextBox();
        
        MaterialLabel daysLabel = new MaterialLabel("Days Count:");
        daysBox = new MaterialTextBox();
        
       
       createButton = new MaterialButton(ButtonType.RAISED, "Create", new MaterialIcon(IconType.SEND));
        
       
        createButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				type_name = leaveTypeNameBox.getText();
				type_desc = leaveTypeDescBox.getText();
				days_count = Integer.valueOf(daysBox.getText());
				
				LeaveType newType = new LeaveType(type_name,type_desc,days_count);
				typeServ.saveLeaveType(newType, new AsyncCallback<String>() {

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
        	
        });
        
      

        card.add(heading);
        card.add(leaveTypeNameLabel);
        card.add(leaveTypeNameBox);
        card.add(leaveTypeDescLabel);
        card.add(leaveTypeDescBox);
        card.add(daysLabel);
        card.add(daysBox);
       
        
        card.add(createButton);
      
        
        initWidget(card);
    }


	public MaterialTextBox getLeaveTypeNameBox() {
		return leaveTypeNameBox;
	}


	public void setLeaveTypeNameBox(MaterialTextBox leaveTypeNameBox) {
		this.leaveTypeNameBox = leaveTypeNameBox;
	}


	public MaterialTextBox getLeaveTypeDescBox() {
		return leaveTypeDescBox;
	}


	public void setLeaveTypeDescBox(MaterialTextBox leaveTypeDescBox) {
		this.leaveTypeDescBox = leaveTypeDescBox;
	}


	public MaterialTextBox getDaysBox() {
		return daysBox;
	}


	public void setDaysBox(MaterialTextBox daysBox) {
		this.daysBox = daysBox;
	}


	public MaterialButton getCreateButton() {
		return createButton;
	}


	public void setCreateButton(MaterialButton createButton) {
		this.createButton = createButton;
	}


	
}
