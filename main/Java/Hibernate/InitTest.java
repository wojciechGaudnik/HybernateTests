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
import org.testng.annotations.*;


import static org.junit.jupiter.api.Assertions.*;

public class InitTest {

	public static Session session;

	@BeforeClass
	public void before(){
		SessionFactory sf = new Configuration()
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
		session = sf.openSession();
		session.beginTransaction();
	}

	@AfterClass
	public void after(){
//		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void initCreepy() {
		Creepy creepy = Creepy
				.builder()
				.firstName("Creepy first name")
				.lastName("Creepy last name")
				.email("test.dont.work.com")
				.nick("root")
				.password("root")
				.photoUrl("http://to.jest.photo.pl")
				.build();
		System.out.println(creepy);
		Creepy finalCreepy = creepy;
		assertThrows(javax.validation.ConstraintViolationException.class, () -> {
			session.save(finalCreepy);
		});
		creepy = Creepy
				.builder()
				.firstName("Cr")
				.lastName("Creepy last name")
				.email("test.dont@work.com")
				.nick("root")
				.password("root")
				.photoUrl("http://to.jest.photo.pl")
				.build();
		System.out.println(creepy);
		Creepy finalCreepy1 = creepy;
		assertThrows(javax.validation.ConstraintViolationException.class, () -> {
			session.save(finalCreepy1);
		});
		creepy = Creepy
				.builder()
				.firstName("Creepy first name")
				.lastName("Creepy last name")
				.email("test.dont@work.com")
				.nick("dd")
				.password("root")
				.photoUrl("http://to.jest.photo.pl")
				.build();
		System.out.println(creepy);
		Creepy finalCreepy2 = creepy;
		assertThrows(javax.validation.ConstraintViolationException.class, () -> {
			session.save(finalCreepy2);
		});
		creepy = Creepy
				.builder()
				.firstName("Creepy first name")
				.lastName("Creepy last name")
				.email("test.dont@work.com")
				.nick("root")
				.password("root")
				.photoUrl("http://to.jest.photo.pl")
				.build();
		System.out.println(creepy);
		Creepy finalCreepy3 = creepy;
		assertDoesNotThrow (() -> {
			session.save(finalCreepy3);
		});
		creepy = Creepy
				.builder()
				.firstName("")
				.lastName("Creepy last name")
				.email("test.dont@work.com")
				.nick("root")
				.password("root")
				.photoUrl("http://to.jest.photo.pl")
				.build();
		System.out.println(creepy);
		Creepy finalCreepy4 = creepy;
		assertThrows (javax.validation.ConstraintViolationException.class, () -> {
			session.save(finalCreepy4);
		});

	}

	@Test
	public void initMentor(){

	}
}