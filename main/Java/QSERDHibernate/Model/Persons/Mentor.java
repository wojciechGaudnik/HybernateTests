package QSERDHibernate.Model.Persons;

import QSERDHibernate.Model.Common.UserClass;
import lombok.*;
import javax.persistence.*;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(toBuilder = true)
@Audited
@Entity(name = "mentors")
public class Mentor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotBlank(message = "firstName is mandatory")
	@Size(min = 3, max = 100, message = "length out of range ")
	private String firstName;

	@NotBlank(message = "last is mandatory")
	@Size(min = 3, max = 100, message = "length out of range ")
	private String lastName;

	@Column(unique = true)
	@NotBlank(message = "email is mandatory")
	@Size(min = 3, max = 100, message = "length out of range ")
	@Email(message="Please provide a valid email address")
	private String email;

	@Column(unique = true)
	@NotBlank(message = "nick is mandatory")
	@Size(min = 3, max = 50, message = "length out of range ")
	private String nick;

	@NotBlank(message = "password is mandatory")
	private String password;  //todo <--- how save password

	@Column(unique = true)
	@Size(min = 3, max = 100, message = "length out of range ")
	private String photoUrl;

	@UniqueElements //todo don't work can make one mentor with two classes1
	@ManyToMany(                                             //todo <--- 100% OK !!!
			targetEntity = UserClass.class)
	@JoinTable(
			name = "join_userclasses_mentors",
			joinColumns = {@JoinColumn(name = "mentor_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_class_id")})
	private List<UserClass> userClasses;
}


