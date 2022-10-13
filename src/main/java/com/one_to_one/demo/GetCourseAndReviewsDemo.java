package com.one_to_one.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.one_to_one.demo.entity.Course;
import com.one_to_one.demo.entity.Instructor;
import com.one_to_one.demo.entity.InstructorDetail;
import com.one_to_one.demo.entity.Review;

public class GetCourseAndReviewsDemo {

	private static final Logger logger = LoggerFactory.getLogger(GetCourseAndReviewsDemo.class);

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
        	logger.info("Creating a course");
        	//create a course
        	Course tempCourse = new Course("SQL 101");
        	//add reviews
        	tempCourse.setReviews(List.of(new Review("Great course"), new Review("Bad course uhhhh"), new Review("I love ittt")));
        	
        	//save the couurse
        	session.save(tempCourse);
        	
        	session.getTransaction().commit();
        } finally {
        	factory.close();
        }
	}
}
