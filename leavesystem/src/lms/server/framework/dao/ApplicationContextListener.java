package lms.server.framework.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent>{
	
	public static ApplicationContext appContext;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		appContext  = event.getApplicationContext();
		//appContext = new ClassPathXmlApplicationContext("services.xml");
	}

}
