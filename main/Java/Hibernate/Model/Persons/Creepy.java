package Hibernate.Model.Persons;

import lombok.*;
import org.hibernate.envers.Audited;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Audited
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(toBuilder = true)
@Entity(name = "creepy")
public class Creepy{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotNull(message = "firstName is mandatory")
	@Size(min = 3, max = 200)
	private String firstName;

	@org.hibernate.validator.constraints.NotEmpty(message = "lastName is mandatory")
	private String lastName;

	@Column(unique = true)
	@NotEmpty(message = "email is mandatory")
	@Email(message="Please provide a valid email address") //todo <--- neither Javax nor hibernate validator
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
}
