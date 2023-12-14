package lms.server.servlet;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import lms.client.asyncservices.LeaveRequestServiceClient;
import lms.server.api.LeaveRequestService;
import lms.server.api.LookupService;
import lms.server.framework.dao.ApplicationContextListener;
import lms.shared.framework.domain.Category;
import lms.shared.framework.domain.Lookup;
import lms.shared.utility.LeaveRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LeaveRequestServlet extends RemoteServiceServlet implements LeaveRequestServiceClient{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(LeaveRequestServlet.class);
	
	private ApplicationContext context;
	private LeaveRequestService reqServ;
	private LookupService lookServ;
	
	
	public LeaveRequestServlet(){
		context = ApplicationContextListener.appContext;
		reqServ = context.getBean(LeaveRequestService.class);
		lookServ = context.getBean(LookupService.class);
	}
	
	
	
	@Override
	public String saveLeaveRequest(LeaveRequest lro) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "email-smtp.us-west-2.amazonaws.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("AKIATW7CXGHIOP3IUYJF", "BPFhop6nVFV4fM/Q3iiX3jeWEeOvWbLRWxKeZRnI22ft");
            }
        });

        try {
           
            Message message = new MimeMessage(session);

            
            message.setFrom(new InternetAddress("nyarramaneni@assetsense.com"));

            
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("klucse2000031363@gmail.com"));

            
            message.setSubject("Leave Request Created");

            
            message.setText("A new Request is created");

            
            Transport.send(message);

            logger.info("Message sent Successfully");

        } catch (MessagingException e) {
        	logger.error("Message Not Sent");
            throw new RuntimeException(e);
        }
        
      return reqServ.saveLeaveRequest(lro);
	}

	@Override
	public List<LeaveRequest> getAllLeaveRequests() {
		logger.debug("this is debug message");
		logger.info("This is an informational message.");
	    logger.warn("This is a warning message.");
	    logger.error("This is an error message.");

		return reqServ.getAllRequests();
	}

	@Override
	public int countLeaveDays(Date startDate, Date endDate, List<Date> holidayDates) {
		return reqServ.countLeaveDaysDays(startDate, endDate, holidayDates);
	}

	@Override
	public List<LeaveRequest> getRequestsByUserId(int userId) {
		return reqServ.getLeaveRequestsByUserId(userId);
	}

	@Override
	public String changeRequestStatus(int requestId, String status) {
		
		List<Lookup> lookups  = lookServ.getLookupsByCategoryId(Category.REQUEST_STATUS);
		
		Lookup answerLookup = findLookupByName(lookups, status);
		
		logger.error(answerLookup.getName()+"---------");
		LeaveRequest lr = reqServ.getLeaveRequestByRequestId(requestId);
		
		lr.setLeaveStatus(answerLookup);

		return reqServ.updateLeaveRequest(lr);
		
	}
	
	private Lookup findLookupByName(List<Lookup> lookupList, String targetName) {
        for (Lookup lookup : lookupList) {
            if (lookup.getName().equals(targetName)) {
                return lookup; 
            }
        }
        return null; 
    }



	@Override
	public List<LeaveRequest> getAllPendingRequests() {
		return reqServ.getAllPendingRequests();
	}
	
	@Override
	public List<LeaveRequest> getAllRequestHistory(){
		
		return reqServ.getAllRequestHistory();
	}
	
	

}
