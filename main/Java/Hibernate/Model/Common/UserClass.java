package Hibernate.Model.Common;

import Hibernate.Model.Persons.Mentor;
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
@Entity(name = "user_class")
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

}
