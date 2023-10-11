package lms.client.admin.pages;


import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;

import lms.client.clientservices.HolidayServiceClient;
import lms.client.clientservices.HolidayServiceClientAsync;

import lms.shared.utility.Holiday;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;

public class HolidayPage extends Composite {
	
	HolidayServiceClientAsync holidayServ = GWT.create(HolidayServiceClient.class);
	
    private MaterialTextBox holidayNameBox;
    private DateBox holidayDateBox;
    private MaterialButton createButton;
    
    
    private String holiday_name;
    private Date   holiday_date;
    
    
	public HolidayPage() {
		
		
    	
        MaterialCard card = new MaterialCard();
        
       
        HTML heading = new HTML("<center><h3>Create Holiday</h3><center>");
        
        MaterialLabel holidayNameLabel = new MaterialLabel("Holiday Name:");
 
        holidayNameBox = new MaterialTextBox();
        
        MaterialLabel holidayDateLabel = new MaterialLabel("Holiday Date:");
        holidayDateBox = new DateBox();
        
       
       createButton = new MaterialButton(ButtonType.RAISED, "Create", new MaterialIcon(IconType.SEND));
        
       
        createButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				holiday_name = holidayNameBox.getText();
				holiday_date = holidayDateBox.getValue();
				
				if(!holiday_name.equals("") && !(holiday_date == null)) {
					Holiday newHoliday = new Holiday(holiday_date,holiday_name);
					holidayServ.saveHoliday(newHoliday, new AsyncCallback<String>() {

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
        
      

        card.add(heading);
        card.add(holidayNameLabel);
        card.add(holidayNameBox);
        card.add(holidayDateLabel);
        card.add(holidayDateBox);
       
        
        card.add(createButton);
      
        
        initWidget(card);
    }


	public MaterialTextBox getHolidayNameBox() {
		return holidayNameBox;
	}

	public void setHolidayNameBox(MaterialTextBox holidayNameBox) {
		this.holidayNameBox = holidayNameBox;
	}

	public DateBox getHolidayDateBox() {
		return holidayDateBox;
	}

	public void setHolidayDateBox(DateBox holidayDateBox) {
		this.holidayDateBox = holidayDateBox;
	}

	public MaterialButton getCreateButton() {
		return createButton;
	}


	public void setCreateButton(MaterialButton createButton) {
		this.createButton = createButton;
	}


	
    
}