package com.one_to_one.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.one_to_one.demo.entity.Course;
import com.one_to_one.demo.entity.Instructor;
import com.one_to_one.demo.entity.InstructorDetail;
import com.one_to_one.demo.entity.Review;

public class CreateCourseAndReviewsDemo {

	private static final Logger logger = LoggerFactory.getLogger(CreateCourseAndReviewsDemo.class);

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
        		.configure("hibernate.cfg.xml")
        		.addAnnotatedClass(Instructor.class)
        		.addAnnotatedClass(InstructorDetail.class)
        		.addAnnotatedClass(Course.class)
        		.addAnnotatedClass(Review.class)
        		.buildSessionFactory();
        Session session = factory.getCurrentSession();
        
        try {
        	session.beginTransaction();        	
        	
        	
        	logger.info("Gettin the course");
        	
        	Course course = session.get(Course.class, 16);
        	
        	logger.info("Reviews => {}", course.getReviews());
        	
        	session.getTransaction().commit();
        } finally {
        	session.close();
        	factory.close();
        }
	}
}
