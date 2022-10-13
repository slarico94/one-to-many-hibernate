package com.one_to_one.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.one_to_one.demo.entity.Instructor;
import com.one_to_one.demo.entity.InstructorDetail;

public class CreateDemo {

	private static final Logger logger = LoggerFactory.getLogger(CreateDemo.class);

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
        		.configure("hibernate.cfg.xml")
        		.addAnnotatedClass(Instructor.class)
        		.addAnnotatedClass(InstructorDetail.class)
        		.buildSessionFactory();
        Session session = factory.getCurrentSession();
        
        try {
        	session.beginTransaction();        	
        	
        	int instructorId = 2;
        	Instructor ins = session.get(Instructor.class, instructorId);
        	logger.info("Instructor to delete: {}", ins);
        	
        	if (ins != null) {
        		logger.info("Deleting instructor id: ", instructorId);
        		session.delete(ins);
        	}
        	
        	
        	session.getTransaction().commit();
        } finally {
        	factory.close();
        }
	}
}
