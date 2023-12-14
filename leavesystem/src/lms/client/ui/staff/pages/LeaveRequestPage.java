package lms.client.ui.staff.pages;

import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import lms.client.asyncservices.EmployeeServiceClient;
import lms.client.asyncservices.EmployeeServiceClientAsync;
import lms.client.asyncservices.HolidayServiceClient;
import lms.client.asyncservices.HolidayServiceClientAsync;
import lms.client.asyncservices.LeaveRequestServiceClient;
import lms.client.asyncservices.LeaveRequestServiceClientAsync;
import lms.client.asyncservices.LeaveTypeServiceClient;
import lms.client.asyncservices.LeaveTypeServiceClientAsync;
import lms.client.asyncservices.LookupServiceClient;
import lms.client.asyncservices.LookupServiceClientAsync;
import lms.client.asyncservices.UserServiceClient;
import lms.client.asyncservices.UserServiceClientAsync;
import lms.shared.Employee;
import lms.shared.User;
import lms.shared.framework.domain.Category;
import lms.shared.framework.domain.Lookup;
import lms.shared.utility.LeaveRequest;
import lms.shared.utility.LeaveStatus;
import lms.shared.utility.LeaveType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;

public class LeaveRequestPage extends Composite {
	
	LeaveRequestServiceClientAsync reqServ = GWT.create(LeaveRequestServiceClient.class);
	LeaveTypeServiceClientAsync typeServ = GWT.create(LeaveTypeServiceClient.class);
	UserServiceClientAsync userServ = GWT.create(UserServiceClient.class);
	EmployeeServiceClientAsync empServ = GWT.create(EmployeeServiceClient.class);
	HolidayServiceClientAsync holServ = GWT.create(HolidayServiceClient.class);
	
	LookupServiceClientAsync lookServ = GWT.create(LookupServiceClient.class);
	
	private List<LeaveType> listOfTypes  = new ArrayList<>();
	private List<Date> listOfHolidayDates = new ArrayList<>();
	private List<Lookup> requestStatusList = new ArrayList<>();
	
	private Employee presentEmployee;
	private LeaveType selectedLeaveType;
	private LeaveStatus status = LeaveStatus.PENDING;
    private Date start_date = new Date();
    private Date end_date = new Date();
    private String description;
    private int leavesCount;
    
    private User user;
    
    private MaterialTextBox userIdBox;
    private ListBox leaveTypeBox;
    private DateBox startDateBox;
    private DateBox endDateBox;
    private MaterialTextBox countLeavesBox;
    //private MaterialTextBox descriptionBox;
    private TextArea areaText;
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
        areaText  = new TextArea();
       	//descriptionBox = new MaterialTextBox();
       	
       	
       	countLeavesBox.setText("0");
        countLeavesBox.setEnabled(false);
        
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
						//Window.alert(result.getFirstName());
						presentEmployee = result;
					}
					
				});
				
			}

       	});
       	
       	holServ.listHolidayDates(new AsyncCallback<List<Date>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(List<Date> result) {
				listOfHolidayDates.clear();
				listOfHolidayDates = result;
			}
       	});
       	
       	lookServ.getLookupsByCategoryId(Category.REQUEST_STATUS, new AsyncCallback<List<Lookup>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(List<Lookup> result) {
				requestStatusList.addAll(result);
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
				selectedLeaveType =result.get(0);
			}

       	});
       	
       	leaveTypeBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				int selectedIndex = leaveTypeBox.getSelectedIndex();
				selectedLeaveType= listOfTypes.get(selectedIndex);
			}

       	});
       	
       	startDateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                start_date = event.getValue();
                //Window.alert(start_date.toString()+"---"+end_date.toString());
                reqServ.countLeaveDays(start_date, end_date, listOfHolidayDates,new AsyncCallback<Integer>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					@Override
					public void onSuccess(Integer result) {
						countLeavesBox.setText(String.valueOf(result));
						//Window.alert(String.valueOf(result));
					}
                });
            }
        });
       	
     	endDateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                end_date = event.getValue();
                //Window.alert(start_date.toString()+"---"+end_date.toString());
                reqServ.countLeaveDays(start_date, end_date, listOfHolidayDates,new AsyncCallback<Integer>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					@Override
					public void onSuccess(Integer result) {
						countLeavesBox.setText(String.valueOf(result));
						//Window.alert(String.valueOf(result));
					}
                });
            }
        });

        createButton = new MaterialButton(ButtonType.RAISED, "Create", new MaterialIcon(IconType.SEND));
        
       
        createButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				start_date = startDateBox.getValue();
				end_date = endDateBox.getValue();
				description = areaText.getText();
				//description = descriptionBox.getText();
				leavesCount = Integer.valueOf(countLeavesBox.getText());
				
				LeaveRequest lr = new LeaveRequest();
				
				lr.setEmployee(presentEmployee);
				lr.setType(selectedLeaveType);
				lr.setStartDate(start_date);
				lr.setEndDate(end_date);
				//lr.setStatus(status);
				lr.setDescription(description);
				lr.setCountLeaves(leavesCount);
				
				
				
//				Lookup newLookup = new Lookup();
//				newLookup.setName("debug");
//				lr.setLeaveStatus(newLookup);
				
				//Lookup lu = requestStatusList.get(0);
				//Window.alert(lu.getDescription()+" asfafasfdafsd");
			    //lr.getLeaveStatus().setName(lu.getName());
//			    lr.getLeaveStatus().setId(lu.getId());
//			    lr.getLeaveStatus().setCategory(lu.getCategory());
//			    lr.getLeaveStatus().setDescription(lu.getDescription());
//			    lr.getLeaveStatus().setLookupId(lu.getLookupId());
				
				Lookup pendingLookup = findLookupByName(requestStatusList,"PENDING");
				lr.setLeaveStatus(pendingLookup);
				
				//Window.alert(requestStatusList.get(0).getDescription());
				
				
				
				reqServ.saveLeaveRequest(lr, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					@Override
					public void onSuccess(String result) {
						Window.alert(result);
					}

				});
				
				//fetchLookups();
				
				//createsavelookups();
						
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
        
        //card.add(descriptionBox);
        card.add(areaText);
        
        card.add(createButton);
      
        
        initWidget(card);
    }
	
	@SuppressWarnings("rawtypes")
	public void createsavelookups() {
		
		Lookup pendinglookup = new Lookup();
		pendinglookup.setName("PENDING");
		pendinglookup.setDescription("request status of pending");
		pendinglookup.setCategory(Category.REQUEST_STATUS);
		
		
		Lookup approvedlookup = new Lookup();
		approvedlookup.setName("APPROVED");
		approvedlookup.setDescription("request status of approved");
		approvedlookup.setCategory(Category.REQUEST_STATUS);
		
		Lookup rejectedlookup = new Lookup();
		rejectedlookup.setName("REJECTED");
		rejectedlookup.setDescription("request status of rejected");
		rejectedlookup.setCategory(Category.REQUEST_STATUS);
		
		Category statusCategory = new Category();
		statusCategory.setName("statusCategory");
		statusCategory.setLookups(Arrays.asList(pendinglookup, approvedlookup, rejectedlookup));
		
		lookServ.saveCategory(statusCategory, new AsyncCallback<String>() {

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

	
	public void fetchLookups() {
		
		lookServ.getLookupsByCategoryId(314,new AsyncCallback<List<Lookup>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(List<Lookup> result) {
				for(Lookup resulty: result) {
					Window.alert(resulty.getName());
				}
			}
			
			
			
		});
		
		
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

//	public MaterialTextBox getDescriptionBox() {
//		return descriptionBox;
//	}
//
//	public void setDescriptionBox(MaterialTextBox descriptionBox) {
//		this.descriptionBox = descriptionBox;
//	}

	public MaterialButton getCreateButton() {
		return createButton;
	}

	public void setCreateButton(MaterialButton createButton) {
		this.createButton = createButton;
	}
	
	private Lookup findLookupByName(List<Lookup> lookupList, String targetName) {
        for (Lookup lookup : lookupList) {
            if (lookup.getName().equals(targetName)) {
                return lookup; 
            }
        }
        return null; 
    }

}