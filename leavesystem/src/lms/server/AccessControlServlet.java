package lms.server;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import lms.client.clientservices.AccessControlServiceClient;
import lms.server.serverserviceinterfaces.AccessControlService;

import lms.shared.security.Role;

public class AccessControlServlet extends  RemoteServiceServlet implements AccessControlServiceClient{

	
	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private AccessControlService accServ;
	
	
	public AccessControlServlet(){
		context = new ClassPathXmlApplicationContext("services.xml");
		accServ = context.getBean(AccessControlService.class);
	}
	
	
	
	@Override
	public Role getRole(int roleId) {
		return accServ.getRole(roleId);
	}

	@Override
	public List<Role> getAllRoles() {
		return accServ.getAllRoles();
	}

}
