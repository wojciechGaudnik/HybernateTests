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

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class InitTests {

	private static Session session;
	private static SessionFactory sessionFactory;

	@BeforeClass
	private static void beforeClass(){
		Properties properties = new Properties();
		properties.put("hibernate.hbm2ddl.auto", "update");
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
	private static void afterClass(){
		session.close();
		sessionFactory.close();
		cleanDB();
	}

	@Test(groups = "Depended")
	public static void creepy() {
		assertDoesNotThrow(() -> {
			InitEntities.creepy(session);
		});
	}

	@Test(groups = "Depended")
	public static void mentor() {
		assertDoesNotThrow(() -> {
			InitEntities.mentor(session);
		});
	}

	@Test(groups = "Depended")
	public static void userClass() {
		assertDoesNotThrow(() -> {
			InitEntities.userClass(session);
		});
	}

	@Test(groups = "Depended")
	public static void userLevel() {
		assertDoesNotThrow(() -> {
			InitEntities.userLevel(session);
		});
	}

	@Test(groups = "Depended")
	public static void itemCategory() {
		assertDoesNotThrow(() -> {
			InitEntities.itemCategory(session);
		});
	}

	@Test(groups = "Depended")
	public static void questCategory() {
		assertDoesNotThrow(() -> {
			InitEntities.questCategory(session);
		});
	}

	@Test(
			dependsOnGroups = "Depended",
			groups = "testExistIndependent")
	public static void testExistIndependent(){
		closeAllAndOpenAgainUpdate();
		assertEquals(session.get(Creepy.class, 1L).getFirstName(), "Creepy first name");
		assertEquals(session.get(Mentor.class, 2L).getFirstName(), "Mentor name Second");
		assertEquals(session.get(UserClass.class, 3L).getName(), "User Class Third");
		assertEquals(session.get(UserLevel.class, 3L).getName(), "User Level Third");
		assertEquals(session.get(ItemCategory.class, 2L).getName(), "Item Category Second");
		assertEquals(session.get(QuestCategory.class, 1L).getName(), "Quest Category First");
	}

	@Test(
			dependsOnGroups = "testExistIndependent",
			groups = "ForBaskets")
	public static void user(){
		closeAllAndOpenAgainUpdate();
		assertDoesNotThrow(() -> {
			InitEntities.user(session);
		});
	}

	@Test(dependsOnGroups = "ForBaskets")
	static void groupItemBasket() {
		assertDoesNotThrow(() -> {
			InitEntities.groupItemBasket(session);
		});
	}

	@Test(dependsOnGroups = "ForBaskets")
	static void groupQuestBasket() {
		assertDoesNotThrow(() -> {
			InitEntities.groupQuestBasket(session);
		});
	}

	@Test(dependsOnGroups = "testExistIndependent")
	public static void itemCard(){
		assertDoesNotThrow(() -> {
			InitEntities.itemCard(session);
		});
	}

	@Test(dependsOnGroups = "testExistIndependent")
	public static void questCard(){
		assertDoesNotThrow(() -> {
			InitEntities.questCard(session);
		});
	}

	private static void closeAllAndOpenAgainUpdate(){
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

		beforeClass();
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
