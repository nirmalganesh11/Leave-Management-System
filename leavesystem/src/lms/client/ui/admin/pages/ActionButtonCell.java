package lms.client.ui.admin.pages;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialButton;
import lms.shared.utility.LeaveRequest;

public class ActionButtonCell extends AbstractCell<LeaveRequest> {

    public interface ActionButtonCellHandler {
        void onAcceptClick(LeaveRequest leaveRequest);
        void onRejectClick(LeaveRequest leaveRequest);
    }

    private final ActionButtonCellHandler handler;

    public ActionButtonCell(ActionButtonCellHandler handler) {
        super("click");
        this.handler = handler;
    }

    @Override
    public void render(Context context, LeaveRequest value, SafeHtmlBuilder sb) {
        // Render buttons using MaterialButton widget
        final LeaveRequest bvalue = value;
       // Window.alert(value.getDescription());
        Button acceptButton = new Button("Accept");
        acceptButton.setStyleName("customButtonStyle"); 
       
        
        acceptButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
            	GWT.log("Accept button clicked");
                handler.onAcceptClick(bvalue);
            }
        });

        Button rejectButton = new Button("Reject");
        rejectButton.setStyleName("customButtonStyle"); 
        rejectButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
            	
                handler.onRejectClick(bvalue);
            }
        });

        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.add(acceptButton);
        buttonPanel.add(rejectButton);

        sb.appendHtmlConstant(buttonPanel.getElement().getString());
    }
    
    
    
}
