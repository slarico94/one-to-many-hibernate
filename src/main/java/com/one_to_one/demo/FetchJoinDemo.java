package com.one_to_one.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.one_to_one.demo.entity.Course;
import com.one_to_one.demo.entity.Instructor;
import com.one_to_one.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	private static final Logger logger = LoggerFactory.getLogger(FetchJoinDemo.class);

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
        	
        	logger.info("Calling hibernate query with HQL!!!");
        	
        	Query<Instructor> query = session.createQuery("SELECT i FROM Instructor i "
        			+ "JOIN FETCH i.courses "
        			+ "WHERE i.instructorId = :insId", Instructor.class);
        	query.setParameter("insId", 9);
        	        	
        	Instructor ins = query.getSingleResult();
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
