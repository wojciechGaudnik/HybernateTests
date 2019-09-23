package Hibernate.Model.Common;

import Hibernate.Model.Cards.ItemCard;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Audited
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(toBuilder = true)
@Entity(name = "item_categories")
public class ItemCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(unique = true)
	@NotEmpty(message = "name is mandatory")
	private String name;

	@OneToMany(
			mappedBy = "itemCategory",
			targetEntity = ItemCard.class,
			cascade = CascadeType.PERSIST,
			fetch = FetchType.EAGER)
	private List<ItemCard> itemCards = new ArrayList<>();
}