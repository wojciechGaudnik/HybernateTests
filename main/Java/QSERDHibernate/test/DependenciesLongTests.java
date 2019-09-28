package QSERDHibernate.test;

import QSERDHibernate.model.baskets.GroupItemBasket;
import QSERDHibernate.model.baskets.GroupQuestBasket;
import QSERDHibernate.model.cards.ItemCard;
import QSERDHibernate.model.cards.QuestCard;
import QSERDHibernate.model.common.ItemCategory;
import QSERDHibernate.model.common.QuestCategory;
import QSERDHibernate.model.common.UserClass;
import QSERDHibernate.model.common.UserLevel;
import QSERDHibernate.model.persons.Creepy;
import QSERDHibernate.model.persons.Mentor;
import QSERDHibernate.model.persons.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.*;

import java.util.Properties;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class DependenciesLongTests {

	private static Session session;
	private static SessionFactory sessionFactory;

	@BeforeClass
	private static void beforeMethod(){
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
	}

	@AfterClass
	private static void afterMethod(){
		session.close();
		sessionFactory.close();
	}

	@Test(groups = "First")
	public static void ifDependencyManyToManyFirst(){
		assertDoesNotThrow(DependenciesLongTests::loadAllDB);
		Mentor mentor1 = session.get(Mentor.class, 1L);
		Mentor mentor2 = session.get(Mentor.class, 2L);
		Mentor mentor3 = session.get(Mentor.class, 3L);
		UserClass userClass1 = session.get(UserClass.class, 1L);
		UserClass userClass2 = session.get(UserClass.class, 2L);
		final UserClass[] userClass3 = {session.get(UserClass.class, 3L)};

		//todo mentor1 set userClass1 and userClass2
		mentor1.getUserClasses().add(userClass1);
		mentor1.getUserClasses().add(userClass2);
		//todo mentor2 set userClass1 and userClass2
		mentor2.getUserClasses().add(userClass1);
		mentor2.getUserClasses().add(userClass2);
		//todo mentor3 set userClass3
		mentor3.getUserClasses().add(userClass3[0]);
		commitAndBegin();

		//todo check userClass1 list of two mentors mentor1 and mentor2
		userClass1 = session.get(UserClass.class, 1L);
		assertEquals(2, userClass1.getMentors().size());
		//todo userClass3 add mentor1
		userClass3[0] = session.get(UserClass.class, 3L);
		userClass3[0].getMentors().add(mentor1);
		commitAndBegin();

		//todo check mentor1 if userCLass3 exist
		mentor1 = session.get(Mentor.class, 1L);
		assertEquals("User Class Third", mentor1.getUserClasses().get(2).getName());

	}

	@Test(dependsOnGroups = "First",
			groups = "Second")
	public static void ifDependencyManyToManySecond(){
		final UserClass[] userClass3 = {session.get(UserClass.class, 3L)};
		//todo delete userClass3 OK
		assertDoesNotThrow(() -> {
			userClass3[0] = session.get(UserClass.class, 3L);
			session.delete(userClass3[0]);
			commitAndBegin();
		});
	}

	@Test(dependsOnGroups = "Second",
			groups = "Third")
	public static void ifDependencyManyToManyThird() {
		Mentor mentor1;
		UserClass userClass1;
		//todo set mentor1 second userClass1 Error
		mentor1 = session.get(Mentor.class, 1L);
		userClass1 = session.get(UserClass.class, 1L);
		assertEquals("User Class First", mentor1.getUserClasses().get(0).getName());
		assertEquals("User Class Second", mentor1.getUserClasses().get(1).getName());
		mentor1.getUserClasses().add(userClass1);
		commitAndBegin();
	}

	@Test(dependsOnGroups = "Third",
			groups = "Fourth")
	public static void ifDependencyManyToManyFourth() {
		Mentor mentor1;
		UserClass userClass1;

		mentor1 = session.get(Mentor.class, 1L);
		userClass1 = session.get(UserClass.class, 1L);
		assertEquals("User Class First - User Class First - User Class Second", mentor1.getUserClasses()
				.stream()
				.map(UserClass::getName)
				.collect(Collectors.joining(" - ")));
		mentor1.getUserClasses().remove(userClass1);
		commitAndBegin();
	}

	@Test(dependsOnGroups = "Fourth",
			groups = "Fifth")
	public static void ifDependencyManyToManyFifth() {
		Mentor mentor1;

		mentor1 = session.get(Mentor.class, 1L);
		assertEquals("User Class First - User Class Second", mentor1.getUserClasses()
				.stream()
				.map(UserClass::getName)
				.collect(Collectors.joining(" - ")));
		commitAndBegin();
	}

	@Test(dependsOnGroups = "Fifth",
			groups = "Sixth")
	public static void ifDependencyManyToManySixth() {
		Mentor mentor1;
		Mentor mentor2;
		Mentor mentor3;

		mentor1 = session.get(Mentor.class, 1L);
		mentor2 = session.get(Mentor.class, 2L);
		mentor3 = session.get(Mentor.class, 3L);

		assertEquals("User Class First - User Class Second", mentor1.getUserClasses()
				.stream()
				.map(UserClass::getName)
				.collect(Collectors.joining(" - ")));
		assertEquals("User Class First - User Class Second", mentor2.getUserClasses()
				.stream()
				.map(UserClass::getName)
				.collect(Collectors.joining(" - ")));
		assertEquals("", mentor3.getUserClasses()
				.stream()
				.map(UserClass::getName)
				.collect(Collectors.joining(" - ")));
	}

	@Test(dependsOnGroups = "Fifth",
			groups = "Sixth")
	public static void manyToManyMentorsAndUserClasses() {
		commitAndBegin();
		beforeMethod();
		assertDoesNotThrow(DependenciesLongTests::loadAllDB);
		Mentor mentor1 = session.get(Mentor.class, 1L);
		UserClass userClass1 = session.get(UserClass.class, 1L);
		UserClass userClass2 = session.get(UserClass.class, 2L);

		mentor1.getUserClasses().add(userClass1);
		mentor1.getUserClasses().add(userClass2);
		commitAndBegin();

		mentor1 = session.get(Mentor.class, 1L);
		userClass1 = session.get(UserClass.class, 1L);
		userClass2 = session.get(UserClass.class, 2L);

		assertEquals("Mentor name First", userClass1.getMentors().get(0).getFirstName());
		assertEquals("Mentor name First", userClass2.getMentors().get(0).getFirstName());
		assertEquals("User Class First - User Class Second", mentor1.getUserClasses().stream().map(UserClass::getName).collect(Collectors.joining(" - ")));
	}

	private static void loadAllDB() {
		InitEntities.creepy(session);
		InitEntities.mentor(session);
		InitEntities.userClass(session);
		InitEntities.userLevel(session);
		InitEntities.itemCategory(session);
		InitEntities.questCategory(session);
		InitEntities.user(session);
		InitEntities.groupItemBasket(session);
		InitEntities.groupQuestBasket(session);
		InitEntities.itemCard(session);
		InitEntities.questCard(session);
		commitAndBegin();
	}

	private static void commitAndBegin(){
		session.getTransaction().commit();
		session.close();
		session = sessionFactory.openSession();
		session.beginTransaction();
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
}
