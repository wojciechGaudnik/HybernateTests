package Hibernate;

import Hibernate.Model.Baskets.GroupItemBasket;
import Hibernate.Model.Common.ItemCategory;
import Hibernate.Model.Common.QuestCategory;
import Hibernate.Model.Common.UserClass;
import Hibernate.Model.Common.UserLevel;
import Hibernate.Model.Persons.Mentor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Set;


public class HibernateMain {
	public static void main(String[] args) {

		SessionFactory sf=new Configuration()
//				.addPackage("Hibernate")
				.addAnnotatedClass(UserClass.class)
				.addAnnotatedClass(UserLevel.class)
				.addAnnotatedClass(Mentor.class)
				.addAnnotatedClass(ItemCategory.class)
				.addAnnotatedClass(QuestCategory.class)
				.addAnnotatedClass(GroupItemBasket.class)
//				.configure()
				.buildSessionFactory();
		Session session=sf.openSession();
		session.beginTransaction();

		initUserClass(session);
		initUserLevel(session);
		initItemCategory(session);
		initQuestCategory(session);








		UserClass userClass1 = session.get(UserClass.class, 1L);
		UserClass userClass2 = session.get(UserClass.class, 2L);
		Mentor mentor = Mentor
				.builder()
				.firstName("Adam")
				.lastName("Stamirowski")
				.email("ala@ma.kota.pl")
				.nick("dot")
				.password("root")
				.photoUrl("http://photo.mentor1.com")
				.userClasses(Set.of(userClass1, userClass2))
				.build();
		session.save(mentor);
		userClass1.getMentors().add(mentor);
		session.merge(userClass1);



//		updateUserClass(session);

		session.getTransaction().commit();
		session.close();
		sf.close();
	}

	private static void initUserLevel(Session session) {
		UserLevel userLevel1 = new UserLevel();
		userLevel1.setName("First User Level");

		UserLevel userLevel2 = new UserLevel();
		userLevel2.setName("Second User Level");

		session.save(userLevel1);
		session.save(userLevel2);
	}

	private static void initQuestCategory(Session session) {
		QuestCategory questCategory1 = new QuestCategory();
		questCategory1.setName("First Quest Category");

		QuestCategory questCategory2 = new QuestCategory();
		questCategory2.setName("Second Quest Category");

		session.save(questCategory1);
		session.save(questCategory2);
	}

	private static void updateUserClass(Session session) {
		UserClass userClass = session.get(UserClass.class, 1L);
		userClass.setName("New name3");
		session.merge(userClass);
	}

	private static void initItemCategory(Session session) {
		ItemCategory itemCategory1 = new ItemCategory();
		itemCategory1.setName("First item category");

		ItemCategory itemCategory2 = new ItemCategory();
		itemCategory2.setName("Second item category");

		session.save(itemCategory1);
		session.save(itemCategory2);
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
