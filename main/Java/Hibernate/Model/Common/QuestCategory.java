package Hibernate.Model.Common;

import Hibernate.Model.Cards.QuestCard;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Audited
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(toBuilder = true)
@Entity(name = "quest_categories")
public class QuestCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(unique = true)
	@NotEmpty(message = "name is mandatory")
	private String name;

	@OneToMany(
			mappedBy = "questCategory",
			targetEntity = QuestCard.class,
			cascade = CascadeType.PERSIST,
			fetch = FetchType.EAGER)
	private List<QuestCard> questCard = new ArrayList<>();
}
