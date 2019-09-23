package Hibernate.Model.Persons;

import Hibernate.Model.Common.UserClass;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Audited
@Getter
@Setter
@Builder
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

	@ManyToMany(
			targetEntity = UserClass.class,
			fetch = FetchType.EAGER
	)
	@JoinTable(
			name = "join_mentor_userclass",
			joinColumns = {@JoinColumn(name = "mentor_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_class_id")}
	)
	private List<UserClass> userClasses;
}
