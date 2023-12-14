package lms.server.api;

import java.util.List;

import lms.shared.framework.domain.Category;
import lms.shared.framework.domain.Lookup;

public interface LookupService {
	
	
	List<Lookup> getLookupsByCategoryId(long categoryId);
	
	String saveCategory(Category category);
	
	
}
