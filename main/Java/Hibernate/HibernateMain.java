package Hibernate;

import Hibernate.Model.Common.UserClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateMain {
	public static void main(String[] args) {

		SessionFactory sf=new Configuration()
//				.addPackage("Hibernate")
				.addAnnotatedClass(UserClass.class)
//				.configure()
				.buildSessionFactory();
		Session session=sf.openSession();
		session.beginTransaction();

		initUserClass(session);






//		updateUserClass(session);

		session.getTransaction().commit();
		session.close();
		sf.close();
	}

	private static void updateUserClass(Session session) {
		UserClass userClass = session.get(UserClass.class, 1L);
		userClass.setName("New name3");
		session.merge(userClass);
	}

	private static void initUserClass(Session session) {
		UserClass userClass1 = new UserClass();
		userClass1.setName("First user class");
		userClass1.setPhotoUrl("http://test.pl/photo1.jpg");

		UserClass userClass2 = new UserClass();
		userClass2.setName("Second user class");
		userClass2.setPhotoUrl("http://test.pl/photo2.jpg");

		session.save(userClass1);
		session.save(userClass2);
	}
}
