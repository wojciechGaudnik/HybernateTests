package Hibernate.Model.Common;

import Hibernate.Model.Persons.Mentor;
import Hibernate.Model.Persons.User;
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
@Entity(name = "user_classes")
public class UserClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(unique = true)
	@NotBlank(message = "name is mandatory")
	@Size(min = 3, max = 100, message = "length out of range ")
	private String name;

	@Column(unique = true)
	@NotBlank(message = "photoUrl is mandatory")
	@Size(min = 3, max = 100, message = "length out of range ")
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
			cascade = CascadeType.PERSIST,
			fetch = FetchType.EAGER)
	private List<User> users = new ArrayList<>();
}
