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
//import Hibernate.testBad.KurwaMac;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.testng.annotations.*;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//
//
//public class KurwaMacTest implements KurwaMac {
//
//	private static Session session;
//	private static SessionFactory sessionFactory;
//
//	private static void before(){
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
//	private static void after(){
//		session.getTransaction().rollback();
//		sessionFactory.close();
//	}
//
//	@Test
//	public void initTest(){
//		before();
//		assertDoesNotThrow(() -> {
//			KurwaMac.creepy(session);
//			KurwaMac.mentor(session);
//			KurwaMac.userClass(session);
//			KurwaMac.userLevel(session);
//			KurwaMac.itemCategory(session);
//			KurwaMac.questCategory(session);
////
////		Init.user(session);
//
////		Init.groupItemBasket(session);
////		Init.groupQuestBasket(session);
//		});
//		after();
//	}
//
//
//	//	static void groupItemBasket(Session session) {
////
////	}
//
//
//	static void groupItemBasket(Session session) {
//		GroupItemBasket groupItemBasket1 = GroupItemBasket.builder()
//				.name("Group Item Basket First")
//				.value(1)
//				.closeBasket(false)
//				.build();
//		GroupItemBasket groupItemBasket2 = GroupItemBasket.builder()
//				.name("Group Item Basket Second")
//				.value(2)
//				.closeBasket(false)
//				.build();
//		GroupItemBasket groupItemBasket3 = GroupItemBasket.builder()
//				.name("Group Item Basket Third")
//				.value(3)
//				.closeBasket(true)
//				.build();
//		session.save(groupItemBasket1);
//		session.save(groupItemBasket2);
//		session.save(groupItemBasket3);
//	}
//
//	static void groupQuestBasket(Session session) {
//		GroupQuestBasket groupQuestBasket1 = GroupQuestBasket.builder()
//				.name("Group Quest Basket First")
//				.value(0)
//				.closeBasket(false)
//				.build();
//		GroupQuestBasket groupQuestBasket2 = GroupQuestBasket.builder()
//				.name("Group Quest Basket Second")
//				.value(0)
//				.closeBasket(false)
//				.build();
//		GroupQuestBasket groupQuestBasket3 = GroupQuestBasket.builder()
//				.name("Group Quest Basket Third")
//				.value(0)
//				.closeBasket(false)
//				.build();
//		session.save(groupQuestBasket1);
//		session.save(groupQuestBasket2);
//		session.save(groupQuestBasket3);
//	}
//
//
//}
