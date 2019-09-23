package Hibernate.Model.Persons;

import Hibernate.Model.Baskets.GroupItemBasket;
import Hibernate.Model.Baskets.GroupQuestBasket;
import Hibernate.Model.Cards.ItemCard;
import Hibernate.Model.Cards.QuestCard;
import Hibernate.Model.Common.UserClass;
import Hibernate.Model.Common.UserLevel;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Audited
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(toBuilder = true)
@Entity(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotEmpty(message = "firstName is mandatory")
	private String firstName;

	@NotEmpty(message = "lastName is mandatory")
	private String lastName;

	@Column(unique = true)
	@NotEmpty(message = "email is mandatory")
	@Email(message="Please provide a valid email address")
//	@Retention(RUNTIME)   //todo <--- check if help with catch errors !!!
	private String email;

	@Column(unique = true)
	@NotEmpty(message = "nick is mandatory")
	private String nick;

	@Column(unique = true)
	@NotEmpty(message = "password is mandatory")
	private String password;  //todo <--- how save password

	@NotEmpty(message = "photo is mandatory")
	private String photoUrl;

	// todo https://thoughts-on-java.org/hibernate-tips-elementcollection/
//	private List<ItemCard> resolvedItemsCards;
//
//	private List<QuestCard> resolvedQuestsCards;

	@OneToMany(
			targetEntity = GroupQuestBasket.class,
			fetch = FetchType.EAGER)
	private List<GroupQuestBasket> groupQuestBasketsOwned;

	@OneToMany(
			targetEntity = GroupItemBasket.class,
			fetch = FetchType.EAGER)
	private List<GroupItemBasket> groupItemBasketsOwned;

	@NotEmpty(message = "userLevel is mandatory")
	@ManyToOne(
			targetEntity = UserLevel.class,
			fetch = FetchType.EAGER	)
	@JoinColumn(name = "user_level_id")
	private UserLevel userLevel;

	@NotEmpty(message = "userClass is mandatory")
	@ManyToOne(
			targetEntity = UserClass.class,
			fetch = FetchType.EAGER	)
	@JoinColumn(name = "user_class_id")
	private UserClass userClass;

	@ManyToMany(
			targetEntity = GroupItemBasket.class,
			fetch = FetchType.EAGER	)
	@JoinTable(
			name = "join_user_groupitembasket",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "group_item_basket_id")})
	private List<GroupItemBasket> groupItemBaskets;

	@ManyToMany(
			targetEntity = GroupQuestBasket.class,
			fetch = FetchType.EAGER	)
	@JoinTable(
			name = "join_user_groupquestbasket",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "group_quest_basket_id")}	)
	private List<GroupQuestBasket> groupQuestBaskets;

	@ManyToMany(
			targetEntity = ItemCard.class,
			fetch = FetchType.EAGER	)
	@JoinTable(
			name = "join_user_itemcard",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "item_card_id")}	)
	private List<ItemCard> itemCards;

	@ManyToMany(
			targetEntity = QuestCard.class,
			fetch = FetchType.EAGER	)
	@JoinTable(
			name = "join_user_questcard",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "quest_card_id")}	)
	private List<QuestCard> questCards;
}
