package lms.server.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import lms.server.api.LookupService;
import lms.server.dao.LeaveTypeDaoImpl;
import lms.server.dao.LookupDaoImpl;
import lms.shared.framework.domain.Category;
import lms.shared.framework.domain.Lookup;

public class LookupServiceImpl implements LookupService {
	
	

    private static final Logger logger = LogManager.getLogger(LookupServiceImpl.class);

    private LookupDaoImpl dao;

    public	LookupServiceImpl(LookupDaoImpl dao) {
        this.dao = dao;
    }

	@Override
	public List<Lookup> getLookupsByCategoryId(long categoryId) {
		return dao.getLookupsByCategoryId(categoryId);
	}

	@Override
	public String saveCategory(Category category) {
		return dao.saveCategory(category);
	}

}
