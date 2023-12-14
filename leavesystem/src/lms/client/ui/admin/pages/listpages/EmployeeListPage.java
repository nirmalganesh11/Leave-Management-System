package lms.client.ui.admin.pages.listpages;


import gwt.material.design.client.ui.*;
import lms.client.asyncservices.EmployeeServiceClient;
import lms.client.asyncservices.EmployeeServiceClientAsync;
import lms.client.asyncservices.HeirarchyServiceClient;
import lms.client.asyncservices.HeirarchyServiceClientAsync;
import lms.client.eventbus.AppEventBus;
import lms.client.eventbus.DeleteEmployeeEvent;
import lms.shared.Employee;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;


import java.util.List;
import java.util.Set;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

public class EmployeeListPage extends Composite {
	
	HeirarchyServiceClientAsync hrServ = GWT.create(HeirarchyServiceClient.class);
	EmployeeServiceClientAsync empServ = GWT.create(EmployeeServiceClient.class);
	
	//SingleSelectionModel<Employee> selectionModel = new SingleSelectionModel<>();
	MultiSelectionModel<Employee> selectionModel = new MultiSelectionModel<>();
	ListDataProvider<Employee> dataProvider =new ListDataProvider<>();
	CellTable<Employee> employeeTable = new CellTable<>();
	
	int selectedIndexEmployeeTable;
	
	public EmployeeListPage() {

        MaterialCard card = new MaterialCard();
        
        HTML heading = new HTML("<center><h3>List of Employees Available</h3><center>");
        
        
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
        

        AppEventBus.getInstance().addHandler(DeleteEmployeeEvent.getType(), new DeleteEmployeeEvent.Handler() {
            @Override
            public void onDeleteEmployee(DeleteEmployeeEvent event) {
            	
            	if(selectedIndexEmployeeTable != 0) {
            		
            	empServ.deleteEmployee(selectedIndexEmployeeTable, new AsyncCallback<String>() {

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
        
        employeeTable.setPageSize(50);
     
        employeeTable.setSelectionModel(selectionModel);
		
			
			Column<Employee, String> empIdColumn = new Column<Employee, String>(new EditTextCell()) {
	            @Override
	            public String getValue(Employee contact) {
	                return contact.getUserId()+"";
	            }
	        };
	       empIdColumn.setFieldUpdater(new FieldUpdater<Employee, String>() {
	            @Override
	            public void update(int index, Employee object, String value) {

	            	//Window.alert(object.getUserId()+"this is object id");

	            }
	        });
		
		Column<Employee, String> employeePositionColumn = new Column<Employee, String>(new EditTextCell()) {
            @Override
            public String getValue(Employee contact) {
                return contact.getPosition();
            }
        };
       employeePositionColumn.setFieldUpdater(new FieldUpdater<Employee, String>() {
            @Override
            public void update(int index, Employee object, String value) {
                // Update the data model when the cell value is edited
            	//Window.alert(object.getUserId()+"this is object id");
            	
                object.setEmail(value);
                //Window.alert(object.getEmail());
            }
        });
		
		Column<Employee, String> employeeFirstNameColumn = new Column<Employee, String>(new EditTextCell()) {
            @Override
            public String getValue(Employee contact) {
                return contact.getFirstName();
            }
        };
       employeeFirstNameColumn.setFieldUpdater(new FieldUpdater<Employee, String>() {
            @Override
            public void update(int index, Employee object, String value) {
               
            	//Window.alert(object.getUserId()+"this is object id");
            	
                object.setEmail(value);
                //Window.alert(object.getEmail());
            }
        });

		Column<Employee, String> employeeUsernameColumn = new Column<Employee, String>(new EditTextCell()) {
            @Override
            public String getValue(Employee contact) {
                return contact.getUsername();
            }
        };
       employeeUsernameColumn.setFieldUpdater(new FieldUpdater<Employee, String>() {
            @Override
            public void update(int index, Employee object, String value) {
                // Update the data model when the cell value is edited
            	//Window.alert(object.getUserId()+"this is object id");
            	
                object.setEmail(value);
                //Window.alert(object.getEmail());
            }
        });
		
		Column<Employee, String> employeePasswordColumn = new Column<Employee, String>(new EditTextCell()) {
            @Override
            public String getValue(Employee contact) {
                return contact.getPassword();
            }
        };
       employeePasswordColumn.setFieldUpdater(new FieldUpdater<Employee, String>() {
            @Override
            public void update(int index, Employee object, String value) {
             
            	//Window.alert(object.getUserId()+"this is object id");
            	
                object.setEmail(value);
               // Window.alert(object.getEmail());
            }
        });
		
		
		Column<Employee, String> employeeLastNameColumn = new Column<Employee, String>(new EditTextCell()) {
            @Override
            public String getValue(Employee contact) {
                return contact.getLastName();
            }
        };
       employeeLastNameColumn.setFieldUpdater(new FieldUpdater<Employee, String>() {
            @Override
            public void update(int index, Employee object, String value) {
               
            	//Window.alert(object.getUserId()+"this is object id");
            	
                object.setEmail(value);
                //Window.alert(object.getEmail());
            }
        });
		
		Column<Employee, String> emailColumn = new Column<Employee, String>(new EditTextCell()) {
	            @Override
	            public String getValue(Employee contact) {
	                return contact.getEmail();
	            }
	        };
	       emailColumn.setFieldUpdater(new FieldUpdater<Employee, String>() {
	            @Override
	            public void update(int index, Employee object, String value) {
	             
	            	//Window.alert(object.getUserId()+"this is object id");
	            	
	                object.setEmail(value);
	                //Window.alert(object.getEmail());
	            }
	        });
		
		employeeTable.addColumn(empIdColumn,"Employee Id:");
		
		employeeTable.addColumn(employeeFirstNameColumn,"Employee firstName:");
		employeeTable.addColumn(employeeLastNameColumn ,"Employee LastName");
		employeeTable.addColumn(employeePositionColumn, "Employee Position:");
		employeeTable.addColumn(emailColumn,"Employee Email:");
		
		
		employeeTable.addColumn(employeeUsernameColumn,"Username");
		employeeTable.addColumn(employeePasswordColumn,"Password");
		
		
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
            	//Window.alert("alerted");
                Set<Employee> selectedEmployees = selectionModel.getSelectedSet();
                
                for (Employee selectedEmployee : selectedEmployees) {
                    //s=s+selectedEmployee.getUserId();
                    selectedIndexEmployeeTable= selectedEmployee.getUserId();
                    //Window.alert("Selected Employee Id: " + selectedEmployee.getUserId());
                }
                
            }
        });
		
		
        
        
        
        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setWidget(employeeTable);
        scrollPanel.setSize("1250px", "400px");
        
        card.add(heading);
        card.add(scrollPanel);
        
        dataProvider.addDataDisplay(employeeTable);
        
        
        initWidget(card);
    }
  
}