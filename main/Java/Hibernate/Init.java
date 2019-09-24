package Hibernate;

import Hibernate.Model.Baskets.GroupItemBasket;
import Hibernate.Model.Baskets.GroupQuestBasket;
import Hibernate.Model.Common.ItemCategory;
import Hibernate.Model.Common.QuestCategory;
import Hibernate.Model.Common.UserClass;
import Hibernate.Model.Common.UserLevel;
import Hibernate.Model.Persons.Creepy;
import Hibernate.Model.Persons.Mentor;
import org.hibernate.Session;


public class Init {

	 static void creepy(Session session) {
		Creepy creepy = Creepy
				.builder()
				.firstName("Creepy first name")
				.lastName("Creepy last name")
				.email("test.dont@work.com")
				.nick("root")
				.password("root")
				.photoUrl("http://to.jest.photo.pl")
				.build();
		session.save(creepy);
	}

	static void userClass(Session session) {
		UserClass userClass1 = UserClass.builder()
				.name("User Class First")
				.photoUrl("http://test.pl/photo1.jpg")
				.build();
		UserClass userClass2 = UserClass.builder()
				.name("User Class Second")
				.photoUrl("http://test.pl/photo2.jpg")
				.build();
		UserClass userClass3 = UserClass.builder()
				.name("User Class Third")
				.photoUrl("http://test.pl/photo3.jpg")
				.build();
		session.save(userClass1);
		session.save(userClass2);
		session.save(userClass3);
	}

	static void userLevel(Session session) {
		UserLevel userLevel1 = UserLevel.builder()
				.name("User Level First")
				.value(1)
				.build();
		UserLevel userLevel2 = UserLevel.builder()
				.name("User Level Second")
				.value(2)
				.build();
		UserLevel userLevel3 = UserLevel.builder()
				.name("User Level Third")
				.value(3)
				.build();
		session.save(userLevel1);
		session.save(userLevel2);
		session.save(userLevel3);
	}

	static void itemCategory(Session session) {
		ItemCategory itemCategory1 = ItemCategory.builder()
				.name("Item Category First")
				.build();
		ItemCategory itemCategory2 = ItemCategory.builder()
				.name("Item Category Second")
				.build();
		ItemCategory itemCategory3 = ItemCategory.builder()
				.name("Item Category Third")
				.build();
		session.save(itemCategory1);
		session.save(itemCategory2);
		session.save(itemCategory3);
	}

	static void questCategory(Session session) {
		QuestCategory questCategory1 = QuestCategory.builder()
				.name("Quest Category First")
				.build();
		QuestCategory questCategory2 = QuestCategory.builder()
				.name("Quest Category Second")
				.build();
		QuestCategory questCategory3 = QuestCategory.builder()
				.name("Quest Category Third")
				.build();
		session.save(questCategory1);
		session.save(questCategory2);
		session.save(questCategory3);
	}

	static void groupItemBasket(Session session) {
		GroupItemBasket groupItemBasket1 = GroupItemBasket.builder()
				.name("Group Item Basket First")
				.value(1)
				.closeBasket(false)
				.build();
		GroupItemBasket groupItemBasket2 = GroupItemBasket.builder()
				.name("Group Item Basket Second")
				.value(2)
				.closeBasket(false)
				.build();
		GroupItemBasket groupItemBasket3 = GroupItemBasket.builder()
				.name("Group Item Basket Third")
				.value(3)
				.closeBasket(true)
				.build();
		session.save(groupItemBasket1);
		session.save(groupItemBasket2);
		session.save(groupItemBasket3);
	}

	static void groupQuestBasket(Session session) {
		GroupQuestBasket groupQuestBasket1 = GroupQuestBasket.builder()
				.name("Group Quest Basket First")
				.value(0)
				.close(false)
				.build();
		GroupQuestBasket groupQuestBasket2 = GroupQuestBasket.builder()
				.name("Group Quest Basket Second")
				.value(0)
				.close(false)
				.build();
		GroupQuestBasket groupQuestBasket3 = GroupQuestBasket.builder()
				.name("Group Quest Basket Third")
				.value(0)
				.close(false)
				.build();
		session.save(groupQuestBasket1);
		session.save(groupQuestBasket2);
		session.save(groupQuestBasket3);
	}

	static void mentor(Session session) {
		Mentor mentor1 = Mentor.builder()
				.firstName("Mentor name First")
				.lastName("Mentor last First")
				.email("mentor1@com.pl")
				.nick("mentor1")
				.password("asdfg")
				.photoUrl("http://mentor.photo1.pl")
				.build();
		Mentor mentor2 = Mentor.builder()
				.firstName("Mentor name Second")
				.lastName("Mentor last Second")
				.email("mentor2@com.pl")
				.nick("mentor2")
				.password("asdfg")
				.photoUrl("http://mentor.photo2.pl")
				.build();
		Mentor mentor3 = Mentor.builder()
				.firstName("Mentor name Third")
				.lastName("Mentor last Third")
				.email("mentor3@com.pl")
				.nick("mentor3")
				.password("asdfg")
				.photoUrl("http://mentor.photo3.pl")
				.build();
		session.save(mentor1);
		session.save(mentor2);
		session.save(mentor3);
	}


	static void updateUserClass(Session session) {
		UserClass userClass = session.get(UserClass.class, 1L);
		userClass.setName("New name3");
		session.merge(userClass);
	}






}
