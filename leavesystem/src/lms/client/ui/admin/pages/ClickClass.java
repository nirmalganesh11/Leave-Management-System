package lms.client.ui.admin.pages;

import com.google.gwt.user.client.Window;

import lms.shared.utility.LeaveRequest;

public class ClickClass implements ActionButtonCell.ActionButtonCellHandler {

    @Override
    public void onAcceptClick(LeaveRequest leaveRequest) {
       Window.alert("wow accept clicked");
    }

    @Override
    public void onRejectClick(LeaveRequest leaveRequest) {
    	Window.alert("wow reject clicked");	
    }

    // Additional methods or fields as needed
}
