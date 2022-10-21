package models.classes;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import models.enums.StatoServizio;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("Distributore")
public class Distributore extends Rivenditore{

	@Enumerated(EnumType.STRING)
	@Column(name = "stato_servizio")
	private StatoServizio inServizio = StatoServizio.IN_SERVIZIO;

}
