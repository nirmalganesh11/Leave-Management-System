package lms.server.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import lms.shared.utility.Message;

public class MessageDaoImpl extends CommonCode{

	public SessionFactory factory;
	private HibernateTemplate template;
	
	
	public MessageDaoImpl(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		
		this.factory = sessionFactory;
	}
	
	
	public String saveMessage(Message msg) {
		saveEntity(msg,factory);
		return "saved";
	}

	
	public List<Message> getHrMessages() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Message> getStaffMessages() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
