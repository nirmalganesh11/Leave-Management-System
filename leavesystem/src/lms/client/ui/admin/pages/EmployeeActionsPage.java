package lms.client.ui.admin.pages;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialNavBar;
import lms.client.eventbus.AppEventBus;
import lms.client.eventbus.DeleteEmployeeEvent;

public class EmployeeActionsPage extends Composite {
	
	 MaterialLink addLink = new MaterialLink("Add");
     MaterialLink deleteLink = new MaterialLink("Delete");
     MaterialLink editLink = new MaterialLink("Edit");
     MaterialLink saveLink = new MaterialLink("Save");
     MaterialLink backToTableLink = new MaterialLink("Back");

	public EmployeeActionsPage() {
		
		VerticalPanel navBar = new VerticalPanel();
        navBar.setSpacing(17);
        navBar.addStyleName("nav-bar-copy");
        
        //MaterialNavBar navBar = new MaterialNavBar();
        //navBar.addStyleName("nav-bar-copy");
      
       
        
        addLink.addStyleName("material-link-align-copy");
        deleteLink.addStyleName("material-link-align-copy");
        editLink.addStyleName("material-link-align-copy");
        saveLink.addStyleName("material-link-align-copy");
        backToTableLink.addStyleName("material-link-align-copy");
        
        addLink.setIconType(IconType.ADD);
        deleteLink.setIconType(IconType.DELETE);
        editLink.setIconType(IconType.EDIT);
        saveLink.setIconType(IconType.SAVE);
        backToTableLink.setIconType(IconType.FLIP_TO_BACK);
        
        deleteLink.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                DeleteEmployeeEvent deleteEvent = new DeleteEmployeeEvent();
                AppEventBus.fireEvent(deleteEvent);
            }
        });

        
        navBar.add(addLink);
        navBar.add(deleteLink);
        navBar.add(editLink);
        navBar.add(saveLink);
        navBar.add(backToTableLink);
        
        initWidget(navBar);
    }
	

	public MaterialLink getAddLink() {
		return addLink;
	}

	public void setAddLink(MaterialLink addLink) {
		this.addLink = addLink;
	}

	public MaterialLink getDeleteLink() {
		return deleteLink;
	}

	public void setDeleteLink(MaterialLink deleteLink) {
		this.deleteLink = deleteLink;
	}

	public MaterialLink getEditLink() {
		return editLink;
	}

	public void setEditLink(MaterialLink editLink) {
		this.editLink = editLink;
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
