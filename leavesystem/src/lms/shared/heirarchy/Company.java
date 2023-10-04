package lms.shared.heirarchy;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Company implements IsSerializable {
	
	private int companyId;
	
	private String companyName;
	
	private String companyDescription;
	
	public Company() {
		
	}
	
	public Company(String companyName,String companyDescription) {
		this.companyName = companyName;
		this.companyDescription = companyDescription;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

}
