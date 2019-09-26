package QSERDHibernate.Model.Common;

import QSERDHibernate.Model.Cards.ItemCard;
import QSERDHibernate.Model.Cards.QuestCard;
import QSERDHibernate.Model.Persons.User;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(toBuilder = true)
@Audited
@Entity(name = "user_levels")
public class UserLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(unique = true)
	@NotBlank(message = "name is mandatory")
	@Size(min = 3, max = 100, message = "length out of range min = 3, max = 100 <--- check !!!")
	private String name;

	@Range(min = 1L, max = 100L, message = "out of range min = 1L, max = 100L  <--- check !!!")
	private int value;

	@OneToMany(
			mappedBy = "userLevel",
			targetEntity = ItemCard.class)
	private List<ItemCard> itemCardList;

	@OneToMany(
			mappedBy = "userLevel",
			targetEntity = QuestCard.class)
	private List<QuestCard> questCardList;

	@OneToMany(
			mappedBy = "userLevel",
			targetEntity = User.class)
	private List<User> usersList;
}
