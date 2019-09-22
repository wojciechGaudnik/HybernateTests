package Hibernate.Model.Baskets;


import Hibernate.Model.Persons.User;
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
@Entity(name = "group_item_basket")
public class GroupItemBasket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(unique = true)
	@NotEmpty(message = "name is mandatory")
	private String name;

	private Q
	@ManyToOne(targetEntity = User.class)
	private Set<User> users = new HashSet<>();
}
