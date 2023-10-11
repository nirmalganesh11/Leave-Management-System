package lms.client.admin.pages;

import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import lms.client.clientservices.HeirarchyServiceClient;
import lms.client.clientservices.HeirarchyServiceClientAsync;
import lms.shared.heirarchy.Company;

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

public class CompanyListPage extends Composite {
	
	HeirarchyServiceClientAsync hrServ = GWT.create(HeirarchyServiceClient.class);
	
	SingleSelectionModel<Company> selectionModel = new SingleSelectionModel<>();
	ListDataProvider<Company> dataProvider =new ListDataProvider<>();
	CellTable<Company> companyTable = new CellTable<>();
	
	int selectedIndexCompanyTable;
	
	public CompanyListPage() {
		
	
		
        MaterialCard card = new MaterialCard();
        
        HTML heading = new HTML("<center><h3>List of Companies Available</h3><center>");
        
        hrServ.getAllCompanies(new AsyncCallback<List<Company>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(List<Company> result) {
				dataProvider.getList().clear();
				dataProvider.getList().addAll(result);
			}
        	
        	
        });
        
        
    	companyTable.setPageSize(50);
		companyTable.setSelectionModel(selectionModel);
		
		
		TextColumn<Company> companyIdColumn = new TextColumn<Company>() {

			@Override
			public String getValue(Company object) {
				return String.valueOf(object.getCompanyId());
			}
		};
		
		TextColumn<Company> companyNameColumn = new TextColumn<Company>() {

			@Override
			public String getValue(Company object) {
				return object.getCompanyName();
			}
		};
		TextColumn<Company> companyDescColumn = new TextColumn<Company>() {

			@Override
			public String getValue(Company object) {
				return object.getCompanyDescription();
			}
		};
        
        companyTable.addColumn(companyIdColumn, "Id");
        companyTable.addColumn(companyNameColumn,"Name");
        companyTable.addColumn(companyDescColumn,"Description");
      
        selectionModel.addSelectionChangeHandler(new Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                int selectedIndex = companyTable.getKeyboardSelectedRow();
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
        scrollPanel.setWidget(companyTable);
        scrollPanel.setSize("500px", "400px");
        
        card.add(heading);
        card.add(scrollPanel);
        
        dataProvider.addDataDisplay(companyTable);
        
        
        initWidget(card);
    }

	



    
}
