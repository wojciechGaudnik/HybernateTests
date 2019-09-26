package QSERDHibernate.Model.Baskets;

import QSERDHibernate.Model.Cards.ItemCard;
import QSERDHibernate.Model.Persons.User;
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
@Entity(name = "group_item_baskets")
public class GroupItemBasket {

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
			targetEntity = ItemCard.class)
	@JoinColumn(name = "item_card_id")
	private ItemCard itemCard;

	@ManyToMany(
			targetEntity = User.class)
	@JoinTable(
			name = "join_user_groupitembasket",
			joinColumns = {@JoinColumn(name = "group_item_basket_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id")})
	private List<User> users;
}
