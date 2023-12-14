package lms.server.servlet;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import lms.client.asyncservices.LookupServiceClient;
import lms.server.api.AccessControlService;
import lms.server.api.LookupService;
import lms.server.framework.dao.ApplicationContextListener;
import lms.shared.framework.domain.Category;
import lms.shared.framework.domain.Lookup;

public class LookupServiceServlet extends RemoteServiceServlet implements LookupServiceClient{

	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private LookupService lookupService;
	
	
	public LookupServiceServlet(){
		context = ApplicationContextListener.appContext;
		lookupService = context.getBean(LookupService.class);
	}
	

	@Override
	public List<Lookup> getLookupsByCategoryId(long categoryId) {
		return lookupService.getLookupsByCategoryId(categoryId);
	}


	@Override
	public String saveCategory(Category category) {
		return lookupService.saveCategory(category);
	}

}
