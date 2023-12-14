package lms.client.ui.admin.pages.listpages;


import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
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

import gwt.material.design.client.ui.MaterialCard;
import lms.client.asyncservices.EmployeeServiceClient;
import lms.client.asyncservices.EmployeeServiceClientAsync;
import lms.client.asyncservices.HeirarchyServiceClient;
import lms.client.asyncservices.HeirarchyServiceClientAsync;
import lms.shared.Employee;

public class OnLeaveListPage extends Composite {
	
	
	HeirarchyServiceClientAsync hrServ = GWT.create(HeirarchyServiceClient.class);
	EmployeeServiceClientAsync empServ = GWT.create(EmployeeServiceClient.class);
	
	
	MultiSelectionModel<Employee> selectionModel = new MultiSelectionModel<>();
	ListDataProvider<Employee> dataProvider =new ListDataProvider<>();
	CellTable<Employee> onLeaveEmployeeTable = new CellTable<>();
	
	
	
	int selectedIndexCompanyTable;
	
	public OnLeaveListPage() {
		
	
		
        MaterialCard card = new MaterialCard();
        
        HTML heading = new HTML("<center><h3>List On Leave Employees</h3><center>");
        
       empServ.getOnLeaveEmployees(new AsyncCallback<List<Employee>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(List<Employee> result) {
				dataProvider.getList().clear();
				dataProvider.getList().addAll(result);
			}
			
        });
        
        
    	onLeaveEmployeeTable.setPageSize(50);
    	onLeaveEmployeeTable.setSelectionModel(selectionModel);
		
		
		TextColumn<Employee> employeeIdColumn = new TextColumn<Employee>() {

			@Override
			public String getValue(Employee object) {
				return String.valueOf(object.getUserId());
			}
		};
		
		TextColumn<Employee> employeeNameColumn = new TextColumn<Employee>() {

			@Override
			public String getValue(Employee object) {
				return object.getFirstName();
			}
		};
		
		TextColumn<Employee> employeelastnameColumn = new TextColumn<Employee>() {

			@Override
			public String getValue(Employee object) {
				return object.getLastName();
			}
		};
		TextColumn<Employee> employeeDespColumn = new TextColumn<Employee>() {

			@Override
			public String getValue(Employee object) {
				return object.getDepartment().getDepartmentName();
			}
		};
        
        onLeaveEmployeeTable.addColumn(employeeIdColumn, "Id");
        onLeaveEmployeeTable.addColumn(employeeNameColumn,"First Name");
        onLeaveEmployeeTable.addColumn(employeelastnameColumn,"Last Name");
        onLeaveEmployeeTable.addColumn(employeeDespColumn,"Description");
      
        selectionModel.addSelectionChangeHandler(new Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                int selectedIndex = onLeaveEmployeeTable.getKeyboardSelectedRow();
                selectedIndexCompanyTable = selectedIndex;
                if (selectedIndex >= 0) {
                    System.out.println("Selected Index: " + selectedIndex);
                } else {
                    // No row is selected
                    System.out.println("No row selected");
                }
            }
        });
        
        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setWidget(onLeaveEmployeeTable);
        scrollPanel.setSize("500px", "400px");
        
        card.add(heading);
        card.add(scrollPanel);
        
        dataProvider.addDataDisplay(onLeaveEmployeeTable);
        
        
        initWidget(card);
    }

}
