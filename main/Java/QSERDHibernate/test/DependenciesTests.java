import QSERDHibernate.Model.Baskets.GroupItemBasket;
import QSERDHibernate.Model.Baskets.GroupQuestBasket;
import QSERDHibernate.Model.Cards.ItemCard;
import QSERDHibernate.Model.Cards.QuestCard;
import QSERDHibernate.Model.Common.ItemCategory;
import QSERDHibernate.Model.Common.QuestCategory;
import QSERDHibernate.Model.Common.UserClass;
import QSERDHibernate.Model.Common.UserLevel;
import QSERDHibernate.Model.Persons.Creepy;
import QSERDHibernate.Model.Persons.Mentor;
import QSERDHibernate.Model.Persons.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.testng.annotations.*;

import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class DependenciesTests {

	private static Session session;
	private static SessionFactory sessionFactory;

	@BeforeMethod
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

	@AfterMethod
	private static void afterMethod(){
		session.close();
		sessionFactory.close();
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

		List questCategoryList = session.createQuery("from quest_categories").list();
		List questCardList = session.createQuery("from quest_cards").list();
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

	@Test
	public static void changeNameQuestCardByQuestCategory(){
		InitEntities.questCategory(session);
		session = closeAfterExceptionAndBegin(sessionFactory, session);
		InitEntities.questCard(session);
		session = closeAfterExceptionAndBegin(sessionFactory, session);

		QuestCategory questCategory1 = session.get(QuestCategory.class, 1L);
		questCategory1.getQuestCard().get(0).setName("New Name");

		commitAndBegin();

		QuestCard questCard1 = session.get(QuestCard.class, 1L);
		assertEquals("New Name", questCard1.getName());
	}

	@Test
	public static void checkHistory(){
		InitEntities.questCategory(session);
		session = closeAfterExceptionAndBegin(sessionFactory, session);
		InitEntities.questCard(session);
		session = closeAfterExceptionAndBegin(sessionFactory, session);

		QuestCategory questCategory2 = session.get(QuestCategory.class, 2L);
		QuestCard questCard1 = session.get(QuestCard.class, 1L);
		questCard1.setQuestCategory(questCategory2);
		commitAndBegin();

		QuestCategory questCategory1 = session.get(QuestCategory.class, 1L);
		questCategory1.setName("New Name1");
		commitAndBegin();
		questCategory1 = session.get(QuestCategory.class, 1L);
		questCategory1.setName("New Name2");
		commitAndBegin();
		questCategory1 = session.get(QuestCategory.class, 1L);
		questCategory1.setName("New Name3");
		commitAndBegin();

		System.out.println("test ---");
		AuditReader auditReader = AuditReaderFactory.get(session);

		QuestCard questCard1Aud = auditReader.find(QuestCard.class, 1L, 2);
		QuestCard questCard2Aud = auditReader.find(QuestCard.class, 1L, 4);

		assertEquals("Quest Category First", questCard1Aud.getQuestCategory().getName());
		assertEquals("Quest Category Second", questCard2Aud.getQuestCategory().getName());
	}

	@Test
	public static void loadAllAndManyToOneAndTestSimpleAndLong(){
		assertDoesNotThrow(DependenciesTests::loadAllDB);
		QuestCard questCard1 = session.get(QuestCard.class, 1L);
		UserLevel userLevel2 = session.get(UserLevel.class, 2L);
		QuestCard finalQuestCard = questCard1;
		assertThrows(NullPointerException.class, () -> {
			finalQuestCard.getUserLevel().getName();
		});
		closeAfterExceptionAndBegin();

		questCard1.setUserLevel(userLevel2);
		session.update(questCard1);
		commitAndBegin();

		questCard1 = session.get(QuestCard.class, 1L);
		userLevel2 = session.get(UserLevel.class, 2L);
		assertEquals("User Level Second", questCard1.getUserLevel().getName());
		assertEquals("Quest Card First", userLevel2.getQuestCardList().get(0).getName());

		QuestCategory questCategory1 = session.get(QuestCategory.class, 1L);
		assertEquals("User Level Second", questCategory1.getQuestCard().get(0).getUserLevel().getName());
	}

	@Test
	public static void loadAllAndManyToOneAndTestLong() {
		assertDoesNotThrow(DependenciesTests::loadAllDB);
		QuestCard questCard1 = session.get(QuestCard.class, 1L);
		UserLevel userLevel2 = session.get(UserLevel.class, 2L);
		QuestCard finalQuestCard = questCard1;
		assertThrows(NullPointerException.class, () -> {
			finalQuestCard.getUserLevel().getName();
		});
		closeAfterExceptionAndBegin();

		questCard1.setUserLevel(userLevel2);
		session.update(questCard1);
		commitAndBegin();

		UserLevel userLevel1 = session.get(UserLevel.class, 1L);

		UserLevel finalUserLevel = userLevel1;
		assertThrows(javax.persistence.PersistenceException.class, () -> { //todo <--- DeleteUserLevel1ExceptionIsOK
			session.delete(finalUserLevel);
			commitAndBegin();
		});
		closeAfterExceptionAndBegin();

		User user1 = session.get(User.class, 1L);
		user1.setUserLevel(userLevel2);
		commitAndBegin();

		userLevel1 = session.get(UserLevel.class, 1L);

		UserLevel finalUserLevel1 = userLevel1;
		assertDoesNotThrow(() -> { //todo <--- DeleteUserLevel1ExceptionWillBeBAD
			session.delete(finalUserLevel1);
			commitAndBegin();
		});

		//todo Delete Quest card First and check if userLevel2 exist OK
		questCard1 = session.get(QuestCard.class, 1L);
		QuestCard finalQuestCard1 = questCard1;
		assertDoesNotThrow(() -> {
			session.delete(finalQuestCard1);
			commitAndBegin();
		});

		userLevel2 = session.get(UserLevel.class, 2L);
		assertEquals("User Level Second", userLevel2.getName());
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
