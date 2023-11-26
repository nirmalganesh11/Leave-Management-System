package lms.client.ui.admin.pages;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import gwt.material.design.client.ui.MaterialCard;
import lms.client.asyncservices.LeaveRequestServiceClient;
import lms.client.asyncservices.LeaveRequestServiceClientAsync;
import lms.shared.utility.LeaveRequest;

public class LeaveRequestListPage extends Composite {
	
	LeaveRequestServiceClientAsync reqServ = GWT.create(LeaveRequestServiceClient.class);
	
	MultiSelectionModel<LeaveRequest> selectionModel = new MultiSelectionModel<>();
	ListDataProvider<LeaveRequest> dataProvider =new ListDataProvider<>();
	CellTable<LeaveRequest> leaveRequestTable = new CellTable<>();
	
	int selectedIndexLeaveRequestTable;
	
	public LeaveRequestListPage() {
		
	
		
        MaterialCard card = new MaterialCard();
        
        HTML heading = new HTML("<center><h3>Leave Requests</h3><center>");
        
        reqServ.getAllLeaveRequests(new AsyncCallback<List<LeaveRequest>>() {
        	
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(List<LeaveRequest> result) {
				
				 
			    Collections.sort(result, new Comparator<LeaveRequest>() {
			        @Override
			        public int compare(LeaveRequest leaveRequest1, LeaveRequest leaveRequest2) {
			            return Integer.compare(leaveRequest2.getRequestId(), leaveRequest1.getRequestId());
			        }
			    });
			    
				dataProvider.getList().clear();
				dataProvider.getList().addAll(result);
				
			}

        });
        
        
        leaveRequestTable.setPageSize(50);
        leaveRequestTable.setSelectionModel(selectionModel);
		
		
//		TextColumn<LeaveRequest> leaveRequestIdColumn = new TextColumn<LeaveRequest>() {
//
//			@Override
//			public String getValue(LeaveRequest object) {
//				return String.valueOf(object.getRequestId());
//			}
//		};
		
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
				return object.getStatus().getValue();
			}
		};
		
		  // Custom column for Accept/Reject buttons
        Column<LeaveRequest, LeaveRequest> actionColumn = new Column<LeaveRequest, LeaveRequest>(new ActionButtonCell(new ActionButtonCell.ActionButtonCellHandler() {
            @Override
            public void onAcceptClick(LeaveRequest leaveRequest) {
               // updateLeaveRequestStatus(leaveRequest, LeaveStatus.ACCEPTED);
            	Window.alert("accepted");
            }

            @Override
            public void onRejectClick(LeaveRequest leaveRequest) {
                //updateLeaveRequestStatus(leaveRequest, LeaveStatus.REJECTED);
            	Window.alert("Rejected");
            }
        })) {
            @Override
            public LeaveRequest getValue(LeaveRequest object) {
                return object;
            }
        };

        
        
		//leaveRequestTable.addColumn(leaveRequestIdColumn , "Request Id");
		leaveRequestTable.addColumn(employeeNameColumn,"Employee Name");
		leaveRequestTable.addColumn(leaveTypeColumn,"Leave Type");
		leaveRequestTable.addColumn(startDateColumn, "Start Date");
		leaveRequestTable.addColumn(endDateColumn,"End Date");
		leaveRequestTable.addColumn(leavesCountColumn,"Leaves Count");
		leaveRequestTable.addColumn(descriptionColumn,"Description");
		leaveRequestTable.addColumn(statusColumn,"Leave Status");
		
		leaveRequestTable.addColumn(actionColumn, "Actions");
      
        selectionModel.addSelectionChangeHandler(new Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                int selectedIndex = leaveRequestTable.getKeyboardSelectedRow();
                selectedIndexLeaveRequestTable = selectedIndex;
                if (selectedIndex >= 0) {
                    System.out.println("Selected Index: " + selectedIndex);
                } else {
                    // No row is selected
                    System.out.println("No row selected");
                }
            }
        });
        
        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setWidget(leaveRequestTable);
        scrollPanel.setSize("1200px", "400px");
        
        card.add(heading);
        card.add(scrollPanel);
        
        dataProvider.addDataDisplay(leaveRequestTable);
        
        
        initWidget(card);
    }
    
}