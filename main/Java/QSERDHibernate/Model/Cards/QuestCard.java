package QSERDHibernate.Model.Cards;

import QSERDHibernate.Model.Baskets.GroupQuestBasket;
import QSERDHibernate.Model.Common.QuestCategory;
import QSERDHibernate.Model.Common.UserLevel;
import QSERDHibernate.Model.Persons.User;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(toBuilder = true)
@Audited
@Entity(name = "quest_cards")
public class QuestCard { //todo implement Serializable  ???

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(unique = true,  columnDefinition = "uuid")
	private UUID uuid;

	@Column(unique = true)
	@NotBlank(message = "name is mandatory")
	@Size(min = 3, max = 100, message = "length out of range min = 3, max = 100 <--- check !!!")
	private String name;

	@Column(unique = true)
	@Size(min = 3, max = 100, message = "length out of range ")
	private String photoUrl;

	@Range(min = 1L, max = 10000L, message = "out of range min = 1L, max =  10000L <--- check !!!")
	private int value;

	@Column(unique = true)
	@NotBlank(message = "description is mandatory")
	@Size(min = 3, max = 500, message = "length out of range min = 3, max = 500 <--- check !!!")
	private String description;

	private boolean allowedGroupBuy;

	@ManyToOne(
			targetEntity = UserLevel.class)
	@JoinColumn(name = "user_level_id")
	private UserLevel userLevel;


	@NotNull(message = "questCategory is mandatory")
	@ManyToOne(                                         //todo <--- 100% OK !!!
			targetEntity = QuestCategory.class)
	@JoinColumn(name = "quest_category_id")
	private QuestCategory questCategory;

	@OneToMany(
			mappedBy = "questCard",
			targetEntity = GroupQuestBasket.class)
	private List<GroupQuestBasket> groupQuestBasket;

	@ManyToMany(
			targetEntity = User.class)
	@JoinTable(
			name = "join_user_questcard",
			joinColumns = {@JoinColumn(name = "quest_card_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id")})
	private List<User> usersList;
}
