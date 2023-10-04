package lms.client.clientservices;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import lms.shared.heirarchy.Company;

public interface HeirarchyServiceClientAsync {
	
	void saveCompany(Company company , AsyncCallback<String> callback);
	
	void getAllCompanies(AsyncCallback<List<Company>> callback);
}
