package Hibernate.Model.Baskets;

import Hibernate.Model.Cards.QuestCard;
import Hibernate.Model.Common.UserClass;
import Hibernate.Model.Persons.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Audited
@Getter
@Setter
@Builder
@Entity(name = "group_quest_baskets")
public class GroupQuestBasket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(unique = true)
	@NotEmpty(message = "name is mandatory")
	private String name;

	@Min(value = 0)
	@Max(value = 1000)
	private int value;

	private boolean close;

	@NotEmpty(message = "owner is mandatory")
	@ManyToOne(
			targetEntity = UserClass.class,
			fetch = FetchType.EAGER
	)
	@JoinColumn(name = "owner_id")
	private User owner;

	@NotEmpty(message = "quest card is mandatory")
	@ManyToOne(
			targetEntity = QuestCard.class,
			fetch = FetchType.EAGER
	)
	@JoinColumn(name = "quest_card_id")
	private QuestCard questCard;

	@ManyToMany(
			targetEntity = User.class,
			fetch = FetchType.EAGER
	)
	@JoinTable(
			name = "join_user_groupquestbasket",
			joinColumns = {@JoinColumn(name = "group_quest_basket_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id")}
	)
	private List<User> users;
}
