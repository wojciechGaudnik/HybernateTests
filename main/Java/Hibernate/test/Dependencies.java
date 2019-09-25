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
import org.testng.annotations.*;

import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class Dependencies {

	private static Session session;
	private static SessionFactory sessionFactory;

	@BeforeMethod
	private static void beforeMethod(){
//		Properties properties = new Properties();
//		properties.put("hibernate.hbm2ddl.auto", "create-drop");
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
	}

	@AfterMethod
	private static void afterMethod(){
		session.close();
		sessionFactory.close();

//		cleanDB();
	}

	@Test
	public static void initAll() {
		assertDoesNotThrow(() -> {
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
		});
	}

	@Test
	public static void changeQuestCardCategory(){
		InitEntities.questCategory(session);
		session = closeAfterExceptionAndBegin(sessionFactory, session);
		InitEntities.questCard(session);
		session = closeAfterExceptionAndBegin(sessionFactory, session);

		QuestCategory questCategory2 = session.get(QuestCategory.class, 2L);
		QuestCard questCard1 = session.get(QuestCard.class, 1L);

		questCard1.setQuestCategory(questCategory2);
		session = closeAfterExceptionAndBegin(sessionFactory, session);
		QuestCategory questCategory2Alertest = session.get(QuestCategory.class, 2L);

		List<QuestCard> questCardList = questCategory2Alertest.getQuestCard();

		StringBuilder test = new StringBuilder();
		for (QuestCard questCard : questCardList) {
			test.append(questCard.getName());
		}
		String ans = "Quest Card SecondQuest Card First";
		assertEquals(test.toString(), ans);
	}

	@Test
	public static void deleteQuestCategoryIfCardExistAndIfNotExist(){
		InitEntities.questCategory(session);
		session = closeAfterExceptionAndBegin(sessionFactory, session);
		InitEntities.questCard(session);
		session = closeAfterExceptionAndBegin(sessionFactory, session);

		QuestCategory questCategory1 = session.get(QuestCategory.class, 1L);
		QuestCategory questCategory2 = session.get(QuestCategory.class, 2L);

		QuestCategory finalQuestCategory = questCategory1;
		assertThrows(javax.persistence.PersistenceException.class, () -> {
			session.delete(finalQuestCategory);
			session = closeAfterExceptionAndBegin(sessionFactory, session);
		});
		closeAfterExceptionAndBegin();

		QuestCard questCard1 = session.get(QuestCard.class, 1L);
		questCard1.setQuestCategory(questCategory2);
		questCategory1 = session.get(QuestCategory.class, 1L);
		QuestCategory finalQuestCategory1 = questCategory1;
		assertDoesNotThrow(() -> {
			session.delete(finalQuestCategory1);
			commitAndBegin();
		});
		closeAfterExceptionAndBegin();
	}

	@Test
	public static void updateQuestCategoryAndCheckQuestCard(){
		InitEntities.questCategory(session);
		session = closeAfterExceptionAndBegin(sessionFactory, session);
		InitEntities.questCard(session);
		session = closeAfterExceptionAndBegin(sessionFactory, session);

		QuestCategory questCategory1 = session.get(QuestCategory.class, 1L);
		questCategory1.setName("Test Name");
		commitAndBegin();
		questCategory1 = session.get(QuestCategory.class, 1L);
		QuestCard questCard1 = session.get(QuestCard.class, 1L);
		assertEquals("Test Name", questCategory1.getName());
		assertEquals("Test Name", questCard1.getQuestCategory().getName());
	}

	@Test
	public static void deleteQuestCardAndCheckIfCategoryExist(){
		InitEntities.questCategory(session);
		session = closeAfterExceptionAndBegin(sessionFactory, session);
		InitEntities.questCard(session);
		session = closeAfterExceptionAndBegin(sessionFactory, session);

		QuestCard questCard1 = session.get(QuestCard.class, 1L);
		session.delete(questCard1);
		commitAndBegin();

		List<QuestCategory> questCategoryList = session.createQuery("from quest_categories").list();
		List<QuestCard> questCardList = session.createQuery("from quest_cards").list();
		assertEquals(3, questCategoryList.size());
		assertEquals(2, questCardList.size());

	}

	@Test
	public static void changeNameCategoryByQuestCard(){
		InitEntities.questCategory(session);
		session = closeAfterExceptionAndBegin(sessionFactory, session);
		InitEntities.questCard(session);
		session = closeAfterExceptionAndBegin(sessionFactory, session);

		QuestCard questCard1 = session.get(QuestCard.class, 1L);
		questCard1.getQuestCategory().setName("New name");
		commitAndBegin();

		QuestCategory questCategory1 = session.get(QuestCategory.class, 1L);
		assertEquals("New name", questCategory1.getName());
	}

//	@Test(dependsOnGroups = "Init")
	public static void ifDependencyManyToManyDelete(){
		System.out.println("test ----------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

//	@Test(dependsOnGroups = "Init")
	public static void ifDependencyManyToManyUpdate(){
		System.out.println("test ----------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

//	@Test(dependsOnGroups = "Init")
	public static void ifDependencyOneToManyWrite(){
		System.out.println("test ----------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

//	@Test(dependsOnGroups = "Init")
	public static void ifDependencyOneToManyDelete(){
		System.out.println("test ----------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

//	@Test(dependsOnGroups = "Init")
	public static void ifDependencyOneToManyUpdate(){
		System.out.println("test ----------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

//	@Test(dependsOnGroups = "Init")
	public static void simulateAllSingleBuy(){
		System.out.println("test ----------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

//	@Test(dependsOnGroups = "Init")
	public static void simulateAllGroupBuy(){
		System.out.println("test ----------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

//	private static void closeAllAndOpenAgainUpdate(){
//		session.getTransaction().commit();
//		session.close();
//		sessionFactory.close();
//
//		beforeClass();
//	}

	private static void rollBackAndBegin(){
		session.getTransaction().rollback();
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

	private static void commitAndBegin(){
		session.getTransaction().commit();
		session.close();
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	private static Session closeAfterExceptionAndBegin(SessionFactory sessionFactory, Session session) {
		session.getTransaction().commit();
		session.close();
		session = sessionFactory.openSession();
		session.beginTransaction();
		return session;
	}

	private static void closeAfterExceptionAndBegin() {
		session.close();
		session = sessionFactory.openSession();
		session.beginTransaction();
	}
}
