package lms.client.asyncservices;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import lms.shared.framework.domain.Category;
import lms.shared.framework.domain.Lookup;

@RemoteServiceRelativePath("lookup")
public interface LookupServiceClient extends RemoteService{
	
	List<Lookup> getLookupsByCategoryId(long categoryId);
	
	String saveCategory(Category category);
	
}
