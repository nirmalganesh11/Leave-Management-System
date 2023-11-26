package lms.server.dao;

import java.util.List;


import org.hibernate.SessionFactory;

import org.springframework.orm.hibernate5.HibernateTemplate;

import lms.shared.heirarchy.Company;


public class CompanyDaoImpl extends CommonCode {
	
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	
	public CompanyDaoImpl(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}
	
	public String saveCompany(Company company) {
		
		return saveEntity(company,factory);
		
	}

	public List<Company> getAllCompanies(){
			
		return getAllEntities(Company.class,factory,"");
	}
	
}
