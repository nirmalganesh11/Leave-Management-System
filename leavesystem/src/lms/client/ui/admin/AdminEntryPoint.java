package lms.client.ui.admin;

import com.google.gwt.core.client.EntryPoint;


public class AdminEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {

		AdminPageHandler pg = new AdminPageHandler();
		pg.createAdminDashboard();
		
	}

}
