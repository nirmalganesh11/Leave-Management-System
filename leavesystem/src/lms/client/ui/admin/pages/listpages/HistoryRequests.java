package lms.client.ui.admin.pages.listpages;


import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.view.client.ListDataProvider;

import gwt.material.design.client.ui.MaterialCard;
import lms.client.asyncservices.LeaveRequestServiceClient;
import lms.client.asyncservices.LeaveRequestServiceClientAsync;
import lms.client.asyncservices.UserServiceClient;
import lms.client.asyncservices.UserServiceClientAsync;
import lms.shared.User;
import lms.shared.utility.LeaveRequest;

public class HistoryRequests extends Composite {
	
	LeaveRequestServiceClientAsync reqServ = GWT.create(LeaveRequestServiceClient.class);
	UserServiceClientAsync userServ = GWT.create(UserServiceClient.class);
	
	//final SingleSelectionModel<LeaveRequest> selectionModel = new SingleSelectionModel<>();
	ListDataProvider<LeaveRequest> dataProvider =new ListDataProvider<>();
	CellTable<LeaveRequest> leaveRequestTable = new CellTable<>();
	
	int selectedIndexLeaveRequestTable;
	private User loggedInUser;
	
	public HistoryRequests() {
		
	
		
        MaterialCard card = new MaterialCard();
        
        HTML heading = new HTML("<center><h3>Leave Requests</h3><center>");
        
        userServ.getAuthenticatedUser(new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(User result) {
				loggedInUser = result;
					
				reqServ.getAllRequestHistory(new AsyncCallback<List<LeaveRequest>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					@Override
					public void onSuccess(List<LeaveRequest> result) {
						dataProvider.getList().clear();
						dataProvider.getList().addAll(result);
					}
					
				});

			}

        });
        
        
        
        
        
        
        
        leaveRequestTable.setPageSize(50);
        //leaveRequestTable.setSelectionModel(selectionModel);
		
		
		TextColumn<LeaveRequest> leaveRequestIdColumn = new TextColumn<LeaveRequest>() {

			@Override
			public String getValue(LeaveRequest object) {
				return String.valueOf(object.getRequestId());
			}
		};
		
		TextColumn<LeaveRequest> employeeNameColumn = new TextColumn<LeaveRequest>() {

			@Override
			public String getValue(LeaveRequest object) {
				return object.getEmployee().getFirstName();
			}
		};
		
		TextColumn<LeaveRequest> leaveTypeColumn = new TextColumn<LeaveRequest>() {

			@Override
			public String getValue(LeaveRequest object) {
				return object.getType().getLeaveTypeName();
			}
		};
		
		TextColumn<LeaveRequest> startDateColumn = new TextColumn<LeaveRequest>() {

			@Override
			public String getValue(LeaveRequest object) {
				Date dateFormat = object.getStartDate();
           	 	String desiredFormatPattern = "dd-MM-yyyy";
                DateTimeFormat dtf = DateTimeFormat.getFormat(desiredFormatPattern);
                String formattedDate = dtf.format(dateFormat);
                return formattedDate;
			}
		};
		
		TextColumn<LeaveRequest> endDateColumn = new TextColumn<LeaveRequest>() {

			@Override
			public String getValue(LeaveRequest object) {
				Date dateFormat = object.getEndDate();
           	 	String desiredFormatPattern = "dd-MM-yyyy";
                DateTimeFormat dtf = DateTimeFormat.getFormat(desiredFormatPattern);
                String formattedDate = dtf.format(dateFormat);
                return formattedDate;
			}
		};
		TextColumn<LeaveRequest> leavesCountColumn = new TextColumn<LeaveRequest>() {

			@Override
			public String getValue(LeaveRequest object) {
				return String.valueOf(object.getCountLeaves());
			}
		};
		TextColumn<LeaveRequest> descriptionColumn = new TextColumn<LeaveRequest>() {

			@Override
			public String getValue(LeaveRequest object) {
				return object.getDescription();
			}
		};
		TextColumn<LeaveRequest> statusColumn = new TextColumn<LeaveRequest>() {

			@Override
			public String getValue(LeaveRequest object) {
				return object.getLeaveStatus().getName();
			}
		};
        
		leaveRequestTable.addColumn(leaveRequestIdColumn , "Request Id");
		leaveRequestTable.addColumn(employeeNameColumn,"Employee Name");
		leaveRequestTable.addColumn(leaveTypeColumn,"Leave Type");
		leaveRequestTable.addColumn(startDateColumn, "Start Date");
		leaveRequestTable.addColumn(endDateColumn,"End Date");
		leaveRequestTable.addColumn(leavesCountColumn,"Leaves Count");
		leaveRequestTable.addColumn(descriptionColumn,"Description");
		leaveRequestTable.addColumn(statusColumn,"Leave Status");
      
//        selectionModel.addSelectionChangeHandler(new Handler() {
//            @Override
//            public void onSelectionChange(SelectionChangeEvent event) {
//                int selectedIndex = leaveRequestTable.getKeyboardSelectedRow();
//                selectedIndexLeaveRequestTable = selectedIndex;
//                if (selectedIndex >= 0) {
//                    System.out.println("Selected Index: " + selectedIndex);
//                } else {
//                    // No row is selected
//                    System.out.println("No row selected");
//                }
//            }
//        });
        
        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setWidget(leaveRequestTable);
        scrollPanel.setSize("1000px", "400px");
        
        card.add(heading);
        card.add(scrollPanel);
        
        dataProvider.addDataDisplay(leaveRequestTable);
        
        
        initWidget(card);
    }
    
}