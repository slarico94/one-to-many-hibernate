package com.one_to_one.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.one_to_one.demo.entity.Course;
import com.one_to_one.demo.entity.Instructor;
import com.one_to_one.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	private static final Logger logger = LoggerFactory.getLogger(EagerLazyDemo.class);

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
        	
        	logger.info("Starting transaction");
        	
        	Instructor ins = session.get(Instructor.class, 9);
        	//logger.info("Query terminated, INSTRUCTOR => {}", ins);
        	logger.info("Courses => {}", ins.getCourses());
        	session.getTransaction().commit();
        	session.close();
        	logger.info("Courses after session closed => {}", ins.getCourses());
        } finally {
        	session.close();
        	factory.close();
        }
	}
}
