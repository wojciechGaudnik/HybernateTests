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
import java.util.ArrayList;
import java.util.List;

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

	@ManyToMany(
			mappedBy = "userClasses",
			targetEntity= Mentor.class,
			cascade = CascadeType.MERGE
//			fetch = FetchType.EAGER
	)
//	@JoinTable(
//			name = "join_mentor_userclass",
//			joinColumns = {@JoinColumn(name = "user_class_id")},
//			inverseJoinColumns = {@JoinColumn(name = "mentor_id")}
//	)
	private List<Mentor> mentors;

	@OneToMany(targetEntity = User.class,
			mappedBy = "userClass",
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER)
	private List<User> users;
}
