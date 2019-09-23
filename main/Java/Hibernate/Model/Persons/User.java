package Hibernate.Model.Persons;

import Hibernate.Model.Baskets.GroupItemBasket;
import Hibernate.Model.Baskets.GroupQuestBasket;
import Hibernate.Model.Cards.ItemCard;
import Hibernate.Model.Cards.QuestCard;
import Hibernate.Model.Common.UserClass;
import Hibernate.Model.Common.UserLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Audited
@Getter
@Setter
@Builder
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

	// todo
//	private List<ItemCard> resolvedItemsCards;
//
//	private List<QuestCard> resolvedQuestsCards;

	@NotEmpty(message = "userLevel is mandatory")
	@ManyToOne()
	@JoinColumn(name = "user_level_id")
	private UserLevel userLevel;

	@NotEmpty(message = "userClass is mandatory")
	@ManyToOne()
	@JoinColumn(name = "user_class_id")
	private UserClass userClass;

	@ManyToMany(targetEntity = GroupItemBasket.class)
	@JoinTable(
			name = "join_user_groupitembasket",
			joinColumns = {@JoinColumn(name = "group_item_basket_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id")}
	)
	private List<GroupItemBasket> groupItemBaskets;

	@ManyToMany(targetEntity = GroupQuestBasket.class)
	@JoinTable(
			name = "join_user_groupquestbasket",
			joinColumns = {@JoinColumn(name = "group_quest_basket_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id")}
	)
	private List<GroupQuestBasket> groupQuestBaskets;

	@ManyToMany(targetEntity = ItemCard.class)
	@JoinTable(
			name = "join_user_itemcard",
			joinColumns = {@JoinColumn(name = "item_card_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id")}
	)
	private List<ItemCard> itemCards;

	@ManyToMany(targetEntity = QuestCard.class)
	@JoinTable(
			name = "join_user_questcard",
			joinColumns = {@JoinColumn(name = "quest_card_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id")}
	)
	private List<QuestCard> questCards;
}
