package Hibernate.Model.Common;

import Hibernate.Model.Cards.ItemCard;
import Hibernate.Model.Cards.QuestCard;
import Hibernate.Model.Persons.User;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Audited
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(toBuilder = true)
@Entity(name = "user_levels")
public class UserLevel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(unique = true)
	@NotEmpty(message = "name is mandatory")
	private String name;

	@Min(value = 1)
	@Max(value = 10)
//	@NotEmpty(message = "value is mandatory")
	private int value;

	@OneToMany(
			mappedBy = "userLevel",  //todo check in both directions !!!!
			targetEntity = ItemCard.class,
			cascade = CascadeType.PERSIST	)
	private List<ItemCard> itemCardList = new ArrayList<>();

	@OneToMany(
			mappedBy = "userLevel",
			targetEntity = QuestCard.class,
			cascade = CascadeType.PERSIST)
	private List<QuestCard> questCardList = new ArrayList<>();

	@OneToMany(
			mappedBy = "userLevel",
			targetEntity = User.class,
			cascade = CascadeType.PERSIST,
			fetch = FetchType.EAGER)
	private List<User> usersList = new ArrayList<>();
}
