package lms.client.ui.admin.pages;

import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import lms.client.asyncservices.AccessControlServiceClient;
import lms.client.asyncservices.AccessControlServiceClientAsync;
import lms.client.asyncservices.EmployeeServiceClient;
import lms.client.asyncservices.EmployeeServiceClientAsync;
import lms.client.asyncservices.HeirarchyServiceClient;
import lms.client.asyncservices.HeirarchyServiceClientAsync;
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

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class EmployeePage extends Composite {
	
	
	 EmployeeServiceClientAsync empServ = GWT.create(EmployeeServiceClient.class);
	 HeirarchyServiceClientAsync hrServ = GWT.create(HeirarchyServiceClient.class);
	AccessControlServiceClientAsync accServ = GWT.create(AccessControlServiceClient.class);
	 
	
	 HorizontalPanel hp = new HorizontalPanel();
	 VerticalPanel panel1 = new VerticalPanel();
	 VerticalPanel panel2 = new VerticalPanel();
	
	 private Company selectedCompany;
	 private List<Company> listOfCompanies = new ArrayList<>();
	 
	 
	 
	 private Role selectedRole;
	 private List<Role> listOfRoles  = new ArrayList<>();
	 
	
	 
	 private Department selectedDepartment;
	 private List<Department> listOfDepartments = new ArrayList<>();
	 
	 ListBox companyListBox = new ListBox();
	 ListBox authorityListBox = new ListBox();
	 ListBox departmentListBox = new ListBox();
	 private MaterialTextBox usernameBox;
	 private MaterialTextBox passwordBox;
	 
	 
    private MaterialTextBox employeePositionBox;
    private MaterialTextBox employeeFirstNameBox;
    private MaterialTextBox employeeLastNameBox;
    private MaterialTextBox employeeEmailBox;
   
    MaterialButton createButton;
    
    
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

				//Window.alert(String.valueOf(emp.getDepartment().getDepartmentId()));
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
        
//        card.add(new Label("choose company:"));
//        card.add(companyListBox);
//        card.add(new Label("choose department"));
//        card.add(departmentListBox);
//        card.add(new Label("choose authority"));
//        card.add(authorityListBox);
//        
//        
//        card.add(usernameLabel);
//        card.add(usernameBox);
//        
//        card.add(passwordLabel);
//        card.add(passwordBox);
        
//        
//        card.add(employeePositionLabel);
//        card.add(employeePositionBox);
//        
//        card.add(employeeFirstNameLabel);
//        card.add(employeeFirstNameBox);
//        
//        card.add(employeeLastNameLabel);
//        card.add(employeeLastNameBox);
//        
//        card.add(employeeEmailLabel);
//        card.add(employeeEmailBox);
//        
//        card.add(createButton);
        
        panel1.add(new Label("choose company:"));
        panel1.add(companyListBox);
        panel1.add(new Label("choose department"));
        panel1.add(departmentListBox);
        panel1.add(new Label("choose authority"));
        panel1.add(authorityListBox);
        
        
        panel1.add(usernameLabel);
        panel1.add(usernameBox);
        
        panel1.add(passwordLabel);
        panel1.add(passwordBox);
        
        
        panel2.add(employeePositionLabel);
        panel2.add(employeePositionBox);
        
        panel2.add(employeeFirstNameLabel);
        panel2.add(employeeFirstNameBox);
        
        panel2.add(employeeLastNameLabel);
        panel2.add(employeeLastNameBox);
        
        panel2.add(employeeEmailLabel);
        panel2.add(employeeEmailBox);
        
        panel2.add(createButton);
        
        hp.setSpacing(40);
        
        hp.add(panel1);
        hp.add(panel2);
        
        card.add(hp);
        
        
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