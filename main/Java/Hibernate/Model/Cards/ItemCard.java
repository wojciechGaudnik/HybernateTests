package Hibernate.Model.Cards;

import Hibernate.Model.Baskets.GroupItemBasket;
import Hibernate.Model.Common.ItemCategory;
import Hibernate.Model.Common.UserLevel;
import Hibernate.Model.Persons.User;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Audited
@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(toBuilder = true)
@Entity(name = "item_cards")
public class ItemCard {

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

	@ManyToOne()
	@JoinColumn(name = "user_level_id")
	private UserLevel userLevel;

	@NotNull(message = "description is mandatory")
	@ManyToOne(targetEntity = ItemCategory.class)
	@JoinColumn(name = "item_category_id")
	private ItemCategory itemCategory;

	@OneToMany(
			mappedBy = "itemCard",
			targetEntity = GroupItemBasket.class,
			cascade = CascadeType.PERSIST)
	private List<GroupItemBasket>  groupItemBaskets = new ArrayList<>();

	@ManyToMany(
			targetEntity = User.class)
	@JoinTable(
			name = "join_user_itemcard",
			joinColumns = {@JoinColumn(name = "item_card_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id")})
	private List<User> usersList = new ArrayList<>();
}
