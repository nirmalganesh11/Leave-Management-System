package lms.client.admin.pages;



import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import lms.client.clientservices.HeirarchyServiceClient;
import lms.client.clientservices.HeirarchyServiceClientAsync;
import lms.shared.heirarchy.Company;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class CompanyPage extends Composite {
	
	HeirarchyServiceClientAsync hrServ = GWT.create(HeirarchyServiceClient.class);
	
    private MaterialTextBox companyNameBox;
    private MaterialTextBox companyDescBox;
    private MaterialButton createButton;
    
    
    private String company_name;
    private String company_desc;
    
    
    

	public CompanyPage() {
		
		
    	
        MaterialCard card = new MaterialCard();
        
       
        HTML heading = new HTML("<center><h3>Create Company</h3><center>");
        
        MaterialLabel companyNameLabel = new MaterialLabel("Company Name:");
 
        companyNameBox = new MaterialTextBox();
        
        MaterialLabel companyDescLabel = new MaterialLabel("Company Description:");
        companyDescBox = new MaterialTextBox();
        

        
       createButton = new MaterialButton(ButtonType.RAISED, "Create", new MaterialIcon(IconType.SEND));
        
       
        createButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				company_name = companyNameBox.getText();
				company_desc =	companyDescBox.getText();
				
				//authenticate(userId,email);
				
				Company compObject = new Company(company_name,company_desc);
				hrServ.saveCompany(compObject, new AsyncCallback<String>() {

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
        card.add(companyNameLabel);
        card.add(companyNameBox);
        card.add(companyDescLabel);
        card.add(companyDescBox);
     
        card.add(createButton);
      

        initWidget(card);
    }




	public MaterialTextBox getCompanyNameBox() {
		return companyNameBox;
	}

	public void setCompanyNameBox(MaterialTextBox companyNameBox) {
		this.companyNameBox = companyNameBox;
	}

	public MaterialTextBox getCompanyDescBox() {
		return companyDescBox;
	}

	public void setCompanyDescBox(MaterialTextBox companyDescBox) {
		this.companyDescBox = companyDescBox;
	}

	public MaterialButton getCreateButton() {
		return createButton;
	}

	public void setCreateButton(MaterialButton createButton) {
		this.createButton = createButton;
	}
    
}
