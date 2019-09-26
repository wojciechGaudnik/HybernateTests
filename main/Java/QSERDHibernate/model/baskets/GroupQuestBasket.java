package QSERDHibernate.model.baskets;

import QSERDHibernate.model.cards.QuestCard;
import QSERDHibernate.model.persons.User;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(toBuilder = true)
@Audited
@Entity(name = "group_quest_baskets")
public class GroupQuestBasket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(unique = true)
	@NotBlank(message = "name is mandatory")
	@Size(min = 3, max = 100, message = "length out of range min = 3, max = 100 <--- check !!!")
	private String name;

	@Range(min = 1L, max = 1000000L, message = "out of range min = 1L, max = 1000000L,  <--- check !!!")
	private int value;

	private boolean closeBasket;

	@NotNull(message = "owner is mandatory")
	@ManyToOne(
			targetEntity = User.class)
	@JoinColumn(name = "owner_id")
	private User owner;

	@ManyToOne(
			targetEntity = QuestCard.class)
	@JoinColumn(name = "quest_card_id")
	private QuestCard questCard;

	@ManyToMany(
			targetEntity = User.class)
	@JoinTable(
			name = "join_user_groupquestbasket",
			joinColumns = {@JoinColumn(name = "group_quest_basket_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id")})
	private List<User> users;
}
