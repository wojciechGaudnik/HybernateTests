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
import org.apache.lucene.analysis.ar.ArabicAnalyzer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//todo one more time email validation !!!
//todo What if make One Class person and all users will be inherit ??

//@NotEmpty – validates that the property is not null or empty; can be applied to String, Collection, Map or Array values
//@NotBlank – can be applied only to text values and validated that the property is not null or whitespace
//@Positive and @PositiveOrZero – apply to numeric values and validate that they are strictly positive, or positive including 0
//@Negative and @NegativeOrZero – apply to numeric values and validate that they are strictly negative, or negative including 0
//@Past and @PastOrPresent – validate that a date value is in the past or the past including the present; can be applied to date types including those added in Java 8
//@Future and @FutureOrPresent – validates that a date value is in the future, or in the future including the present
//@NotNull(message = "Name cannot be null")
//private String name;
//
//@AssertTrue
//private boolean working;
//
//@Size(min = 10, max = 200, message
//		= "About Me must be between 10 and 200 characters")
//private String aboutMe;
//
//@Min(value = 18, message = "Age should not be less than 18")
//@Max(value = 150, message = "Age should not be greater than 150")
//private int age;
//
//@Email(message = "Email should be valid")
//private String email;


public class HibernateMain {
	public static void main(String[] args) {

		SessionFactory sf=new Configuration()
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
//				.configure()
				.buildSessionFactory();
		Session session=sf.openSession();
		session.beginTransaction();

		initCreepy(session);
		initItemCategory(session);
		initQuestCategory(session);
		initUserClass(session);
		initUserLevel(session);
		initGroupItemBasket(session);
		initGroupQuestBasket(session);


		UserLevel userLevel = session.get(UserLevel.class, 1L);
		ItemCategory itemCategory = session.get(ItemCategory.class, 1L);
		GroupItemBasket groupItemBasket = session.get(GroupItemBasket.class, 1L);



		ItemCard itemCard = ItemCard.builder()
				.name("Item Card first")
				.photoUrl("http://it.is.url.for.photo1.com")
				.value(1)
				.description("Item Card Description First")
				.allowedGroupBuy(true)
				.resolved(false)
				.userLevel(userLevel)
				.itemCategory(itemCategory)
				.groupItemBasket(groupItemBasket)
				.build();


		session.save(itemCard);
		userLevel.getItemCardSet().add(itemCard);
		session.merge(userLevel);

//		UserLevel userLevel1 = session.get(UserLevel.class, 1L);



//








//		UserClass userClass1 = session.get(UserClass.class, 1L);
//		UserClass userClass2 = session.get(UserClass.class, 2L);
//		Mentor mentor = Mentor
//				.builder()
//				.firstName("Adam")
//				.lastName("Stamirowski")
//				.email("ala@ma.kota.pl")
//				.nick("dot")
//				.password("root")
//				.photoUrl("http://photo.mentor1.com")
//				.userClasses(new HashSet<>(Set.of(userClass1, userClass2)))
//				.build();
//		session.save(mentor);
//		userClass1.getMentors().add(mentor);
//		session.merge(userClass1);



//		updateUserClass(session);

		session.getTransaction().commit();
		session.close();
//		sf.close();
	}

	private static void initGroupQuestBasket(Session session) {
		GroupQuestBasket groupQuestBasket1 = GroupQuestBasket.builder()
				.name("Group Quest Basket First")
				.build();

		GroupQuestBasket groupQuestBasket2 = GroupQuestBasket.builder()
				.name("Group Quest Basket Second")
				.build();

		session.save(groupQuestBasket1);
		session.save(groupQuestBasket2);
	}

	private static void initGroupItemBasket(Session session) {
		GroupItemBasket groupItemBasket1 = GroupItemBasket.builder()
				.name("Group Item Basket First")
				.build();

		GroupItemBasket groupItemBasket2 = GroupItemBasket.builder()
				.name("Group Item Basket Second")
				.build();

		session.save(groupItemBasket1);
		session.save(groupItemBasket2);
	}

	private static void initCreepy(Session session) {
		Creepy creepy = Creepy
				.builder()
				.firstName("Creepy first name")
				.lastName("Creepy last name")
				.email("test.dont@work.com")
				.nick("gg666")
				.password("666")
				.photoUrl("http://to.jest.photo.pl")
				.build();
		session.save(creepy);
	}

	private static void updateUserClass(Session session) {
		UserClass userClass = session.get(UserClass.class, 1L);
		userClass.setName("New name3");
		session.merge(userClass);
	}

	private static void initUserLevel(Session session) {
		UserLevel userLevel1 = UserLevel.builder()
				.name("User Level First")
				.value(1)
				.itemCardSet(new ArrayList<>())
				.questCardSet(new ArrayList<>())
				.usersSet(new ArrayList<>())
				.build();

		UserLevel userLevel2 = UserLevel.builder()
				.name("User Level Second")
				.value(1)
				.build();

		session.save(userLevel1);
		session.save(userLevel2);
	}

	private static void initQuestCategory(Session session) {
		QuestCategory questCategory1 = QuestCategory.builder()
				.name("Quest Category First")
				.build();

		QuestCategory questCategory2 = QuestCategory.builder()
				.name("Quest Category Second")
				.build();

		session.save(questCategory1);
		session.save(questCategory2);
	}

	private static void initItemCategory(Session session) {
		ItemCategory itemCategory1 = ItemCategory.builder()
				.name("Item Category First")
				.build();

		ItemCategory itemCategory2 = ItemCategory.builder()
				.name("Item Category Second")
				.build();

		session.save(itemCategory1);
		session.save(itemCategory2);
	}

	private static void initUserClass(Session session) {
		UserClass userClass1 = UserClass.builder()
				.name("User Class First")
				.photoUrl("http://test.pl/photo1.jpg")
				.build();

		UserClass userClass2 = UserClass.builder()
				.name("User Class Second")
				.photoUrl("http://test.pl/photo2.jpg")
				.build();

		session.save(userClass1);
		session.save(userClass2);
	}
}
