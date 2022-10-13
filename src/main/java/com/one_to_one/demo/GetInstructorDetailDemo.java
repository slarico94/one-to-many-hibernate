package com.one_to_one.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.one_to_one.demo.entity.Instructor;
import com.one_to_one.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	private static final Logger logger = LoggerFactory.getLogger(GetInstructorDetailDemo.class);

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
        		.configure("hibernate.cfg.xml")
        		.addAnnotatedClass(Instructor.class)
        		.addAnnotatedClass(InstructorDetail.class)
        		.buildSessionFactory();
        Session session = factory.getCurrentSession();
        
        try {
        	session.beginTransaction();        	
        	InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, 88);
        	logger.info("Temp instructor detail => {}", tempInstructorDetail);
        	logger.info("Associated instructor => {}", tempInstructorDetail.getInstructor());
        	session.getTransaction().commit();
        	logger.info("Done");
        } catch(Exception ex) {
        	logger.error("Error!", ex);
        } finally {
        	session.close();
        	factory.close();
        }
	}
}
