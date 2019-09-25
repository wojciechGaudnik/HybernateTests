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


class InitEntities {

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

	static void user(Session session) {
		UserLevel userLevel1 = session.get(UserLevel.class, 1L);
		UserLevel userLevel2 = session.get(UserLevel.class, 2L);
		UserLevel userLevel3 = session.get(UserLevel.class, 3L);

		User user1 = User.builder()
				.firstName("User First")
				.lastName("Last name First")
				.email("user1@test.pl")
				.nick("user nick First")
				.password("asdf")
				.photoUrl("http://photo1.com.pl")
				.userLevel(userLevel1)
				.build();
		User user2 = User.builder()
				.firstName("User Second")
				.lastName("Last name Second")
				.email("user2@test.pl")
				.nick("user nick Second")
				.password("asdff")
				.photoUrl("http://photo2.com.pl")
				.userLevel(userLevel1)
				.build();
		User user3 = User.builder()
				.firstName("User Third")
				.lastName("Last name Third")
				.email("user3@test.pl")
				.nick("user nick Third")
				.password("asdf")
				.photoUrl("http://photo3.com.pl")
				.userLevel(userLevel3)
				.build();

		session.save(user1);
		session.save(user2);
		session.save(user3);
//		session.persist(user1);
//		session.persist(user2);
//		session.persist(user3);
	}

	static void groupItemBasket(Session session){
		User user1 = session.get(User.class, 1L);
		User user2 = session.get(User.class, 2L);
		User user3 = session.get(User.class, 3L);
		System.out.println(user1.getFirstName());
		System.out.println(user2.getFirstName());
		System.out.println(user3.getFirstName());
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------v");
		GroupItemBasket groupItemBasket1 = GroupItemBasket.builder()
				.name("Group Item Basket First")
				.value(1)
				.closeBasket(false)
				.owner(user1)
				.build();
		GroupItemBasket groupItemBasket2 = GroupItemBasket.builder()
				.name("Group Item Basket Second")
				.value(2)
				.closeBasket(false)
				.owner(user2)
				.build();
		GroupItemBasket groupItemBasket3 = GroupItemBasket.builder()
				.name("Group Item Basket Third")
				.value(3)
				.closeBasket(true)
				.owner(user2)
				.build();
		session.save(groupItemBasket1);
		session.save(groupItemBasket2);
		session.save(groupItemBasket3);
	}

	static void groupQuestBasket(Session session){
		User user1 = session.get(User.class, 1L);
		User user2 = session.get(User.class, 2L);
		User user3 = session.get(User.class, 3L);
		GroupQuestBasket groupQuestBasket1 = GroupQuestBasket.builder()
				.name("Group Quest Basket First")
				.value(1)
				.closeBasket(false)
				.owner(user1)
				.build();
		GroupQuestBasket groupQuestBasket2 = GroupQuestBasket.builder()
				.name("Group Quest Basket Second")
				.value(2)
				.closeBasket(false)
				.owner(user2)
				.build();
		GroupQuestBasket groupQuestBasket3 = GroupQuestBasket.builder()
				.name("Group Quest Basket Third")
				.value(3)
				.closeBasket(false)
				.owner(user3)
				.build();
		session.save(groupQuestBasket1);
		session.save(groupQuestBasket2);
		session.save(groupQuestBasket3);
	}

	static void itemCard(Session session) {
		ItemCategory itemCategory1 = session.get(ItemCategory.class, 1L);
		ItemCategory itemCategory2 = session.get(ItemCategory.class, 2L);
		ItemCategory itemCategory3 = session.get(ItemCategory.class, 3L);
		ItemCard itemCard1 = ItemCard.builder()
				.name("Item Card First")
				.photoUrl("http://test.photo1.com")
				.value(1)
				.description("Item Card Decryption First")
				.allowedGroupBuy(false)
				.itemCategory(itemCategory1)
				.build();
		ItemCard itemCard2 = ItemCard.builder()
				.name("Item Card Second")
				.photoUrl("http://test.photo2.com")
				.value(1)
				.description("Item Card Decryption Second")
				.allowedGroupBuy(false)
				.itemCategory(itemCategory2)
				.build();
		ItemCard itemCard3 = ItemCard.builder()
				.name("Item Card Third")
				.photoUrl("http://test.photo3.com")
				.value(1)
				.description("Item Card Decryption Third")
				.allowedGroupBuy(false)
				.itemCategory(itemCategory3)
				.build();
		session.save(itemCard1);
		session.save(itemCard2);
		session.save(itemCard3);
	}

	static void questCard(Session session) {
		QuestCategory questCategory1 = session.get(QuestCategory.class, 1L);
		QuestCategory questCategory2 = session.get(QuestCategory.class, 2L);
		QuestCategory questCategory3 = session.get(QuestCategory.class, 3L);
		QuestCard questCard1 = QuestCard.builder()
				.name("Quest Card First")
				.photoUrl("http://test.photo1.com")
				.value(1)
				.description("Quest Card Decryption First")
				.allowedGroupBuy(false)
				.questCategory(questCategory1)
				.build();
		QuestCard questCard2 = QuestCard.builder()
				.name("Quest Card Second")
				.photoUrl("http://test.photo2.com")
				.value(2)
				.description("Quest Card Decryption Second")
				.allowedGroupBuy(false)
				.questCategory(questCategory2)
				.build();
		QuestCard questCard3 = QuestCard.builder()
				.name("Quest Card Third")
				.photoUrl("http://test.photo3.com")
				.value(3)
				.description("Quest Card Decryption Third")
				.allowedGroupBuy(false)
				.questCategory(questCategory3)
				.build();
		session.save(questCard1);
		session.save(questCard2);
		session.save(questCard3);
	}
}
