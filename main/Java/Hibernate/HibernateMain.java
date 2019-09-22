package Hibernate;

import Hibernate.Model.UserClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;


public class HibernateMain {
	public static void main(String[] args) {

		UserClass userClass1 = new UserClass();
		userClass1.setName("First user class");
		userClass1.setPhotoUrl("http://test.pl/photo.jpg");


		SessionFactory sf=new Configuration()
//				.addPackage("Hibernate")
				.addAnnotatedClass(UserClass.class)
//				.configure()
				.buildSessionFactory();
		Session session=sf.openSession();
		session.beginTransaction();
		session.save(userClass1);
		session.save(userClass1);
		session.getTransaction().commit();
		session.close();
//		sf.close();
	}
}
