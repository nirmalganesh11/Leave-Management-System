package lms.shared.utility;

import lms.shared.Employee;
import lms.shared.framework.domain.PersistantObject;
import lms.shared.security.Role;

public class Message extends PersistantObject{

	private static final long serialVersionUID = 1L;
	
	private long messageId;
	
	private String subject;
	
	private String description;
	
	private Role role;
	
	private Employee employee;
	
	public Message() {
		
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public void detach() {
		super.detach();
		this.role.detach();
	}

}
