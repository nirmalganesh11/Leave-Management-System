package lms.server.framework.dao;

import lms.shared.utility.Holiday;

public class TemplateRootDaoImpl extends LeaveSystemRootDaoImpl{

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getReferenceClass() {
		return Holiday.class;
	}
	
	public Object get(int id) {
		return get(new Integer(id));
	}
	
	public String saveObjectType(Object obj) {
		saveObject(obj);
		return "Saved";
	}
	
}
