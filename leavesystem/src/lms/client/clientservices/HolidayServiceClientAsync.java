package lms.client.clientservices;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import lms.shared.utility.Holiday;

public interface HolidayServiceClientAsync {
	
	void saveHoliday(Holiday holiday,AsyncCallback<String> callback);
	
	void getAllHolidays(AsyncCallback<List<Holiday>> callback);
}
