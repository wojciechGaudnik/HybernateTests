package Hibernate.Model.Common;

import Hibernate.Model.Cards.ItemCard;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
	@NotBlank(message = "name is mandatory")
	@Size(min = 3, max = 100, message = "length out of range min = 3, max = 100 <--- check !!!")
	private String name;

	@OneToMany(
			mappedBy = "itemCategory",
			targetEntity = ItemCard.class,
			cascade = CascadeType.PERSIST,
			fetch = FetchType.EAGER)
	private List<ItemCard> itemCards = new ArrayList<>();
}