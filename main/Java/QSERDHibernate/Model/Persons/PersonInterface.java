//package QSERDHibernate.Model.Persons;
//
//import javax.persistence.*;
//import org.hibernate.envers.Audited;
//
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;
//import javax.validation.constraints.Email;
//
////@Audited
////@Entity(name = "creepy")
////@MappedSuperclass
//public abstract PersonInterface extends Entity{
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	Long Id;
//
//	@NotEmpty(message = "firstName is mandatory")
//	@Size(min = 3, max = 100, message = "length out of range ")
//	String firstName = null;
//
//	@NotEmpty(message = "last is mandatory")
//	@Size(min = 3, max = 100, message = "length out of range ")
//	String lastName = null;
//
//	@Column(unique = true)
//	@NotEmpty(message = "email is mandatory")
//	@Email(message="Please provide a valid email address")
//	String email = null;
//
//	@Column(unique = true)
//	@NotEmpty(message = "nick is mandatory")
//	@Size(min = 3, max = 50, message = "length out of range ")
//	String nick = null;
//
//	@Column(unique = true)
//	@NotEmpty(message = "password is mandatory")
//	String password = null;  //todo <--- how save password
//
//	@NotEmpty(message = "photo is mandatory")
//	@Size(min = 3, max = 100, message = "length out of range ")
//	String photoUrl = null;
//}
