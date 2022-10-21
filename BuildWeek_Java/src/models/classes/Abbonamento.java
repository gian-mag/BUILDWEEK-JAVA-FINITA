package models.classes;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import models.enums.Emissione;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("Abbonamento")
public class Abbonamento extends Biglietto {

	@Enumerated(EnumType.STRING)
	private Emissione emissione;

	@ManyToOne
	@JoinColumn(name = "tessera_id")
	private Tessera tessera;

	private LocalDate dataEmissione = LocalDate.now();

	private LocalDate validità = (emissione == Emissione.SETTIMANALE ? LocalDate.now().plusDays(7) : LocalDate.now().plusMonths(1));

}
