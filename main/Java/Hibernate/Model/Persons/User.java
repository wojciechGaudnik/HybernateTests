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
import java.util.HashSet;
import java.util.Set;

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

	@NotEmpty(message = "userLevel is mandatory")
	@ManyToOne(targetEntity = UserLevel.class)
	private UserLevel userLevel;

	@NotEmpty(message = "userClass is mandatory")
	@ManyToOne(targetEntity = UserClass.class)
	private UserClass userClass;

	@ManyToMany(targetEntity = GroupItemBasket.class)
	private Set<GroupItemBasket> groupItemBaskets = new HashSet<>();

	@ManyToMany(targetEntity = GroupQuestBasket.class)
	private Set<GroupQuestBasket> groupQuestBaskets = new HashSet<>();

	@ManyToMany(targetEntity = ItemCard.class)
	private Set<ItemCard> itemCards = new HashSet<>();

	@ManyToMany(targetEntity = QuestCard.class)
	private Set<QuestCard> questCards = new HashSet<>();
}
