package com.one_to_one.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.one_to_one.demo.entity.Course;
import com.one_to_one.demo.entity.Instructor;
import com.one_to_one.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

	private static final Logger logger = LoggerFactory.getLogger(CreateInstructorDemo.class);

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
        		.configure("hibernate.cfg.xml")
        		.addAnnotatedClass(Instructor.class)
        		.addAnnotatedClass(InstructorDetail.class)
        		.addAnnotatedClass(Course.class)
        		.buildSessionFactory();
        Session session = factory.getCurrentSession();
        
        try {
        	session.beginTransaction();        	
        	
        	Instructor ins = new Instructor("Susan", "Rodriguez", "s@mail.com");
        	InstructorDetail insDetail = new InstructorDetail(null, "yoooo", "jogging");
        	ins.setInstructorDetail(insDetail);
        	logger.info("Saving instructor => {}", ins);       	
        	session.save(ins);
        	session.getTransaction().commit();
        } finally {
        	factory.close();
        }
	}
}
