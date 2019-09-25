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


//		AuditReader auditReader = AuditReaderFactory.get(session);
//				Creepy oldCreepy = auditReader.find(Creepy.class, 1L, 1);
//		System.out.println(oldCreepy);

//todo @ToString @ wquals and hascode
//todo ASK MENTOR how make interface with hibernate users,mentors,creepy https://www.baeldung.com/hibernate-inheritance
//todo one more time email validation !!!
//todo What if make One Class person and all users will be inherit ??
//todo change EAGER into Hibernate.initialize(object proxy);
//todo serializable ?? why here ?
//todo	@Retention(RUNTIME)   //todo <--- check if help with catch errors !!!

//@NotEmpty – validates that the property is not null or empty; can be applied to String, Collection, Map or Array values
//@NotBlank – can be applied only to text values and validated that the property is not null or whitespace
//@Positive and @PositiveOrZero – apply to numeric values and validate that they are strictly positive, or positive including 0
//@Negative and @NegativeOrZero – apply to numeric values and validate that they are strictly negative, or negative including 0
//@Past and @PastOrPresent – validate that a date value is in the past or the past including the present; can be applied to date types including those added in Java 8
//@Future and @FutureOrPresent – validates that a date value is in the future, or in the future including the present
//@NotNull(message = "Name cannot be null")
//@AssertTrue
//private boolean working;
//@Size(min = 10, max = 200, message
//		= "About Me must be between 10 and 200 characters")
//@Min(value = 18, message = "Age should not be less than 18")
//@Max(value = 150, message = "Age should not be greater than 150")
//@Email(message = "Email should be valid")
//If you worry about the lack of a predefined entry order, then you can use either the @OrderBy or @OrderColumn JPA annotations.
//@Temporal(TemporalType.TIMESTAMP)

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
		InitEntities.questCategory(session);
		session = closeOpenSession(sessionFactory, session);
		InitEntities.questCard(session);
		session = closeOpenSession(sessionFactory, session);

		System.out.println("First -------------------------------------------------------------------------------------");
		QuestCategory questCategory2 = session.get(QuestCategory.class, 2L);
		QuestCard questCard1 = session.get(QuestCard.class, 1L);

		questCard1.setQuestCategory(questCategory2);

//		session.save(questCard1);
//		session.update(questCategory2);

		session = closeOpenSession(sessionFactory, session);
//		getCommitBegin();

		QuestCategory questCategory2Alertest = session.get(QuestCategory.class, 2L);


		questCategory2Alertest.getQuestCard().forEach(c -> System.out.println(c.getName()));




		System.out.println("Second -------------------------------------------------------------------------------------");
//		questCategory2.getQuestCard().forEach(c -> System.out.println(c.getName()));
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
	private static void getCommitBegin (){
		session.getTransaction().commit();
		session.beginTransaction();
	}
}
