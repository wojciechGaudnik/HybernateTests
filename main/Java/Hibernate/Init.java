package Hibernate;

import Hibernate.Model.Baskets.GroupItemBasket;
import Hibernate.Model.Baskets.GroupQuestBasket;
import Hibernate.Model.Common.ItemCategory;
import Hibernate.Model.Common.QuestCategory;
import Hibernate.Model.Common.UserClass;
import Hibernate.Model.Common.UserLevel;
import Hibernate.Model.Persons.Creepy;
import org.hibernate.Session;

import java.util.ArrayList;

public class Init {

	 static void initCreepy(Session session) {
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

	static void initGroupQuestBasket(Session session) {
		GroupQuestBasket groupQuestBasket1 = GroupQuestBasket.builder()
				.name("Group Quest Basket First")
				.build();

		GroupQuestBasket groupQuestBasket2 = GroupQuestBasket.builder()
				.name("Group Quest Basket Second")
				.build();

		session.save(groupQuestBasket1);
		session.save(groupQuestBasket2);
	}

	static void initGroupItemBasket(Session session) {
		GroupItemBasket groupItemBasket1 = GroupItemBasket.builder()
				.name("Group Item Basket First")
				.users(new ArrayList<>())
				.close(false)
				.users(new ArrayList<>())
				.build();

		GroupItemBasket groupItemBasket2 = GroupItemBasket.builder()
				.name("Group Item Basket Second")
				.build();

		session.save(groupItemBasket1);
		session.save(groupItemBasket2);
	}



	static void updateUserClass(Session session) {
		UserClass userClass = session.get(UserClass.class, 1L);
		userClass.setName("New name3");
		session.merge(userClass);
	}

	static void initUserLevel(Session session) {
		UserLevel userLevel1 = UserLevel.builder()
				.name("User Level First")
				.value(1)
				.itemCardList(new ArrayList<>())
				.questCardList(new ArrayList<>())
				.usersList(new ArrayList<>())
				.build();

		UserLevel userLevel2 = UserLevel.builder()
				.name("User Level Second")
				.value(1)
				.build();

		session.save(userLevel1);
		session.save(userLevel2);
	}

	static void initQuestCategory(Session session) {
		QuestCategory questCategory1 = QuestCategory.builder()
				.name("Quest Category First")
				.build();

		QuestCategory questCategory2 = QuestCategory.builder()
				.name("Quest Category Second")
				.build();

		session.save(questCategory1);
		session.save(questCategory2);
	}

	static void initItemCategory(Session session) {
		ItemCategory itemCategory1 = ItemCategory.builder()
				.name("Item Category First")
				.build();

		ItemCategory itemCategory2 = ItemCategory.builder()
				.name("Item Category Second")
				.build();

		session.save(itemCategory1);
		session.save(itemCategory2);
	}

	static void initUserClass(Session session) {
		UserClass userClass1 = UserClass.builder()
				.name("User Class First")
				.photoUrl("http://test.pl/photo1.jpg")
				.mentors(new ArrayList<>())
				.users(new ArrayList<>())
				.build();

		UserClass userClass2 = UserClass.builder()
				.name("User Class Second")
				.photoUrl("http://test.pl/photo2.jpg")
				.mentors(new ArrayList<>())
				.users(new ArrayList<>())
				.build();

		session.save(userClass1);
		session.save(userClass2);
	}
}
