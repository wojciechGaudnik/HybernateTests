package Hibernate.Model.Cards;

import Hibernate.Model.Baskets.GroupQuestBasket;
import Hibernate.Model.Common.QuestCategory;
import Hibernate.Model.Common.UserLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

@Audited
@Getter
@Setter
@Builder
@Entity(name = "quest_cards")
public class QuestCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@GeneratedValue
	@Column(unique = true,  columnDefinition = "uuid", updatable = false)
	private UUID uuid;

	@Column(unique = true)
	@NotEmpty(message = "name is mandatory")
	private String name;

	@NotEmpty(message = "photoUrl is mandatory")
	private String photoUrl;

	@Min(value = 1)
	@Max(value = 10)
	@NotEmpty(message = "value is mandatory")
	private int value;

	@NotEmpty(message = "description is mandatory")
	private String description;

	private boolean allowedGroupBuy;

	private boolean resolved;

	@OneToOne
	private UserLevel requiredLevel;

	@OneToOne
	private QuestCategory itemCategory;

	@OneToOne
	private GroupQuestBasket groupQuestBasket;
}
