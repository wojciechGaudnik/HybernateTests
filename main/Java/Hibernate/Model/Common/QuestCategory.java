package Hibernate.Model.Common;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Audited
@Getter
@Setter
@Entity(name = "quest_category")
public class QuestCategory implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(unique = true)
	@NotEmpty(message = "name is mandatory")
	private String name;

}
