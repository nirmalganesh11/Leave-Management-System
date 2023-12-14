package lms.client.ui.admin.pages;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialLink;
import lms.client.eventbus.AcceptLeaveRequestEvent;
import lms.client.eventbus.AppEventBus;
import lms.client.eventbus.DeleteEmployeeEvent;
import lms.client.eventbus.RejectLeaveRequestEvent;

public class LeaveRequestActionsPage extends Composite {
	
	 MaterialLink acceptLink = new MaterialLink("Accept");
     MaterialLink rejectLink = new MaterialLink("Reject");
     MaterialLink sortLink = new MaterialLink("Sort");
     MaterialLink saveLink = new MaterialLink("Save");
     MaterialLink backToTableLink = new MaterialLink("Back");

	public LeaveRequestActionsPage() {
		
		VerticalPanel navBar = new VerticalPanel();
        navBar.setSpacing(17);
        navBar.addStyleName("nav-bar-copy");
 
        acceptLink.addStyleName("material-link-align-copy");
        rejectLink.addStyleName("material-link-align-copy");
        sortLink.addStyleName("material-link-align-copy");
        saveLink.addStyleName("material-link-align-copy");
        backToTableLink.addStyleName("material-link-align-copy");
        
        acceptLink.setIconType(IconType.CHECK);
        rejectLink.setIconType(IconType.CLOSE);
        sortLink.setIconType(IconType.SORT);
        saveLink.setIconType(IconType.SAVE);
        backToTableLink.setIconType(IconType.FLIP_TO_BACK);
        
        
        acceptLink.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
            	
                AcceptLeaveRequestEvent acceptLeaveRequestEvent = new AcceptLeaveRequestEvent();
                AppEventBus.fireEvent(acceptLeaveRequestEvent);
            }
        });
        
        rejectLink.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
            	
            	Window.alert("reject event clicked");
                RejectLeaveRequestEvent rejectLeaveEvent = new RejectLeaveRequestEvent();
                AppEventBus.fireEvent(rejectLeaveEvent);
            }
        });
        

        navBar.add(acceptLink);
        navBar.add(rejectLink);
        navBar.add(sortLink);
        navBar.add(saveLink);
        navBar.add(backToTableLink);
        
        initWidget(navBar);
    }

	public MaterialLink getAcceptLink() {
		return acceptLink;
	}

	public void setAcceptLink(MaterialLink acceptLink) {
		this.acceptLink = acceptLink;
	}

	public MaterialLink getRejectLink() {
		return rejectLink;
	}

	public void setRejectLink(MaterialLink rejectLink) {
		this.rejectLink = rejectLink;
	}

	public MaterialLink getSortLink() {
		return sortLink;
	}

	public void setSortLink(MaterialLink sortLink) {
		this.sortLink = sortLink;
	}

	public MaterialLink getSaveLink() {
		return saveLink;
	}

	public void setSaveLink(MaterialLink saveLink) {
		this.saveLink = saveLink;
	}


	public MaterialLink getBackToTableLink() {
		return backToTableLink;
	}


	public void setBackToTableLink(MaterialLink backToTableLink) {
		this.backToTableLink = backToTableLink;
	}

}

