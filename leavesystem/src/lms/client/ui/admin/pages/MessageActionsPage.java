package lms.client.ui.admin.pages;


import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialLink;

public class MessageActionsPage extends Composite {
	
	 MaterialLink sendLink = new MaterialLink("Send");
     MaterialLink inboxLink = new MaterialLink("Inbox");
     MaterialLink pastMessagesLink = new MaterialLink("Logs");
     MaterialLink saveLink = new MaterialLink("Save");
     MaterialLink backToTableLink = new MaterialLink("Back");

	public MessageActionsPage() {
		
		VerticalPanel navBar = new VerticalPanel();
        navBar.setSpacing(17);
        navBar.addStyleName("nav-bar-copy");
       
        sendLink.addStyleName("material-link-align-copy");
        inboxLink.addStyleName("material-link-align-copy");
        pastMessagesLink.addStyleName("material-link-align-copy");
        saveLink.addStyleName("material-link-align-copy");
        backToTableLink.addStyleName("material-link-align-copy");
        
        sendLink.setIconType(IconType.SEND);
        inboxLink.setIconType(IconType.INBOX);
        pastMessagesLink.setIconType(IconType.RECORD_VOICE_OVER);
        saveLink.setIconType(IconType.SAVE);
        backToTableLink.setIconType(IconType.FLIP_TO_BACK);
        
        
        navBar.add(sendLink);
        navBar.add(inboxLink);
        navBar.add(pastMessagesLink);
        navBar.add(saveLink);
        navBar.add(backToTableLink);
        
        initWidget(navBar);
    }
	

	

	public MaterialLink getSendLink() {
		return sendLink;
	}

	public void setSendLink(MaterialLink sendLink) {
		this.sendLink = sendLink;
	}

	public MaterialLink getInboxLink() {
		return inboxLink;
	}

	public void setInboxLink(MaterialLink inboxLink) {
		this.inboxLink = inboxLink;
	}

	public MaterialLink getPastMessagesLink() {
		return pastMessagesLink;
	}

	public void setPastMessagesLink(MaterialLink pastMessagesLink) {
		this.pastMessagesLink = pastMessagesLink;
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
