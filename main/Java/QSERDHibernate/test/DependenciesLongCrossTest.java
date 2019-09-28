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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DependenciesLongCrossTest {
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
	public static void longShotPrepare() {
		assertDoesNotThrow(DependenciesLongCrossTest::loadAllDB);
		User user1 = session.get(User.class, 1L);
		Mentor mentor1 = session.get(Mentor.class, 1L);
		UserClass userClass1 = session.get(UserClass.class, 1L);
		UserLevel userLevel1 = session.get(UserLevel.class, 1L);
		ItemCard itemCard1 = session.get(ItemCard.class, 1L);
		QuestCard questCard1 = session.get(QuestCard.class, 1L);
		ItemCategory itemCategory1 = session.get(ItemCategory.class, 1L);
		QuestCategory questCategory1 = session.get(QuestCategory.class, 1L);
		GroupItemBasket groupItemBasket1 = session.get(GroupItemBasket.class, 1L);
		GroupQuestBasket groupQuestBasket1 = session.get(GroupQuestBasket.class, 1L);

		assertDoesNotThrow(() -> {
			user1.setUserClass(userClass1);
			user1.getItemCards().add(itemCard1);
			user1.getQuestCards().add(questCard1);
			user1.getGroupItemBaskets().add(groupItemBasket1);
			user1.getGroupQuestBaskets().add(groupQuestBasket1);
			user1.getGroupItemBasketsOwned().add(groupItemBasket1);
			user1.getGroupQuestBasketsOwned().add(groupQuestBasket1);
			mentor1.setFirstName("Long Shot to mentor");
			mentor1.getUserClasses().add(userClass1);
			itemCard1.setUserLevel(userLevel1);
			groupQuestBasket1.setQuestCard(questCard1);
			itemCategory1.setName("Long Shot to itemCategory");
			questCategory1.setName("Long Shot to questCategory");
			groupItemBasket1.setItemCard(itemCard1);
			groupItemBasket1.setName("Long Shot to groupItemBasket");
			commitAndBegin();
		});
	}

	@Test(dependsOnGroups = "First")
	public static void longShotFromMentorToItemCategory() {
		Mentor mentor1 = session.get(Mentor.class, 1L);
		assertEquals("Long Shot to itemCategory",
				mentor1
						.getUserClasses().get(0)
						.getUserList().get(0)
						.getUserLevel()
						.getItemCardList().get(0)
						.getItemCategory()
						.getName());
	}

	@Test(dependsOnGroups = "First")
	public static void longShotFromItemCategoryToMentor() {
		ItemCategory itemCategory1 = session.get(ItemCategory.class, 1L);
		assertEquals("Long Shot to mentor",
				itemCategory1
						.getItemCards().get(0)
						.getGroupItemBaskets().get(0)
						.getUsers().get(0)
						.getUserClass()
						.getMentors().get(0)
						.getFirstName());
	}

	@Test(dependsOnGroups = "First")
	public static void longShotFromQuestCategoryToMentor() {
		QuestCategory questCategory1 = session.get(QuestCategory.class, 1L);

		assertEquals("Long Shot to mentor",
				questCategory1
						.getQuestCard().get(0)
						.getGroupQuestBasket().get(0)
						.getUsers().get(0)
						.getUserClass()
						.getMentors().get(0)
						.getFirstName());
	}

	@Test(dependsOnGroups = "First")
	public static void longShotFromMentorToQuestCategoryByOwner() {
		Mentor mentor1 = session.get(Mentor.class, 1L);

		assertEquals("Long Shot to questCategory",
				mentor1
						.getUserClasses().get(0)
						.getUserList().get(0)
						.getGroupQuestBasketsOwned().get(0)
						.getQuestCard()
						.getQuestCategory()
						.getName());
	}

	@Test(dependsOnGroups = "First")
	public static void longShotFromQuestCategoryToGroupItemBasket() {
		QuestCategory questCategory1 = session.get(QuestCategory.class, 1L);

		assertEquals("Long Shot to groupItemBasket",
				questCategory1
						.getQuestCard().get(0)
						.getUsersList().get(0)
						.getGroupItemBaskets().get(0)
						.getName());
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
}
