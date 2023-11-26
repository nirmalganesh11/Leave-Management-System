package lms.shared.framework.domain;

public class Lookup extends PersistantObject{
	
	private static final long serialVersionUID =1L;
	
	private long lookupId;
	
	private String name;
	
	private long category;
	
	private String description;

	public long getLookupId() {
		return lookupId;
	}

	public void setLookupId(long lookupId) {
		this.lookupId = lookupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCategory() {
		return category;
	}

	public void setCategory(long category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public void detach() {
		super.detach();
	}
	
}
