package Hibernate.Model.Persons;

import Hibernate.Model.Common.UserClass;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Audited
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(toBuilder = true)
@Entity(name = "mentors")
public class Mentor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotEmpty(message = "firstName is mandatory")
	private String firstName;

	@NotEmpty(message = "lastName is mandatory")
	private String lastName;

	@Column(unique = true)
	@NotEmpty(message = "email is mandatory")
	@Email(message="Please provide a valid email address")
//	@Retention(RUNTIME)   //todo <--- check if help with catch errors !!!
	private String email;

	@Column(unique = true)
	@NotEmpty(message = "nick is mandatory")
	private String nick;

	@Column(unique = true)
	@NotEmpty(message = "password is mandatory")
	private String password;  //todo <--- how save password

	@NotEmpty(message = "photo is mandatory")
	private String photoUrl;

	@UniqueElements
	@ManyToMany(
			targetEntity = UserClass.class,
			fetch = FetchType.EAGER	)
	@JoinTable(
			name = "join_userclasses_mentors",
			joinColumns = {@JoinColumn(name = "mentor_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_class_id")}	)
	private List<UserClass> userClasses;
}


