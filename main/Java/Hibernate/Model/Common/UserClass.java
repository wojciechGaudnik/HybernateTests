package Hibernate.Model.Common;

import Hibernate.Model.Persons.Mentor;
import Hibernate.Model.Persons.User;
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
@Entity(name = "user_classes")
public class UserClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(unique = true)
	@NotEmpty(message = "name is mandatory")
	private String name;

	@NotEmpty(message = "photoUrl is mandatory")
	private String photoUrl;

	@ManyToMany(
			targetEntity= Mentor.class	)
	@JoinTable(
			name = "join_userclasses_mentors",
			joinColumns = {@JoinColumn(name = "user_class_id")},
			inverseJoinColumns = {@JoinColumn(name = "mentor_id")}	)
	private List<Mentor> mentors;

	@OneToMany(targetEntity = User.class,
			mappedBy = "userClass",
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER)
	private List<User> users = new ArrayList<>();
}
