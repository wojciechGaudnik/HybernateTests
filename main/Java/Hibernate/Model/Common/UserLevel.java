package Hibernate.Model.Common;

import Hibernate.Model.Cards.ItemCard;
import Hibernate.Model.Cards.QuestCard;
import Hibernate.Model.Persons.User;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
public class UserLevel implements Serializable {  //todo remove if not necessary

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
			mappedBy = "userLevel",  //todo check in both directions !!!!
			targetEntity = ItemCard.class,
			cascade = CascadeType.PERSIST	)
	private List<ItemCard> itemCardList;

	@OneToMany(
			mappedBy = "userLevel",
			targetEntity = QuestCard.class)
//			cascade = CascadeType.PERSIST)
	private List<QuestCard> questCardList;

	@OneToMany(
			mappedBy = "userLevel",
//			targetEntity = User.class,
			cascade = CascadeType.ALL)
//			fetch = FetchType.LAZY)
	private List<User> usersList;
}
