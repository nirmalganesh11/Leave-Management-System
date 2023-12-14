package lms.client.asyncservices;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import lms.shared.framework.domain.Category;
import lms.shared.framework.domain.Lookup;

public interface LookupServiceClientAsync {
	
	void getLookupsByCategoryId(long categoryId,AsyncCallback<List<Lookup>>callback);
	
	
	void saveCategory(Category category,AsyncCallback<String> callback);
}
