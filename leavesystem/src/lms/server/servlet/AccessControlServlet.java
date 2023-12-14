package lms.server.servlet;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import lms.client.asyncservices.AccessControlServiceClient;
import lms.server.api.AccessControlService;
import lms.server.framework.dao.ApplicationContextListener;
import lms.shared.security.Role;

public class AccessControlServlet extends  RemoteServiceServlet implements AccessControlServiceClient{

	
	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private AccessControlService accServ;
	
	
	public AccessControlServlet(){
		context = ApplicationContextListener.appContext;
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
