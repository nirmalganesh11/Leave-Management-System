package lms.client.admin.pages;

import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import lms.client.clientservices.HeirarchyServiceClient;
import lms.client.clientservices.HeirarchyServiceClientAsync;
import lms.shared.heirarchy.Company;
import lms.shared.heirarchy.Department;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class DepartmentPage extends Composite {
	
	
	HeirarchyServiceClientAsync hrServ = GWT.create(HeirarchyServiceClient.class);
	
	private Company selectedCompany;
	
	
	private List<Company> listOfCompanies = new ArrayList<>();
	
    private MaterialTextBox departmentNameBox;
    private MaterialTextBox departmentDescBox;
    private MaterialButton createButton;
    
    
    private String department_name;
    private String department_desc;
    
    
    ListBox companyListBox = new ListBox();
    
    
    

	public DepartmentPage() {
		
		
    	
        MaterialCard card = new MaterialCard();
        
       
        HTML heading = new HTML("<center><h3>Create Department</h3><center>");
        
        MaterialLabel departmentNameLabel = new MaterialLabel("Department Name:");
 
        departmentNameBox = new MaterialTextBox();
        
        MaterialLabel departmentDescLabel = new MaterialLabel("Department Description:");
        departmentDescBox = new MaterialTextBox();
        
        
//        companyListBox.addItem("Option 1");
//        companyListBox.addItem("Option 2");
//        companyListBox.addItem("Option 3");
        
        hrServ.getAllCompanies(new AsyncCallback<List<Company>>() {

			@Override
			public void onSuccess(List<Company> result) {
				selectedCompany =result.get(0);
				companyListBox.clear();
				for(Company c: result) {
					companyListBox.addItem(c.getCompanyName());
				}
				listOfCompanies.clear();
				listOfCompanies = result;
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}
        	
        });
        
        companyListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				int selectedIndex = companyListBox.getSelectedIndex();
				selectedCompany = listOfCompanies.get(selectedIndex);
			}
        	
        });
        

        
       createButton = new MaterialButton(ButtonType.RAISED, "Create", new MaterialIcon(IconType.SEND));
        
       
        createButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				department_name = departmentNameBox.getText();
				department_desc =	departmentDescBox.getText();
				Department dept = new Department(department_name,department_desc,selectedCompany);
//				Window.alert(String.valueOf(selectedCompany.getCompanyId()));
				
				hrServ.saveDepartment(dept, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					@Override
					public void onSuccess(String result) {
						Window.Location.reload();
					}
					
				});
				
				
				
			}
        	
        });
        
      

        card.add(heading);
        
        card.add(companyListBox);
        card.add(departmentNameLabel);
        card.add(departmentNameBox);
        card.add(departmentDescLabel);
        card.add(departmentDescBox);
        
     
        card.add(createButton);
      

        initWidget(card);
    }




	public MaterialTextBox getDepartmentNameBox() {
		return departmentNameBox;
	}




	public void setDepartmentNameBox(MaterialTextBox departmentNameBox) {
		this.departmentNameBox = departmentNameBox;
	}




	public MaterialTextBox getDepartmentDescBox() {
		return departmentDescBox;
	}




	public void setDepartmentDescBox(MaterialTextBox departmentDescBox) {
		this.departmentDescBox = departmentDescBox;
	}




	public MaterialButton getCreateButton() {
		return createButton;
	}




	public void setCreateButton(MaterialButton createButton) {
		this.createButton = createButton;
	}


	

	
    
}