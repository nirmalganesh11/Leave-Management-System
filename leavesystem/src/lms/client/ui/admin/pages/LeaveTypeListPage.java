package lms.client.ui.admin.pages;

import gwt.material.design.client.ui.*;
import lms.client.asyncservices.LeaveTypeServiceClient;
import lms.client.asyncservices.LeaveTypeServiceClientAsync;
import lms.shared.utility.LeaveType;


import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

public class LeaveTypeListPage extends Composite {
	
	LeaveTypeServiceClientAsync typeServ = GWT.create(LeaveTypeServiceClient.class);
	
	SingleSelectionModel<LeaveType> selectionModel = new SingleSelectionModel<>();
	ListDataProvider<LeaveType> dataProvider =new ListDataProvider<>();
	CellTable<LeaveType> leaveTypeTable = new CellTable<>();
	
	int selectedIndexLeaveTypeTable;
	
	public LeaveTypeListPage() {
		
	
		
        MaterialCard card = new MaterialCard();
        
        HTML heading = new HTML("<center><h3>List of LeaveTypes Available</h3><center>");
        
        typeServ.getAllLeaveTypes(new AsyncCallback<List<LeaveType>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(List<LeaveType> result) {
				dataProvider.getList().clear();
				dataProvider.getList().addAll(result);
			}

        });
        
    	leaveTypeTable.setPageSize(50);
		leaveTypeTable.setSelectionModel(selectionModel);
		
		
		TextColumn<LeaveType> leaveTypeIdColumn = new TextColumn<LeaveType>() {

			@Override
			public String getValue(LeaveType object) {
				return String.valueOf(object.getLeaveTypeId());
			}
		};
		
		TextColumn<LeaveType> leaveTypeNameColumn = new TextColumn<LeaveType>() {

			@Override
			public String getValue(LeaveType object) {
				return object.getLeaveTypeName();
			}
		};
		TextColumn<LeaveType> leaveTypeDescColumn = new TextColumn<LeaveType>() {

			@Override
			public String getValue(LeaveType object) {
				return object.getLeaveTypeDesc();
			}
		};
		TextColumn<LeaveType> daysCountColumn = new TextColumn<LeaveType>() {

			@Override
			public String getValue(LeaveType object) {
				return String.valueOf(object.getDaysCount());
			}
		};
        
        leaveTypeTable.addColumn(leaveTypeIdColumn, "LeaveType Id");
        leaveTypeTable.addColumn(leaveTypeNameColumn,"LeaveType Name");
        leaveTypeTable.addColumn(leaveTypeDescColumn,"LeaveType Desc");
        leaveTypeTable.addColumn(daysCountColumn,"Days Count");
      
        selectionModel.addSelectionChangeHandler(new Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                int selectedIndex = leaveTypeTable.getKeyboardSelectedRow();
                selectedIndexLeaveTypeTable = selectedIndex;
                if (selectedIndex >= 0) {
                    System.out.println("Selected Index: " + selectedIndex);
                } else {
                    // No row is selected
                    System.out.println("No row selected");
                }
            }
        });
        
        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setWidget(leaveTypeTable);
        scrollPanel.setSize("500px", "400px");
        
        card.add(heading);
        card.add(scrollPanel);
        
        dataProvider.addDataDisplay(leaveTypeTable);
        
        
        initWidget(card);
    }
    
}
