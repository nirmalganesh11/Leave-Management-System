package lms.server;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import lms.client.clientservices.HeirarchyServiceClient;
import lms.server.serverserviceinterfaces.HeirarchyService;
import lms.shared.heirarchy.Company;

public class HeirarchyServlet extends RemoteServiceServlet implements HeirarchyServiceClient {
	
	private static final long serialVersionUID = 1L;
	
	private ApplicationContext context;
	private HeirarchyService hrServ;
	
	
	public HeirarchyServlet(){
		context = new ClassPathXmlApplicationContext("services.xml");
		hrServ = context.getBean(HeirarchyService.class);
	}
	
	
	@Override
	public String saveCompany(Company company) {
		return hrServ.saveCompany(company);
	}


	@Override
	public List<Company> getAllCompanies() {
		return hrServ.getAllCompanies();
	}
	
	

}