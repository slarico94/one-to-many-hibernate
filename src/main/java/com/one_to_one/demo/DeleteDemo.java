package com.one_to_one.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.one_to_one.demo.entity.Instructor;
import com.one_to_one.demo.entity.InstructorDetail;

public class DeleteDemo {

	private static final Logger logger = LoggerFactory.getLogger(DeleteDemo.class);

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
        		.configure("hibernate.cfg.xml")
        		.addAnnotatedClass(Instructor.class)
        		.addAnnotatedClass(InstructorDetail.class)
        		.buildSessionFactory();
        Session session = factory.getCurrentSession();
        
        try {
        	session.beginTransaction();        	
        	Instructor tempInstructor = new Instructor("Samuel", "Rodolfo", "caleb@mail.com");
        	InstructorDetail tempInstructorDetail = new InstructorDetail(null, "youutube/caleb", "Eating");
        	//session.save(tempInstructorDetail);        	
        	tempInstructor.setInstructorDetail(tempInstructorDetail);
        	
        	//this will also save the details object because of CascadeType.ALL
        	logger.info("Saving instructor: {}", tempInstructor);
        	session.persist(tempInstructor);
        	logger.info("Instructor after saved: {}", tempInstructor);
        	
        	
        	session.getTransaction().commit();
        } finally {
        	factory.close();
        }
	}
}
