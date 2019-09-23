package Hibernate.Model.Baskets;

import Hibernate.Model.Cards.QuestCard;
import Hibernate.Model.Persons.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Audited
@Getter
@Setter
@Builder
@Entity(name = "group_item_baskets")
public class GroupItemBasket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(unique = true)
	@NotEmpty(message = "name is mandatory")
	private String name;

	@NotEmpty(message = "item card is mandatory")
	@ManyToOne
	@JoinColumn(name = "item_card_id")
	private QuestCard itemCard;

	@ManyToMany(targetEntity = User.class)
	@JoinTable(
			name = "join_user_groupitembasket",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "group_item_basket_id")}
	)
	private List<User> users;
}
