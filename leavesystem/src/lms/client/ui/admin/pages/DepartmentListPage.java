package lms.client.ui.admin.pages;


import gwt.material.design.client.ui.*;
import lms.client.asyncservices.HeirarchyServiceClient;
import lms.client.asyncservices.HeirarchyServiceClientAsync;

import lms.shared.heirarchy.Department;

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

public class DepartmentListPage extends Composite {
	
	HeirarchyServiceClientAsync hrServ = GWT.create(HeirarchyServiceClient.class);
	
	SingleSelectionModel<Department> selectionModel = new SingleSelectionModel<>();
	ListDataProvider<Department> dataProvider =new ListDataProvider<>();
	CellTable<Department> departmentTable = new CellTable<>();
	
	int selectedIndexCompanyTable;
	
	public DepartmentListPage() {
		
	
		
        MaterialCard card = new MaterialCard();
        
        HTML heading = new HTML("<center><h3>List of Departments Available</h3><center>");
        
        hrServ.getAllDepartments(new AsyncCallback<List<Department>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(List<Department> result) {
				dataProvider.getList().clear();
				dataProvider.getList().addAll(result);
			}
        	
        	
        });
        
        
        departmentTable.setPageSize(50);
        departmentTable.setSelectionModel(selectionModel);
		
		
		TextColumn<Department> departmentIdColumn = new TextColumn<Department>() {

			@Override
			public String getValue(Department object) {
				return String.valueOf(object.getDepartmentId());
			}
		};
		
		TextColumn<Department> departmentNameColumn = new TextColumn<Department>() {

			@Override
			public String getValue(Department object) {
				return object.getDepartmentName();
			}
		};
		TextColumn<Department> departmentDescColumn = new TextColumn<Department>() {

			@Override
			public String getValue(Department object) {
				return object.getDepartmentDescription();
			}
		};
		TextColumn<Department> companyNameColumn = new TextColumn<Department>() {

			@Override
			public String getValue(Department object) {
				return object.getCompany().getCompanyName();
			}
		};
        
		departmentTable.addColumn(departmentIdColumn, "Dept Id:");
		departmentTable.addColumn(companyNameColumn,"Company Name:");
		departmentTable.addColumn(departmentNameColumn,"Dept Name:");
		departmentTable.addColumn(departmentDescColumn,"Dept Description:");
      
        selectionModel.addSelectionChangeHandler(new Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                int selectedIndex = departmentTable.getKeyboardSelectedRow();
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
        scrollPanel.setWidget(departmentTable);
        scrollPanel.setSize("500px", "400px"); 
        
        card.add(heading);
        card.add(scrollPanel);
        
        dataProvider.addDataDisplay(departmentTable);
        
        
        initWidget(card);
    }

}
