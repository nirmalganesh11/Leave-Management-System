package lms.client.ui.admin.pages.listpages;


import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
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
import lms.client.asyncservices.HolidayServiceClient;
import lms.client.asyncservices.HolidayServiceClientAsync;
import lms.client.ui.admin.pages.ButtonCell;
import lms.shared.utility.Holiday;

public class HolidayListPage extends Composite {
	
	HolidayServiceClientAsync hrServ = GWT.create(HolidayServiceClient.class);
	
	MultiSelectionModel<Holiday> selectionModel = new MultiSelectionModel<>();
	ListDataProvider<Holiday> dataProvider =new ListDataProvider<>();
	CellTable<Holiday> holidayTable = new CellTable<>();
	
	int selectedIndexHolidayTable;
	
	public HolidayListPage() {	
		
	
		
        MaterialCard card = new MaterialCard();
        
        HTML heading = new HTML("<center><h3>List of Holidays Available</h3><center>");
        
        hrServ.getAllHolidays(new AsyncCallback<List<Holiday>>() {
        	
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(List<Holiday> result) {
				dataProvider.getList().clear();
				dataProvider.getList().addAll(result);
			}
        	
        	
        });
        
        
    	holidayTable.setPageSize(50);
		holidayTable.setSelectionModel(selectionModel);
		
		
		TextColumn<Holiday> holidayIdColumn = new TextColumn<Holiday>() {

			@Override
			public String getValue(Holiday object) {
				return String.valueOf(object.getHolidayId());
			}
		};
		
		TextColumn<Holiday> holidayNameColumn = new TextColumn<Holiday>() {

			@Override
			public String getValue(Holiday object) {
				return object.getHolidayName();
			}
		};
		TextColumn<Holiday> holidayDateColumn = new TextColumn<Holiday>() {

			@Override
			public String getValue(Holiday object) {
				Date dateFormat = object.getHolidayDate();
           	 	String desiredFormatPattern = "dd-MM-yyyy";
                DateTimeFormat dtf = DateTimeFormat.getFormat(desiredFormatPattern);
                String formattedDate = dtf.format(dateFormat);
                return formattedDate;
			}
		};
		
		Column<Holiday, String> buttonColumn = new Column<Holiday, String>(new ButtonCell()) {
		    @Override
		    public String getValue(Holiday object) {
		        // Return any unique identifier for each row (e.g., holidayId) as a string
		        return String.valueOf(object.getHolidayId());
		    }
		};
		
		
		// Set a FieldUpdater to handle the click events for buttons
		buttonColumn.setFieldUpdater(new FieldUpdater<Holiday, String>() {
		    @Override
		    public void update(int index, Holiday object, String value) {
		        // Handle the click events for buttons
		        int holidayId = Integer.parseInt(value);
		        // Assuming the holidayId is used for identification
		        handleButtonClick(object, holidayId);
		    }
		});
		
		
		
		
		
        
        holidayTable.addColumn(holidayIdColumn, "Holiday Id");
        holidayTable.addColumn(holidayNameColumn,"Holiday Name");
        holidayTable.addColumn(holidayDateColumn,"Holiday Date");
        
        holidayTable.addColumn(buttonColumn, "Action");
      
        selectionModel.addSelectionChangeHandler(new Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                int selectedIndex = holidayTable.getKeyboardSelectedRow();
                selectedIndexHolidayTable = selectedIndex;
                if (selectedIndex >= 0) {
                    System.out.println("Selected Index: " + selectedIndex);
                } else {
                    // No row is selected
                    System.out.println("No row selected");
                }
            }
        });
        
        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setWidget(holidayTable);
        scrollPanel.setSize("500px", "400px");
        
        card.add(heading);
        card.add(scrollPanel);
        
        dataProvider.addDataDisplay(holidayTable);
        
        
        initWidget(card);
    }
	private void handleButtonClick(Holiday holiday, int holidayId) {
	    // Implement the logic for handling button click
	    // You can use the holiday object and holidayId as needed
		
		Window.alert("handled button click");
	}
}