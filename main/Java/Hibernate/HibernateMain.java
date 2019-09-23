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

import java.util.ArrayList;

import static Hibernate.Init.*;

//todo one more time email validation !!!
//todo What if make One Class person and all users will be inherit ??
//todo change EAGER into Hibernate.initialize(object proxy);
//todo serializable ?? why here ?

//@NotEmpty – validates that the property is not null or empty; can be applied to String, Collection, Map or Array values
//@NotBlank – can be applied only to text values and validated that the property is not null or whitespace
//@Positive and @PositiveOrZero – apply to numeric values and validate that they are strictly positive, or positive including 0
//@Negative and @NegativeOrZero – apply to numeric values and validate that they are strictly negative, or negative including 0
//@Past and @PastOrPresent – validate that a date value is in the past or the past including the present; can be applied to date types including those added in Java 8
//@Future and @FutureOrPresent – validates that a date value is in the future, or in the future including the present
//@NotNull(message = "Name cannot be null")
//@AssertTrue
//private boolean working;
//@Size(min = 10, max = 200, message
//		= "About Me must be between 10 and 200 characters")
//@Min(value = 18, message = "Age should not be less than 18")
//@Max(value = 150, message = "Age should not be greater than 150")
//@Email(message = "Email should be valid")


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

		initUserClass(session);
//		initUserLevel(session);
//		initItemCategory(session);
//		initQuestCategory(session);
//		initGroupItemBasket(session);
//		initGroupQuestBasket(session);

		UserClass userClass1 = session.get(UserClass.class, 1L);
		UserClass userClass2 = session.get(UserClass.class, 2L);

		Mentor mentor1 = Mentor.builder()
				.firstName("Mentor name First")
				.lastName("Mentor last Second")
				.email("test.mail.pl")
				.nick("mentor1")
				.password("mentor")
				.photoUrl("http://photo.mentor1.com")
				.userClasses(new ArrayList<>())
				.build();
		mentor1.getUserClasses().add(userClass1);
		mentor1.getUserClasses().add(userClass2);

		userClass1.getMentors().add(mentor1);
		userClass2.getMentors().add(mentor1);


		session.save(mentor1);
		session.update(userClass1);
		session.update(userClass2);

		UserClass userClass11 = session.get(UserClass.class, 1L);
		UserClass userClass22 = session.get(UserClass.class, 2L);

		System.out.println(userClass11.getMentors() + " <-----------------------------------");
		System.out.println(userClass22.getMentors() + " <-----------------------------------");


//		Mentor mentor1_but_1 = session.get(Mentor.class, 1L);
//		System.out.println(mentor1_but_1 + " <------------------------------------");
//		System.out.println(mentor1_but_1.getFirstName() + " <------------------------------------");
//		System.out.println(mentor1_but_1.getUserClasses().get(0).getName() + " <------------------------------------");
//		System.out.println(mentor1_but_1.getUserClasses().get(1).getName() + " <------------------------------------");


//		UserLevel userLevel = session.get(UserLevel.class, 1L);
//		ItemCategory itemCategory = session.get(ItemCategory.class, 1L);
//		GroupItemBasket groupItemBasket = session.get(GroupItemBasket.class, 1L);
//
//		ItemCard itemCard = ItemCard.builder()
//				.name("Item Card first")
//				.photoUrl("http://it.is.url.for.photo1.com")
//				.value(1)
//				.description("Item Card Description First")
//				.allowedGroupBuy(true)
//				.userLevel(userLevel)
//				.itemCategory(itemCategory)
//				.groupItemBasket(groupItemBasket)
//				.build();
//
//
//		session.save(itemCard);
//		userLevel.getItemCardSet().add(itemCard);
//		session.merge(userLevel);














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


}
