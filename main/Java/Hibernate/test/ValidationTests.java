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

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

//@Test(priority = 9)
public class ValidationTests {

	private static Session session;
	private static SessionFactory sessionFactory;

	@BeforeClass(dependsOnGroups = "InitTestsStop")
	private static void beforeClass(){
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
				.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	@BeforeMethod
	private static void beforeMethod(){
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	@AfterMethod
	private static void afterMethod(){
		session.getTransaction().rollback();
		session.close();
	}

	@AfterClass
	private static void afterClass(){
		session.close();
		sessionFactory.close();
	}



	@Test(groups = "Independent")
	public void creepy() {
		Creepy creepy = Creepy
				.builder()
				.firstName("Creepy first name")
				.lastName("Creepy last name")
				.email("test.dont.work.com")
				.nick("root")
				.password("root")
				.photoUrl("http://to.jest.photo.pl")
				.build();
		Creepy finalCreepy = creepy;
		assertThrows(javax.validation.ConstraintViolationException.class, () -> {
			session.save(finalCreepy);
		});
		creepy = Creepy
				.builder()
				.firstName("Cr")
				.lastName("Creepy last name")
				.email("test.dont6@work.com")
				.nick("root")
				.password("root")
				.photoUrl("http://to.jest.photo.pl")
				.build();
		Creepy finalCreepy1 = creepy;
		assertThrows(javax.validation.ConstraintViolationException.class, () -> {
			session.save(finalCreepy1);
		});
		creepy = Creepy
				.builder()
				.firstName("Creepy first name")
				.lastName("Creepy last name")
				.email("test.dont5@work.com")
				.nick("dd")
				.password("root")
				.photoUrl("http://to.jest.photo.pl")
				.build();
		Creepy finalCreepy2 = creepy;
		assertThrows(javax.validation.ConstraintViolationException.class, () -> {
			session.save(finalCreepy2);
		});
		creepy = Creepy
				.builder()
				.firstName("     ")
				.lastName("Creepy last name")
				.email("test.dont4@work.com")
				.nick("dd")
				.password("root")
				.photoUrl("http://to.jest.photo.pl")
				.build();
		Creepy finalCreepy3 = creepy;
		assertThrows(javax.validation.ConstraintViolationException.class, () -> {
			session.save(finalCreepy3);
		});
		creepy = Creepy
				.builder()
				.firstName("")
				.lastName("Creepy last name")
				.email("test.dont3@work.com")
				.nick("root")
				.password("root")
				.photoUrl("http://to.jest.photo.pl")
				.build();
		Creepy finalCreepy4 = creepy;
		assertThrows (javax.validation.ConstraintViolationException.class, () -> {
			session.save(finalCreepy4);
		});
		creepy = Creepy
				.builder()
				.firstName("Test")
				.lastName("Creepy last name")
				.email("test.dont2@work.com")
				.nick("root")
				.password("root")
				.photoUrl("http://to.jest.photo.pl")
				.build();
		Creepy finalCreepy5 = creepy;
		creepy = Creepy
				.builder()
				.firstName("Test2")
				.lastName("Creepy last name2")
				.email("test.dont1@work.com")
				.nick("root2")
				.password("root")
				.photoUrl("http://to.jest.photo.pl")
				.build();
		Creepy finalCreepy6 = creepy;
		assertThrows (org.hibernate.exception.ConstraintViolationException.class, () -> {
			session.save(finalCreepy5);
			session.save(finalCreepy6);
		});
	}

	@Test(groups = "Independent")
	public void mentor(){
		Mentor mentor1 = Mentor.builder()
				.firstName("")
				.lastName("Mentor last First")
				.email("mentor1@com.pl")
				.nick("mentor1")
				.password("asdfg")
				.photoUrl("http://mentor.photo1.pl")
				.build();
		Mentor finalMentor = mentor1;
		assertThrows(javax.validation.ConstraintViolationException.class, () -> {
			session.save(finalMentor);
		});
		mentor1 = Mentor.builder()
				.firstName("Test")
				.lastName("Mentor last First")
				.email("mentor1.com.pl")
				.nick("mentor1")
				.password("asdfg")
				.photoUrl("http://mentor.photo1.pl")
				.build();
		Mentor finalMentor1 = mentor1;
		assertThrows(javax.validation.ConstraintViolationException.class, () -> {
			session.save(finalMentor1);
		});
		mentor1 = Mentor.builder()
				.firstName("Test")
				.lastName("Mentor last First")
				.email("mentor1@com.pl")
				.nick("mentor1")
				.password("asdfg")
				.photoUrl("http://mentor.photo1.pl")
				.build();
		Mentor mentor2 = Mentor.builder()
				.firstName("Test2")
				.lastName("Mentor last First2")
				.email("mentor1@com.pl2")
				.nick("mentor12")
				.password("asdfg")
				.photoUrl("http://mentor.photo1.pl")
				.build();
        rollBackAndBegin();
		session.save(mentor1);
		assertThrows (org.hibernate.exception.ConstraintViolationException.class, () -> {
			session.save(mentor2);
		});

	}

	@Test(groups = "Independent")
	public void userClass(){
		UserClass userClass = UserClass
				.builder()
				.name("el")
				.photoUrl("asdfasdfasdf")
				.build();
		UserClass userClass1 = userClass;
		assertThrows(ConstraintViolationException.class, () -> {
			session.save(userClass1);
		});
		userClass = UserClass
				.builder()
				.name("asdf")
				.photoUrl("")
				.build();
		UserClass userClass2 = userClass;
		assertThrows(ConstraintViolationException.class, () -> {
			session.save(userClass2);
		});
		userClass = UserClass
				.builder()
				.name("asdf")
				.photoUrl("asdf")
				.build();
		UserClass userClass3 = userClass;
		userClass = UserClass
				.builder()
				.name("asdf")
				.photoUrl("asdf")
				.build();
		UserClass userClass4 = userClass;
		rollBackAndBegin();
		session.save(userClass3);
		assertThrows(org.hibernate.exception.ConstraintViolationException.class, () -> {
			session.save(userClass4);
		});
	}

	@Test(groups = "Independent")
	public void userLevel(){
		UserLevel userLevel = UserLevel
				.builder()
				.name("User Level")
				.value(0)
				.build();
		UserLevel userLevel1 = userLevel;
		assertThrows(ConstraintViolationException.class, () -> {
			session.save(userLevel1);
		});
		userLevel = UserLevel
				.builder()
				.name("User Level1")
				.value(101)
				.build();
		UserLevel userLevel2 = userLevel;
		assertThrows(ConstraintViolationException.class, () -> {
			session.save(userLevel2);
		});
		userLevel = UserLevel
				.builder()
				.name("")
				.value(99)
				.build();
		UserLevel userLevel3 = userLevel;
		assertThrows(ConstraintViolationException.class, () -> {
			session.save(userLevel3);
		});
		userLevel = UserLevel
				.builder()
				.name("asdfgh")
				.value(90)
				.build();
		UserLevel userLevel4 = userLevel;
		userLevel = UserLevel
				.builder()
				.name("asdfgh")
				.value(90)
				.build();
		UserLevel userLevel5 = userLevel;
        rollBackAndBegin();
		session.save(userLevel4);
		assertThrows(org.hibernate.exception.ConstraintViolationException.class, () -> {
			session.save(userLevel5);
		});
	}

	@Test(groups = "Independent")
	public void itemCategory(){
		ItemCategory itemCategory = ItemCategory
				.builder()
				.name("")
				.build();
		ItemCategory finalItemCategory = itemCategory;
		assertThrows(ConstraintViolationException.class, () -> {
			session.save(finalItemCategory);
		});
		itemCategory = ItemCategory
				.builder()
				.name("    ")
				.build();
		ItemCategory itemCategory2 = itemCategory;
		assertThrows(ConstraintViolationException.class, () -> {
			session.save(itemCategory2);
		});
		ItemCategory itemCategory3 = ItemCategory
				.builder()
				.name("Test")
				.build();
		ItemCategory itemCategory4 = ItemCategory
				.builder()
				.name("Test")
				.build();
		rollBackAndBegin();
		session.save(itemCategory3);
		assertThrows(org.hibernate.exception.ConstraintViolationException.class, () -> {
			session.save(itemCategory4);
		});
	}

	@Test(groups = "Independent")
	public void questCategory(){
		QuestCategory questCategory = QuestCategory
				.builder()
				.name("")
				.build();
		QuestCategory finalQuestCategory = questCategory;
		assertThrows(ConstraintViolationException.class, () -> {
			session.save(finalQuestCategory);
		});
		questCategory = QuestCategory
				.builder()
				.name("    ")
				.build();
		QuestCategory questCategory2 = questCategory;
		assertThrows(ConstraintViolationException.class, () -> {
			session.save(questCategory2);
		});
		QuestCategory questCategory3 = QuestCategory
				.builder()
				.name("Test")
				.build();
		QuestCategory questCategory4 = QuestCategory
				.builder()
				.name("Test")
				.build();
		rollBackAndBegin();
		session.save(questCategory3);
		assertThrows(org.hibernate.exception.ConstraintViolationException.class, () -> {
			session.save(questCategory4);
		});
	}








	private static void rollBackAndBegin(){
		session.getTransaction().rollback();
		session.beginTransaction();
	}


}
