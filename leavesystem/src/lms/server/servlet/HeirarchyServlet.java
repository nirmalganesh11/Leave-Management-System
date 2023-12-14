package lms.server.servlet;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import lms.client.asyncservices.HeirarchyServiceClient;
import lms.server.api.HeirarchyService;
import lms.server.framework.dao.ApplicationContextListener;
import lms.shared.heirarchy.Company;
import lms.shared.heirarchy.Department;

public class HeirarchyServlet extends RemoteServiceServlet implements HeirarchyServiceClient {
	
	private static final long serialVersionUID = 1L;
	
	private ApplicationContext context;
	private HeirarchyService hrServ;
	
	
	public HeirarchyServlet(){
		context = ApplicationContextListener.appContext;
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


	@Override
	public String saveDepartment(Department dept) {
		return hrServ.saveDepartment(dept);
	}


	@Override
	public List<Department> getAllDepartments() {
		return hrServ.getAllDepartments();
	}


	@Override
	public List<Department> companyDepartments(Company company) {
		
		return hrServ.companyDepartments(company);
	}
	
	

}
