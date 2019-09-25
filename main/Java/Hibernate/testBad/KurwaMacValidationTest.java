//package Hibernate.testBad;
//
//import Hibernate.Model.Baskets.GroupItemBasket;
//import Hibernate.Model.Baskets.GroupQuestBasket;
//import Hibernate.Model.Cards.ItemCard;
//import Hibernate.Model.Cards.QuestCard;
//import Hibernate.Model.Common.ItemCategory;
//import Hibernate.Model.Common.QuestCategory;
//import Hibernate.Model.Common.UserClass;
//import Hibernate.Model.Common.UserLevel;
//import Hibernate.Model.Persons.Creepy;
//import Hibernate.Model.Persons.Mentor;
//import Hibernate.Model.Persons.User;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.testng.annotations.*;
//
//
//import javax.validation.ConstraintViolationException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class KurwaMacValidationTest {
//
//	private static Session session;
//	private static SessionFactory sessionFactory;
//
//	@BeforeMethod
//	public static void beforeMethod(){
//		sessionFactory = new Configuration()
//				.addPackage("Hibernate")
//				.addAnnotatedClass(User.class)
//				.addAnnotatedClass(Creepy.class)
//				.addAnnotatedClass(Mentor.class)
//				.addAnnotatedClass(UserClass.class)
//				.addAnnotatedClass(UserLevel.class)
//				.addAnnotatedClass(ItemCategory.class)
//				.addAnnotatedClass(QuestCategory.class)
//				.addAnnotatedClass(GroupItemBasket.class)
//				.addAnnotatedClass(GroupQuestBasket.class)
//				.addAnnotatedClass(QuestCard.class)
//				.addAnnotatedClass(ItemCard.class)
//				.buildSessionFactory();
//		session = sessionFactory.openSession();
//		session.beginTransaction();
//	}
//
//	@AfterMethod
//	public static void afterMethod(){
//		session.getTransaction().rollback();
//		sessionFactory.close();
//	}
//
//	@Test
//	public void creepy() {
//		Creepy creepy = Creepy
//				.builder()
//				.firstName("Creepy first name")
//				.lastName("Creepy last name")
//				.email("test.dont.work.com")
//				.nick("root")
//				.password("root")
//				.photoUrl("http://to.jest.photo.pl")
//				.build();
//		Creepy finalCreepy = creepy;
//		assertThrows(javax.validation.ConstraintViolationException.class, () -> {
//			session.save(finalCreepy);
//		});
//		creepy = Creepy
//				.builder()
//				.firstName("Cr")
//				.lastName("Creepy last name")
//				.email("test.dont6@work.com")
//				.nick("root")
//				.password("root")
//				.photoUrl("http://to.jest.photo.pl")
//				.build();
//		Creepy finalCreepy1 = creepy;
//		assertThrows(javax.validation.ConstraintViolationException.class, () -> {
//			session.save(finalCreepy1);
//		});
//		creepy = Creepy
//				.builder()
//				.firstName("Creepy first name")
//				.lastName("Creepy last name")
//				.email("test.dont5@work.com")
//				.nick("dd")
//				.password("root")
//				.photoUrl("http://to.jest.photo.pl")
//				.build();
//		Creepy finalCreepy2 = creepy;
//		assertThrows(javax.validation.ConstraintViolationException.class, () -> {
//			session.save(finalCreepy2);
//		});
//		creepy = Creepy
//				.builder()
//				.firstName("     ")
//				.lastName("Creepy last name")
//				.email("test.dont4@work.com")
//				.nick("dd")
//				.password("root")
//				.photoUrl("http://to.jest.photo.pl")
//				.build();
//		Creepy finalCreepy3 = creepy;
//		assertThrows(javax.validation.ConstraintViolationException.class, () -> {
//			session.save(finalCreepy3);
//		});
//		creepy = Creepy
//				.builder()
//				.firstName("")
//				.lastName("Creepy last name")
//				.email("test.dont3@work.com")
//				.nick("root")
//				.password("root")
//				.photoUrl("http://to.jest.photo.pl")
//				.build();
//		Creepy finalCreepy4 = creepy;
//		assertThrows (javax.validation.ConstraintViolationException.class, () -> {
//			session.save(finalCreepy4);
//		});
//		creepy = Creepy
//				.builder()
//				.firstName("Test")
//				.lastName("Creepy last name")
//				.email("test.dont2@work.com")
//				.nick("root")
//				.password("root")
//				.photoUrl("http://to.jest.photo.pl")
//				.build();
//		Creepy finalCreepy5 = creepy;
//		creepy = Creepy
//				.builder()
//				.firstName("Test2")
//				.lastName("Creepy last name")
//				.email("test.dont1@work.com")
//				.nick("root")
//				.password("root")
//				.photoUrl("http://to.jest.photo.pl")
//				.build();
//		Creepy finalCreepy6 = creepy;
//		assertThrows (org.hibernate.exception.ConstraintViolationException.class, () -> {
//			session.save(finalCreepy5);
//			session.save(finalCreepy6);
//		});
//	}
//
//	@Test
//	public void mentor(){
//		Mentor mentor1 = Mentor.builder()
//				.firstName("")
//				.lastName("Mentor last First")
//				.email("mentor1@com.pl")
//				.nick("mentor1")
//				.password("asdfg")
//				.photoUrl("http://mentor.photo1.pl")
//				.build();
//		Mentor finalMentor = mentor1;
//		assertThrows(javax.validation.ConstraintViolationException.class, () -> {
//			session.save(finalMentor);
//		});
//		mentor1 = Mentor.builder()
//				.firstName("Test")
//				.lastName("Mentor last First")
//				.email("mentor1.com.pl")
//				.nick("mentor1")
//				.password("asdfg")
//				.photoUrl("http://mentor.photo1.pl")
//				.build();
//		Mentor finalMentor1 = mentor1;
//		assertThrows(javax.validation.ConstraintViolationException.class, () -> {
//			session.save(finalMentor1);
//		});
//		mentor1 = Mentor.builder()
//				.firstName("Test")
//				.lastName("Mentor last First")
//				.email("mentor1@com.pl")
//				.nick("mentor1")
//				.password("asdfg")
//				.photoUrl("http://mentor.photo1.pl")
//				.build();
//		Mentor mentor2 = Mentor.builder()
//				.firstName("Test")
//				.lastName("Mentor last First")
//				.email("mentor1@com.pl")
//				.nick("mentor1")
//				.password("asdfg")
//				.photoUrl("http://mentor.photo1.pl")
//				.build();
//		session.getTransaction().rollback();
//		session.beginTransaction();
//		session.save(mentor1);
//		assertThrows (org.hibernate.exception.ConstraintViolationException.class, () -> {
//			session.save(mentor2);
//		});
//
//	}
//
//	@Test
//	public void userClass(){
//		UserClass userClass = UserClass
//				.builder()
//				.name("el")
//				.photoUrl("asdfasdfasdf")
//				.build();
//		UserClass userClass1 = userClass;
//		assertThrows(ConstraintViolationException.class, () -> {
//			session.save(userClass1);
//		});
//		userClass = UserClass
//				.builder()
//				.name("asdf")
//				.photoUrl("")
//				.build();
//		UserClass userClass2 = userClass;
//		assertThrows(ConstraintViolationException.class, () -> {
//			session.save(userClass2);
//		});
//		userClass = UserClass
//				.builder()
//				.name("asdf")
//				.photoUrl("asdf")
//				.build();
//		UserClass userClass3 = userClass;
//		userClass = UserClass
//				.builder()
//				.name("asdf")
//				.photoUrl("asdf")
//				.build();
//		UserClass userClass4 = userClass;
//		session.getTransaction().rollback();
//		session.beginTransaction();
//		session.save(userClass3);
//		assertThrows(org.hibernate.exception.ConstraintViolationException.class, () -> {
//			session.save(userClass4);
//		});
//	}
//
//	@Test
//	public void userLevel(){
//		UserLevel userLevel = UserLevel
//				.builder()
//				.name("User Level")
//				.value(0)
//				.build();
//		UserLevel userLevel1 = userLevel;
//		assertThrows(ConstraintViolationException.class, () -> {
//			session.save(userLevel1);
//		});
//		userLevel = UserLevel
//				.builder()
//				.name("User Level1")
//				.value(101)
//				.build();
//		UserLevel userLevel2 = userLevel;
//		assertThrows(ConstraintViolationException.class, () -> {
//			session.save(userLevel2);
//		});
//		userLevel = UserLevel
//				.builder()
//				.name("")
//				.value(99)
//				.build();
//		UserLevel userLevel3 = userLevel;
//		assertThrows(ConstraintViolationException.class, () -> {
//			session.save(userLevel3);
//		});
//		userLevel = UserLevel
//				.builder()
//				.name("asdfgh")
//				.value(90)
//				.build();
//		UserLevel userLevel4 = userLevel;
//		userLevel = UserLevel
//				.builder()
//				.name("asdfgh")
//				.value(90)
//				.build();
//		UserLevel userLevel5 = userLevel;
//		session.getTransaction().rollback();
//		session.beginTransaction();
//		session.save(userLevel4);
//		assertThrows(org.hibernate.exception.ConstraintViolationException.class, () -> {
//			session.save(userLevel5);
//		});
//	}
//
//	@Test
//	public void itemCategory(){
//		ItemCategory itemCategory = ItemCategory
//				.builder()
//				.name("")
//				.build();
//		ItemCategory finalItemCategory = itemCategory;
//		assertThrows(ConstraintViolationException.class, () -> {
//			session.save(finalItemCategory);
//		});
//		itemCategory = ItemCategory
//				.builder()
//				.name("    ")
//				.build();
//		ItemCategory itemCategory2 = itemCategory;
//		assertThrows(ConstraintViolationException.class, () -> {
//			session.save(itemCategory2);
//		});
//		ItemCategory itemCategory3 = ItemCategory
//				.builder()
//				.name("Test")
//				.build();
//		ItemCategory itemCategory4 = ItemCategory
//				.builder()
//				.name("Test")
//				.build();
//		session.getTransaction().rollback();
//		session.beginTransaction();
//		session.save(itemCategory3);
//		assertThrows(org.hibernate.exception.ConstraintViolationException.class, () -> {
//			session.save(itemCategory4);
//		});
//	}
//
//	@Test
//	public void questCategory(){
//		QuestCategory questCategory = QuestCategory
//				.builder()
//				.name("")
//				.build();
//		QuestCategory finalQuestCategory = questCategory;
//		assertThrows(ConstraintViolationException.class, () -> {
//			session.save(finalQuestCategory);
//		});
//		questCategory = QuestCategory
//				.builder()
//				.name("    ")
//				.build();
//		QuestCategory questCategory2 = questCategory;
//		assertThrows(ConstraintViolationException.class, () -> {
//			session.save(questCategory2);
//		});
//		QuestCategory questCategory3 = QuestCategory
//				.builder()
//				.name("Test")
//				.build();
//		QuestCategory questCategory4 = QuestCategory
//				.builder()
//				.name("Test")
//				.build();
//		session.getTransaction().rollback();
//		session.beginTransaction();
//		session.save(questCategory3);
//		assertThrows(org.hibernate.exception.ConstraintViolationException.class, () -> {
//			session.save(questCategory4);
//		});
//	}
//
//	@Test
//	public static void user(){
////		KurwaMac.user(session);
//		session = closeOpenSession(sessionFactory, session);
////		Init.userLevel(session);
//
//		UserLevel userLevel1 = session.get(UserLevel.class, 1L);
//		UserLevel userLevel2 = session.get(UserLevel.class, 2L);
//		assertEquals(userLevel1.getName(), "User Level First");
//		User user1 = User.builder()
//				.firstName("User First")
//				.lastName("Last name First")
//				.email("user1@test.pl")
//				.nick("user nick First")
//				.password("asdf")
//				.photoUrl("http://photo1.com.pl")
//				.userLevel(userLevel1)
//				.build();
//		User user2 = User.builder()
//				.firstName("User Second")
//				.lastName("Last name Second")
//				.email("user2@test.pl")
//				.nick("user nick Second")
//				.password("asdff")
//				.photoUrl("http://photo2.com.pl")
//				.userLevel(userLevel1)
//				.build();
//		User user3 = User.builder()
//				.firstName("User Third")
//				.lastName("Last name Third")
//				.email("user3@test.pl")
//				.nick("user nick Third")
//				.password("asdf")
//				.photoUrl("http://photo3.com.pl")
//				.userLevel(userLevel1)
//				.build();
//
//		session.save(user1);
//		session.save(user2);
//		session.save(user3);
//
//		session = closeOpenSession(sessionFactory, session);
//		User userTest1 = session.get(User.class, 1L);
//		User userTest2 = session.get(User.class, 2L);
//		assertEquals(userTest1.getUserLevel().getName(), "User Level First");
//		assertDoesNotThrow(() -> {
//			userTest1.setUserLevel(userLevel2);
//		});
//		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
//
//
//
//
//
////		System.out.println(userLevel1.getUsersList());
////		session = closeOpenSession(sessionFactory, session);
////		userTest2 = session.get(User.class, 2L);
////		System.out.println(userTest2.getUserLevel().getName());
////		assertThrows(PersistenceException.class, () -> {
////			session.delete(userLevel1);
////		});
////		session = closeOpenSession(sessionFactory, session);
////		userTest2 = session.get(User.class, 2L);
////		System.out.println(userTest2.getUserLevel().getName());
////		session.delete(userLevel1);
////		assertEquals(userTest.getUserLevel().getName(), "User Level First");
//
//	}
//
////	@Test
////	public void groupItemBasket(){
////		Init.creepy(session);
////		Init.mentor(session);
////		Init.userClass(session);
////		Init.userLevel(session);
////		Init.itemCategory(session);
////		Init.questCategory(session);
////		GroupItemBasket groupItemBasket = GroupItemBasket
////				.builder()
////				.name("Group Item Basket ")
////
////
////	}
//
//	private static Session closeOpenSession(SessionFactory sessionFactory, Session session) {
//		session.getTransaction().commit();
//		session.close();
//		session = sessionFactory.openSession();
//		session.beginTransaction();
//		return session;
//	}
//}