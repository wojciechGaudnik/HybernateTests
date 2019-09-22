package Hibernate.Model.Baskets;

import Hibernate.Model.Cards.QuestCard;
import Hibernate.Model.Persons.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
	@OneToOne
	private QuestCard itemCard;

	@OneToMany()
	private Set<User> users = new HashSet<>();
}
