package Hibernate.Model.Cards;

import Hibernate.Model.Baskets.GroupQuestBasket;
import Hibernate.Model.Common.QuestCategory;
import Hibernate.Model.Common.UserLevel;
import Hibernate.Model.Persons.User;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Audited
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(toBuilder = true)
@Entity(name = "quest_cards")
public class QuestCard {

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


	@NotNull(message = "questCategory is mandatory") //todo <--- 100% OK !!!
	@ManyToOne(
			targetEntity = QuestCategory.class)
	@JoinColumn(name = "quest_category_id")
	private QuestCategory questCategory;

	@OneToMany(
			mappedBy = "questCard",
			targetEntity = GroupQuestBasket.class,
			cascade = CascadeType.PERSIST)
//todo			fetch = FetchType.EAGER
	private List<GroupQuestBasket> groupQuestBasket;

	@ManyToMany(
			targetEntity = User.class	)
	@JoinTable(
			name = "join_user_questcard",
			joinColumns = {@JoinColumn(name = "quest_card_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id")}	)
	private List<User> usersList;
}
