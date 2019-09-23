package Hibernate.Model.Cards;

import Hibernate.Model.Baskets.GroupItemBasket;
import Hibernate.Model.Common.ItemCategory;
import Hibernate.Model.Common.UserLevel;
import Hibernate.Model.Persons.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.lang.annotation.Target;
import java.util.List;
import java.util.UUID;

@Audited
@Getter
@Setter
@Builder
@Entity(name = "item_cards")
public class ItemCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@GeneratedValue
	@Column(unique = true,  columnDefinition = "uuid", updatable = false)
	private UUID uuid;

	@Column(unique = true)
	@NotEmpty(message = "name is mandatory")
	private String name;

	@NotEmpty(message = "photoUrl is mandatory")
	private String photoUrl;

	@Min(value = 1)
	@Max(value = 10)
	@NotEmpty(message = "value is mandatory")
	private int value;

	@NotEmpty(message = "description is mandatory")
	private String description;

	private boolean allowedGroupBuy;

	@ManyToOne()
	@JoinColumn(name = "user_level_id")
	private UserLevel userLevel;

	@ManyToOne(targetEntity = ItemCategory.class)
	@JoinColumn(name = "item_category_id")
	private ItemCategory itemCategory;

	@OneToMany(
			mappedBy = "itemCard",
			targetEntity = GroupItemBasket.class,
			cascade = CascadeType.PERSIST)
	private List<GroupItemBasket>  groupItemBaskets;

	@ManyToMany(targetEntity = User.class)
	@JoinTable(
			name = "join_user_itemcard",
			joinColumns = {@JoinColumn(name = "item_card_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id")}
	)
	private List<User> usersList;
}

//	@ManyToOne()
//	@JoinColumn(name = "user_class_id")
//	private UserClass userClass;