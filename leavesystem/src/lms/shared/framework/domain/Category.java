package lms.shared.framework.domain;

import java.util.List;

@SuppressWarnings("unused")
public class Category extends PersistantObject {
	
	
	private static final long serialVersionUID = 1L;
	
	
	private static final long  REQUEST_STATUS =31;
	
	private static final long  LEAVE_TIME = 41;
	
	
	private String name;
	
	private List<Lookup> lookups;
	
	@Override
	public void detach() {
		super.detach();
		lookups = detachList(lookups);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Lookup> getLookups() {
		return lookups;
	}

	public void setLookups(List<Lookup> lookups) {
		this.lookups = lookups;
	}
	

}
