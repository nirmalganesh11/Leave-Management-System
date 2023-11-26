package lms.shared.framework.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersistantObject implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private long id=0;
	
	public PersistantObject() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void detach() {
		
	}
	
	@Override
	public boolean equals(Object compare) {
		
		if(compare == null || !(compare instanceof PersistantObject)) {
			return false;
		}
		
		return id == ((PersistantObject)compare).id;
		
	}
	
	public static <T> List<T> detachList(List<T> values) {
		
		if(values == null) {
			return values;
		}
		List<T> detached  = new ArrayList<T>(values);
		
		detachCollection(detached);
		
		return detached;
	}
	
	public static <T> void detachCollection(Collection<T> values) {
		
		if(values == null) {
			return;
		}
		for(Object value: values) {
			detach(value);	
		}
	}
	
	public static <T> Object detach(Object value) {
		if(value == null) {
			return null;
		}
		if(value instanceof PersistantObject) {
			((PersistantObject)value).detach();
			return value;
		}
		if(value instanceof List) {
			return detachList((List<T>)value);
		}
		else {
			return value;
		}
	}

}
