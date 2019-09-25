//package Hibernate.testBad;
//
//import Hibernate.Model.Common.ItemCategory;
//import Hibernate.Model.Common.QuestCategory;
//import Hibernate.Model.Common.UserClass;
//import Hibernate.Model.Common.UserLevel;
//import Hibernate.Model.Persons.Creepy;
//import Hibernate.Model.Persons.Mentor;
//import Hibernate.Model.Persons.User;
//import org.hibernate.Session;
//
//
//public interface KurwaMac {
//	static void creepy(Session session) {
//		Creepy creepy = Creepy
//				.builder()
//				.firstName("Creepy first name")
//				.lastName("Creepy last name")
//				.email("test.dont@work.com")
//				.nick("root")
//				.password("root")
//				.photoUrl("http://to.jest.photo.pl")
//				.build();
//		session.save(creepy);
//	}
//
//	static void mentor(Session session) {
//		Mentor mentor1 = Mentor.builder()
//				.firstName("Mentor name First")
//				.lastName("Mentor last First")
//				.email("mentor1@com.pl")
//				.nick("mentor1")
//				.password("asdfg")
//				.photoUrl("http://mentor.photo1.pl")
//				.build();
//		Mentor mentor2 = Mentor.builder()
//				.firstName("Mentor name Second")
//				.lastName("Mentor last Second")
//				.email("mentor2@com.pl")
//				.nick("mentor2")
//				.password("asdfg")
//				.photoUrl("http://mentor.photo2.pl")
//				.build();
//		Mentor mentor3 = Mentor.builder()
//				.firstName("Mentor name Third")
//				.lastName("Mentor last Third")
//				.email("mentor3@com.pl")
//				.nick("mentor3")
//				.password("asdfg")
//				.photoUrl("http://mentor.photo3.pl")
//				.build();
//		session.save(mentor1);
//		session.save(mentor2);
//		session.save(mentor3);
//	}
//
//	static void userClass(Session session) {
//		UserClass userClass1 = UserClass.builder()
//				.name("User Class First")
//				.photoUrl("http://test.pl/photo1.jpg")
//				.build();
//		UserClass userClass2 = UserClass.builder()
//				.name("User Class Second")
//				.photoUrl("http://test.pl/photo2.jpg")
//				.build();
//		UserClass userClass3 = UserClass.builder()
//				.name("User Class Third")
//				.photoUrl("http://test.pl/photo3.jpg")
//				.build();
//		session.save(userClass1);
//		session.save(userClass2);
//		session.save(userClass3);
//	}
//
//	static void userLevel(Session session) {
//		UserLevel userLevel1 = UserLevel.builder()
//				.name("User Level First")
//				.value(1)
//				.build();
//		UserLevel userLevel2 = UserLevel.builder()
//				.name("User Level Second")
//				.value(2)
//				.build();
//		UserLevel userLevel3 = UserLevel.builder()
//				.name("User Level Third")
//				.value(3)
//				.build();
//		session.save(userLevel1);
//		session.save(userLevel2);
//		session.save(userLevel3);
//	}
//
//	static void itemCategory(Session session) {
//		ItemCategory itemCategory1 = ItemCategory.builder()
//				.name("Item Category First")
//				.build();
//		ItemCategory itemCategory2 = ItemCategory.builder()
//				.name("Item Category Second")
//				.build();
//		ItemCategory itemCategory3 = ItemCategory.builder()
//				.name("Item Category Third")
//				.build();
//		session.save(itemCategory1);
//		session.save(itemCategory2);
//		session.save(itemCategory3);
//	}
//
//	static void questCategory(Session session) {
//		QuestCategory questCategory1 = QuestCategory.builder()
//				.name("Quest Category First")
//				.build();
//		QuestCategory questCategory2 = QuestCategory.builder()
//				.name("Quest Category Second")
//				.build();
//		QuestCategory questCategory3 = QuestCategory.builder()
//				.name("Quest Category Third")
//				.build();
//		session.save(questCategory1);
//		session.save(questCategory2);
//		session.save(questCategory3);
//	}
//
//	static void user(Session session) {
//		UserLevel userLevel1 = session.get(UserLevel.class, 1L);
//		UserLevel userLevel2 = session.get(UserLevel.class, 2L);
//		UserLevel userLevel3 = session.get(UserLevel.class, 3L);
//
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
//		System.out.println(userLevel1.getName() + " <---------------");
//		System.out.println(userLevel2.getName() + " <---------------");
//		System.out.println(userLevel3.getName() + " <---------------");
//	}
//
//	static void updateUserClass(Session session) {
//		UserClass userClass = session.get(UserClass.class, 1L);
//		userClass.setName("New name3");
//		session.merge(userClass);
//	}
//}
