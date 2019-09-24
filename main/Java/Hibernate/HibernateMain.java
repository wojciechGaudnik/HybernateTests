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


//		AuditReader auditReader = AuditReaderFactory.get(session);
//				Creepy oldCreepy = auditReader.find(Creepy.class, 1L, 1);
//		System.out.println(oldCreepy);

//todo ASK MENTOR how make interface with hibernate users,mentors,creepy
//todo one more time email validation !!!
//todo What if make One Class person and all users will be inherit ??
//todo change EAGER into Hibernate.initialize(object proxy);
//todo serializable ?? why here ?
//todo	@Retention(RUNTIME)   //todo <--- check if help with catch errors !!!

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
//If you worry about the lack of a predefined entry order, then you can use either the @OrderBy or @OrderColumn JPA annotations.
//@Temporal(TemporalType.TIMESTAMP)

public class HibernateMain {
	public static void main(String[] args) {

		SessionFactory sessionFactory=new Configuration()
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
		Session session=sessionFactory.openSession();
		session.beginTransaction();

		System.out.println("Init -------------------------------------------------------------------------------------");

		Init.creepy(session);
		Init.mentor(session);
		Init.userClass(session);
		Init.userLevel(session);
		Init.itemCategory(session);
		Init.questCategory(session);
//		Init.groupItemBasket(session);
//		Init.groupQuestBasket(session);

		session = closeOpenSession(sessionFactory, session);
		System.out.println("First -------------------------------------------------------------------------------------");







//		ItemCategory itemCategory1 = session.get(ItemCategory.class, 1L);
//		ItemCategory itemCategory2 = session.get(ItemCategory.class, 2L);
//
//		ItemCard itemCard1 = ItemCard.builder()
//				.name("Card First")
//				.photoUrl("http://test.photo1.com")
//				.value(6)
//				.description("Description Card First")
//				.allowedGroupBuy(false)
//				.build();
//		ItemCard itemCard2 = ItemCard.builder()
//				.name("Card Second")
//				.photoUrl("http://test.photo2.com")
//				.value(6)
//				.description("Description Card First")
//				.allowedGroupBuy(false)
//				.build();
//		ItemCard itemCard3 = ItemCard.builder()
//				.name("Card Third")
//				.photoUrl("http://test.photo3.com")
//				.value(6)
//				.description("Description Card Third")
//				.allowedGroupBuy(false)
//				.build();
//
//		itemCard1.setItemCategory(itemCategory1);
//		itemCard2.setItemCategory(itemCategory1);
//		itemCategory1.getItemCards().add(itemCard3);
////		itemCard3.setItemCategory(itemCategory2);
//
////		itemCategory2.getItemCards().add(itemCard2);
//
//		session.save(itemCard1);
//		session.save(itemCard2);
//		session.save(itemCard3);
//		session.save(itemCategory1);
//
		session = closeOpenSession(sessionFactory, session);
		System.out.println("Second -------------------------------------------------------------------------------------");

//		ItemCategory itemCategoryTest = session.get(ItemCategory.class, 1L);
//		ItemCard itemCard1Test = session.get(ItemCard.class, 1L);
//
//		System.out.println(itemCategoryTest.getItemCards().get(0).getName());
//		System.out.println(itemCategoryTest.getItemCards().get(1).getName());
//		System.out.println(itemCard1Test.getItemCategory().getName());
//		System.out.println(itemCategoryTest.toString());
//		itemCategoryTest.getItemCards().forEach(ItemCard::getName);
//		System.out.println(itemCategoryTest + " <-------");
//		System.out.println(itemCategoryTest.getItemCards().get(0).getName() + " <---------");
//		System.out.println(itemCategoryTest.getItemCards().get(1).getName() + " <---------");







		System.out.println("Stop -------------------------------------------------------------------------------------");
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}

	private static Session closeOpenSession(SessionFactory sf, Session session) {
		session.getTransaction().commit();
		session.close();
		session = sf.openSession();
		session.beginTransaction();
		return session;
	}


}
