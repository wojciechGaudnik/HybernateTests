package Hibernate.Model.Persons;

import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Audited
@Data
@Entity(name = "mentor")
public class Mentor  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotEmpty(message = "firstName is mandatory")
	private String firstName;

	@NotEmpty(message = "lastName is mandatory")
	private String lastName;

	@Email
	@Column(unique = true)
	@NotEmpty(message = "email is mandatory")
	private String email;


	
	@NotEmpty(message = "name is mandatory")
	private String photoUrl;
}
