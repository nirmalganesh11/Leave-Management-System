package lms.client.ui.staff;

import com.google.gwt.core.client.EntryPoint;


public class StaffEntryPoint implements EntryPoint{
	
	@Override
	public void onModuleLoad() {
		StaffPageHandler pg = new StaffPageHandler();
		pg.createStaffDashboard();
	}

}
