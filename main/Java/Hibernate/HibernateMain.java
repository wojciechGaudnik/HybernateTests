package Hibernate;

import Hibernate.Model.Baskets.GroupItemBasket;
import Hibernate.Model.Baskets.GroupQuestBasket;
import Hibernate.Model.Cards.ItemCard;
import Hibernate.Model.Cards.QuestCard;
import Hibernate.Model.Common.ItemCategory;
import Hibernate.Model.Common.QuestCategory;
import Hibernate.Model.Common.UserClass;
import Hibernate.Model.Common.UserLevel;
import Hibernate.Model.Persons.Creepy;
import Hibernate.Model.Persons.Mentor;
import Hibernate.Model.Persons.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;



//"org.hibernate.envers.audit_table_suffix", "_AUDIT_LOG");
//		sessionFactory.setHibernateProperties(hibernateProperties);
//todo @ToString @ equals and hascode
//todo ASK MENTOR how make interface with hibernate users,mentors,creepy https://www.baeldung.com/hibernate-inheritance
//todo change EAGER into Hibernate.initialize(object proxy);
//todo serializable ?? why here ?
//todo	@Retention(RUNTIME)   //todo <--- check if help with catch errors !!!
//todo correct all validation to Column(bleble) for One Exception
//todo If you worry about the lack of a predefined entry order, then you can use either the @OrderBy or @OrderColumn JPA annotations.
//todo @Temporal(TemporalType.TIMESTAMP)

public class HibernateMain {

	private static SessionFactory sessionFactory;
	private static Session session;

	public static void main(String[] args) {

//		Properties properties = new Properties();
//		properties.put("hibernate.hbm2ddl.auto", "update");
		sessionFactory = new Configuration()
				.addPackage("Hibernate")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Creepy.class)
				.addAnnotatedClass(Mentor.class)
				.addAnnotatedClass(UserClass.class)
				.addAnnotatedClass(UserLevel.class)
				.addAnnotatedClass(ItemCategory.class)
				.addAnnotatedClass(QuestCategory.class)
				.addAnnotatedClass(GroupItemBasket.class)
				.addAnnotatedClass(GroupQuestBasket.class)
				.addAnnotatedClass(QuestCard.class)
				.addAnnotatedClass(ItemCard.class)
//				.addProperties(properties)
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();


		System.out.println("Init -------------------------------------------------------------------------------------");

		System.out.println("First -------------------------------------------------------------------------------------");

		System.out.println("Second -------------------------------------------------------------------------------------");

		System.out.println("Stop -------------------------------------------------------------------------------------");

		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}




	private static Session closeOpenSession(SessionFactory sessionFactory, Session session) {
		session.getTransaction().commit();
		session.close();
		session = sessionFactory.openSession();
		session.beginTransaction();
		return session;
	}

	private static void cleanDB() {
		Properties properties = new Properties();
		properties.put("hibernate.hbm2ddl.auto", "create-drop");
		sessionFactory = new Configuration()
				.addPackage("Hibernate")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Creepy.class)
				.addAnnotatedClass(Mentor.class)
				.addAnnotatedClass(UserClass.class)
				.addAnnotatedClass(UserLevel.class)
				.addAnnotatedClass(ItemCategory.class)
				.addAnnotatedClass(QuestCategory.class)
				.addAnnotatedClass(GroupItemBasket.class)
				.addAnnotatedClass(GroupQuestBasket.class)
				.addAnnotatedClass(QuestCard.class)
				.addAnnotatedClass(ItemCard.class)
				.addProperties(properties)
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
	private static void rollBackAndBegin(){
		session.getTransaction().rollback();
		session.beginTransaction();
	}

	public static void commitAndBegin(){
		session.getTransaction().commit();
		session.close();
		session = sessionFactory.openSession();
		session.beginTransaction();
	}
}
