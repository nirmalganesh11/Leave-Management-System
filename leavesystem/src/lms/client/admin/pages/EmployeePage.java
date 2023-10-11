package lms.client.admin.pages;

import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import lms.client.clientservices.AccessControlServiceClient;
import lms.client.clientservices.AccessControlServiceClientAsync;
import lms.client.clientservices.EmployeeServiceClient;
import lms.client.clientservices.EmployeeServiceClientAsync;
import lms.client.clientservices.HeirarchyServiceClient;
import lms.client.clientservices.HeirarchyServiceClientAsync;
import lms.shared.Employee;
import lms.shared.security.*;
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

public class EmployeePage extends Composite {
	
	
	 EmployeeServiceClientAsync empServ = GWT.create(EmployeeServiceClient.class);
	 HeirarchyServiceClientAsync hrServ = GWT.create(HeirarchyServiceClient.class);
	AccessControlServiceClientAsync accServ = GWT.create(AccessControlServiceClient.class);
	 
	 private Company selectedCompany;
	 private List<Company> listOfCompanies = new ArrayList<>();
	 ListBox companyListBox = new ListBox();
	 
	 
	 private Role selectedRole;
	 private List<Role> listOfRoles  = new ArrayList<>();
	 ListBox authorityListBox = new ListBox();
	
	 
	 private Department selectedDepartment;
	 private List<Department> listOfDepartments = new ArrayList<>();
	 ListBox departmentListBox = new ListBox();
	
	 private MaterialTextBox usernameBox;
	 private MaterialTextBox passwordBox;
	 
	 
    private MaterialTextBox employeePositionBox;
    private MaterialTextBox employeeFirstNameBox;
    private MaterialTextBox employeeLastNameBox;
    private MaterialTextBox employeeEmailBox;
   
    private MaterialButton createButton;
    
    
	public EmployeePage() {

        MaterialCard card = new MaterialCard();
        
       
        HTML heading = new HTML("<center><h3>Create Employee</h3><center>");
        
        MaterialLabel employeePositionLabel = new MaterialLabel("Employee Position:");
        employeePositionBox = new MaterialTextBox();
        
        MaterialLabel employeeFirstNameLabel = new MaterialLabel("Employee Firstname:");
        employeeFirstNameBox = new MaterialTextBox();
        
        MaterialLabel employeeLastNameLabel = new MaterialLabel("Employee Lastname:");
        employeeLastNameBox= new MaterialTextBox();
        
        MaterialLabel employeeEmailLabel = new MaterialLabel("Employee Email:");
        employeeEmailBox = new MaterialTextBox();
        
        MaterialLabel usernameLabel = new MaterialLabel("Employee username:");
        usernameBox= new MaterialTextBox();
        
        MaterialLabel passwordLabel = new MaterialLabel("Employee password:");
        passwordBox = new MaterialTextBox();
        
        
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
				//Window.alert(selectedCompany.getCompanyName());
				//Window.alert(String.valueOf(selectedCompany.getCompanyId()));
				
				 hrServ.companyDepartments(selectedCompany, new AsyncCallback<List<Department>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("cannot fetch company departments");
							Window.alert(caught.toString());
						}

						@Override
						public void onSuccess(List<Department> result) {
							
							selectedDepartment = result.get(0);
							departmentListBox.clear();
							for(Department c: result) {
								departmentListBox.addItem(c.getDepartmentName());
							}
							//Window.alert("came here");
							listOfDepartments.clear();
							listOfDepartments.addAll(result);
							
							
							//Window.alert(selectedDepartment.getDepartmentName());
					
						}
			        	
			        	
			        });
				 
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
				
				 hrServ.companyDepartments(selectedCompany, new AsyncCallback<List<Department>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.toString());
						}

						@Override
						public void onSuccess(List<Department> result) {
							selectedDepartment = result.get(0);
							listOfDepartments.clear();
							listOfDepartments.addAll(result);
							
							departmentListBox.clear();
							for(Department c: result) {
								departmentListBox.addItem(c.getDepartmentName());
							}

						}

			        });
	
			}
        	
        });
        
        
        departmentListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				int selectedIndex = departmentListBox.getSelectedIndex();
				selectedDepartment = listOfDepartments.get(selectedIndex);
				//Window.alert(selectedDepartment.getDepartmentName());
			}
        });
        
        accServ.getAllRoles(new AsyncCallback<List<Role>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(List<Role> result) {
				listOfRoles.clear();
				listOfRoles = result;
				selectedRole = result.get(0);
				authorityListBox.clear();
				for(Role r:result) {
					authorityListBox.addItem(r.getRoleName());
				}
			}
        	
        	
        });
        
        
        
        authorityListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				int selectedIndex = authorityListBox.getSelectedIndex();
				//selectedAuthority = authorityListBox.getInd
				selectedRole = listOfRoles.get(selectedIndex);
			}
        	
        	
        });
        
        
        
        

        
       createButton = new MaterialButton(ButtonType.RAISED, "Create", new MaterialIcon(IconType.SEND));
        
       
        createButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Employee emp = new Employee();
				emp.setCompany(selectedDepartment.getCompany());
				emp.setDepartment(selectedDepartment);
				emp.setFirstName(employeeFirstNameBox.getText());
				emp.setLastName(employeeLastNameBox.getText());
				emp.setEmail(employeeEmailBox.getText());
				emp.setPosition(employeePositionBox.getText());
				
				emp.setUsername(usernameBox.getText());
				emp.setPassword(passwordBox.getText());
				emp.setRole(selectedRole);

				Window.alert(String.valueOf(emp.getDepartment().getDepartmentId()));
				empServ.saveEmployee(emp, new AsyncCallback<String>() {

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
        	
        });

        card.add(heading);
        
        card.add(new Label("choose company:"));
        card.add(companyListBox);
        card.add(new Label("choose department"));
        card.add(departmentListBox);
        card.add(new Label("choose authority"));
        card.add(authorityListBox);
        
        
        card.add(usernameLabel);
        card.add(usernameBox);
        
        card.add(passwordLabel);
        card.add(passwordBox);
        
        
        card.add(employeePositionLabel);
        card.add(employeePositionBox);
        
        card.add(employeeFirstNameLabel);
        card.add(employeeFirstNameBox);
        
        card.add(employeeLastNameLabel);
        card.add(employeeLastNameBox);
        
        card.add(employeeEmailLabel);
        card.add(employeeEmailBox);
        
        card.add(createButton);
        

        initWidget(card);
    }


	public MaterialTextBox getUsernameBox() {
		return usernameBox;
	}

	public void setUsernameBox(MaterialTextBox usernameBox) {
		this.usernameBox = usernameBox;
	}


	public MaterialTextBox getPasswordBox() {
		return passwordBox;
	}


	public void setPasswordBox(MaterialTextBox passwordBox) {
		this.passwordBox = passwordBox;
	}


	public MaterialTextBox getEmployeePositionBox() {
		return employeePositionBox;
	}


	public void setEmployeePositionBox(MaterialTextBox employeePositionBox) {
		this.employeePositionBox = employeePositionBox;
	}


	public MaterialTextBox getEmployeeFirstNameBox() {
		return employeeFirstNameBox;
	}


	public void setEmployeeFirstNameBox(MaterialTextBox employeeFirstNameBox) {
		this.employeeFirstNameBox = employeeFirstNameBox;
	}



	public MaterialTextBox getEmployeeLastNameBox() {
		return employeeLastNameBox;
	}


	public void setEmployeeLastNameBox(MaterialTextBox employeeLastNameBox) {
		this.employeeLastNameBox = employeeLastNameBox;
	}

	public MaterialTextBox getEmployeeEmailBox() {
		return employeeEmailBox;
	}


	public void setEmployeeEmailBox(MaterialTextBox employeeEmailBox) {
		this.employeeEmailBox = employeeEmailBox;
	}



	public MaterialButton getCreateButton() {
		return createButton;
	}

	public void setCreateButton(MaterialButton createButton) {
		this.createButton = createButton;
	}
 
}