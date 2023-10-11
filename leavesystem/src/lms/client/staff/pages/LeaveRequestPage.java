package lms.client.staff.pages;

import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import lms.client.clientservices.EmployeeServiceClient;
import lms.client.clientservices.EmployeeServiceClientAsync;
import lms.client.clientservices.LeaveRequestServiceClient;
import lms.client.clientservices.LeaveRequestServiceClientAsync;
import lms.client.clientservices.LeaveTypeServiceClient;
import lms.client.clientservices.LeaveTypeServiceClientAsync;
import lms.client.clientservices.UserServiceClient;
import lms.client.clientservices.UserServiceClientAsync;
import lms.shared.Employee;
import lms.shared.User;
import lms.shared.utility.LeaveStatus;
import lms.shared.utility.LeaveType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;

public class LeaveRequestPage extends Composite {
	
	LeaveRequestServiceClientAsync reqServ = GWT.create(LeaveRequestServiceClient.class);
	LeaveTypeServiceClientAsync typeServ = GWT.create(LeaveTypeServiceClient.class);
	UserServiceClientAsync userServ = GWT.create(UserServiceClient.class);
	EmployeeServiceClientAsync empServ = GWT.create(EmployeeServiceClient.class);
	
	private List<LeaveType> listOfTypes  = new ArrayList<>();
	
	private Employee presentEmployee;
	private LeaveType selectedLeaveType;
	private LeaveStatus status = LeaveStatus.PENDING;
    private Date start_date;
    private Date end_date;
    private String description;
    private int leavesCount;
    
    private User user;
    
    private MaterialTextBox userIdBox;
    private ListBox leaveTypeBox;
    private DateBox startDateBox;
    private DateBox endDateBox;
    private MaterialTextBox countLeavesBox;
    private MaterialTextBox descriptionBox;
    private MaterialButton createButton;
    
	public LeaveRequestPage() {
		
		
        MaterialCard card = new MaterialCard();
        
        HTML heading = new HTML("<center><h3>Create Leave Request</h3><center>");
        
        MaterialLabel employeeIdLabel = new MaterialLabel("Employee Name:");
 
        userIdBox = new MaterialTextBox();
        
        MaterialLabel leaveTypeLabel = new MaterialLabel("Choose LeaveType:");
        leaveTypeBox = new ListBox();
        
        MaterialLabel 	startDateLabel = new MaterialLabel("Choose Start Date:");
        startDateBox = new DateBox();
        
        MaterialLabel endDateLabel = new MaterialLabel("Choose End Date:");
        endDateBox = new DateBox();
        
        MaterialLabel leavesCountLabel = new MaterialLabel("Leaves Count:");
       	countLeavesBox = new MaterialTextBox();
        
        MaterialLabel descriptionLabel = new MaterialLabel("Request Description:");
       	descriptionBox = new MaterialTextBox();
        
       	userServ.getAuthenticatedUser(new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(User result) {
				user =result;
				userIdBox.setText(user.getUsername());
				userIdBox.setEnabled(false);
				
				empServ.getEmployee(user.getUserId(),new AsyncCallback<Employee>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					@Override
					public void onSuccess(Employee result) {
						Window.alert(result.getFirstName());
						presentEmployee = result;
					}
					
				});
				
			}

       	});
       	
       	typeServ.getAllLeaveTypes(new AsyncCallback<List<LeaveType>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(List<LeaveType> result) {
				listOfTypes.clear();
				listOfTypes.addAll(result);
				leaveTypeBox.clear();
				for(LeaveType type: result) {
					leaveTypeBox.addItem(type.getLeaveTypeName());
				}
			}

       	});

        createButton = new MaterialButton(ButtonType.RAISED, "Create", new MaterialIcon(IconType.SEND));
        
       
        createButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				
											
			}
        	
        });
        
      

        card.add(heading);
        card.add(employeeIdLabel);
        
        card.add(userIdBox);
        card.add(leaveTypeLabel);
        
        card.add(leaveTypeBox);
        card.add(startDateLabel);
        
        card.add(startDateBox);
        card.add(endDateLabel);
        
        card.add(endDateBox);
        card.add(leavesCountLabel);
        
        card.add(countLeavesBox);
        card.add(descriptionLabel);
        
        card.add(descriptionBox);
        
        card.add(createButton);
      
        
        initWidget(card);
    }

	public MaterialTextBox getUserIdBox() {
		return userIdBox;
	}

	public void setUserIdBox(MaterialTextBox userIdBox) {
		this.userIdBox = userIdBox;
	}

	public ListBox getLeaveTypeBox() {
		return leaveTypeBox;
	}

	public void setLeaveTypeBox(ListBox leaveTypeBox) {
		this.leaveTypeBox = leaveTypeBox;
	}

	public DateBox getStartDateBox() {
		return startDateBox;
	}

	public void setStartDateBox(DateBox startDateBox) {
		this.startDateBox = startDateBox;
	}

	public DateBox getEndDateBox() {
		return endDateBox;
	}

	public void setEndDateBox(DateBox endDateBox) {
		this.endDateBox = endDateBox;
	}

	public MaterialTextBox getDescriptionBox() {
		return descriptionBox;
	}

	public void setDescriptionBox(MaterialTextBox descriptionBox) {
		this.descriptionBox = descriptionBox;
	}

	public MaterialButton getCreateButton() {
		return createButton;
	}

	public void setCreateButton(MaterialButton createButton) {
		this.createButton = createButton;
	}

}