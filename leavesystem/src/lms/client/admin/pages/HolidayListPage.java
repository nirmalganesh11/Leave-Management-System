package lms.client.admin.pages;


import gwt.material.design.client.ui.*;

import lms.client.clientservices.HolidayServiceClient;
import lms.client.clientservices.HolidayServiceClientAsync;
import lms.shared.utility.Holiday;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

public class HolidayListPage extends Composite {
	
	HolidayServiceClientAsync hrServ = GWT.create(HolidayServiceClient.class);
	
	SingleSelectionModel<Holiday> selectionModel = new SingleSelectionModel<>();
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
        
        holidayTable.addColumn(holidayIdColumn, "Holiday Id");
        holidayTable.addColumn(holidayNameColumn,"Holiday Name");
        holidayTable.addColumn(holidayDateColumn,"Holiday Date");
      
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
    
}