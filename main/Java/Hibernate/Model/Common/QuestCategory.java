package Hibernate.Model.Common;

import Hibernate.Model.Cards.QuestCard;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Audited
@Getter
@Setter
@Builder
@Entity(name = "quest_categories")
public class QuestCategory implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(unique = true)
	@NotEmpty(message = "name is mandatory")
	private String name;

	@OneToMany
	private Set<QuestCard> questCard = new HashSet<>();
}
