package lms.server.servlet;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import lms.client.asyncservices.MessageServiceClient;
import lms.server.api.LookupService;
import lms.server.api.MessageService;
import lms.server.framework.dao.ApplicationContextListener;
import lms.shared.utility.Message;

public class MessageServlet extends RemoteServiceServlet implements MessageServiceClient {

	private static final long serialVersionUID = 1L;

	private ApplicationContext context;
	private MessageService msgService;
	
	
	public MessageServlet(){
		context = ApplicationContextListener.appContext;
		msgService = context.getBean(MessageService.class);
	}
	
	@Override
	public String saveMessage(Message msg) {
		return msgService.saveMessage(msg);
	}

	@Override
	public List<Message> getHrMessages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getStaffMessages() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
