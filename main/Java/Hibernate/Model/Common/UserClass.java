package Hibernate.Model.Common;

import Hibernate.Model.Persons.Mentor;
import Hibernate.Model.Persons.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Audited
@Getter
@Setter
@Builder
@Entity(name = "user_classes")
public class UserClass implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(unique = true)
	@NotEmpty(message = "name is mandatory")
	private String name;

	@NotEmpty(message = "photoUrl is mandatory")
	private String photoUrl;

	@ManyToMany(targetEntity = Mentor.class)
	private Set<Mentor> mentors = new HashSet<>();  //todo one UserClass can have many Mentors --->

	@OneToMany(targetEntity = User.class)
	private Set<User> users = new HashSet<>();  //todo one UserClass can have many Mentors --->
}
