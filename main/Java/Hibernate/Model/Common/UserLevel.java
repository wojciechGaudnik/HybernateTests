package Hibernate.Model.Common;

import Hibernate.Model.Cards.ItemCard;
import Hibernate.Model.Cards.QuestCard;
import Hibernate.Model.Persons.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Audited
@Getter
@Setter
@Builder
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
	@NotEmpty(message = "value is mandatory")
	private int value;

	@OneToMany
	private Set<ItemCard> itemCard = new HashSet<>();

	@OneToMany
	private Set<QuestCard> questCard = new HashSet<>();

	@OneToMany
	private Set<User> user = new HashSet<>();



}
