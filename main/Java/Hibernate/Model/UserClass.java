package Hibernate.Model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.UniqueElements;


import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "user_class")
public class UserClass{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(unique = true)
//	@UniqueElements
	@NotEmpty(message = "name is mandatory")
	private String name;

	@NotEmpty(message = "name is mandatory")
	private String photoUrl;

}
