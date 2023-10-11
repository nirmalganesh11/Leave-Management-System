package lms.client.admin.pages;

import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import lms.client.clientservices.EmployeeServiceClient;
import lms.client.clientservices.EmployeeServiceClientAsync;
import lms.client.clientservices.HeirarchyServiceClient;
import lms.client.clientservices.HeirarchyServiceClientAsync;
import lms.shared.Employee;
import lms.shared.heirarchy.Company;
import lms.shared.heirarchy.Department;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

public class EmployeeListPage extends Composite {
	
	HeirarchyServiceClientAsync hrServ = GWT.create(HeirarchyServiceClient.class);
	EmployeeServiceClientAsync empServ = GWT.create(EmployeeServiceClient.class);
	
	SingleSelectionModel<Employee> selectionModel = new SingleSelectionModel<>();
	ListDataProvider<Employee> dataProvider =new ListDataProvider<>();
	CellTable<Employee> employeeTable = new CellTable<>();
	
	int selectedIndexEmployeeTable;
	
	public EmployeeListPage() {

        MaterialCard card = new MaterialCard();
        
        HTML heading = new HTML("<center><h3>List of Employees Available</h3><center>");
        
//        hrServ.getAllDepartments(new AsyncCallback<List<Department>>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert(caught.toString());
//			}
//
//			@Override
//			public void onSuccess(List<Department> result) {
//				dataProvider.getList().clear();
//				dataProvider.getList().addAll(result);
//			}
//        	
//        	
//        });
//        
        empServ.getAllEmployees(new AsyncCallback<List<Employee>> (){

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
        
        employeeTable.setPageSize(50);
        employeeTable.setSelectionModel(selectionModel);
		
        TextColumn<Employee> userIdColumn = new TextColumn<Employee>() {

			@Override
			public String getValue(Employee object) {
				return String.valueOf(object.getUserId());
			}
		};
		
		TextColumn<Employee> departmentNameColumn = new TextColumn<Employee>() {

			@Override
			public String getValue(Employee object) {
				return String.valueOf(object.getDepartment().getDepartmentName());
			}
		};
		
		TextColumn<Employee> employeePositionColumn = new TextColumn<Employee>() {

			@Override
			public String getValue(Employee object) {
				return String.valueOf(object.getPosition());
			}
		};
		
		TextColumn<Employee> employeeFirstNameColumn = new TextColumn<Employee>() {

			@Override
			public String getValue(Employee object) {
				return object.getFirstName();
			}
		};
		TextColumn<Employee> employeeLastNameColumn = new TextColumn<Employee>() {

			@Override
			public String getValue(Employee object) {
				return object.getLastName();
			}
		};
		TextColumn<Employee> emailColumn = new TextColumn<Employee>() {

			@Override
			public String getValue(Employee object) {
				return object.getEmail();
			}
		};
        
		employeeTable.addColumn(userIdColumn,"User Id:");
		employeeTable.addColumn(departmentNameColumn,"Dept Name:");
		employeeTable.addColumn(employeePositionColumn, "Employee Position:");
		
		employeeTable.addColumn(employeeFirstNameColumn,"Employee firstName:");
		employeeTable.addColumn(employeeLastNameColumn ,"Employee LastName");
		
		employeeTable.addColumn(emailColumn,"Employee Email:");
      
        selectionModel.addSelectionChangeHandler(new Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                int selectedIndex =	employeeTable.getKeyboardSelectedRow();
                selectedIndexEmployeeTable = selectedIndex;
                if (selectedIndex >= 0) {
                    System.out.println("Selected Index: " + selectedIndex);
                } else {
                    // No row is selected
                    System.out.println("No row selected");
                }
            }
        });
        
        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setWidget(employeeTable);
        scrollPanel.setSize("900px", "400px");
        
        card.add(heading);
        card.add(scrollPanel);
        
        dataProvider.addDataDisplay(employeeTable);
        
        
        initWidget(card);
    }
  
}