package lms.client.clientservices;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import lms.shared.heirarchy.Company;

@RemoteServiceRelativePath("ladder")
public interface HeirarchyServiceClient extends RemoteService {
	
	String saveCompany(Company company);
	
	List<Company> getAllCompanies();
	
}
