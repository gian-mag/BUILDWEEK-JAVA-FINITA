package models.classes;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.*;
import models.enums.Vidima;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(name = "ticket", discriminatorType = DiscriminatorType.STRING )
@DiscriminatorValue("Biglietto")
public class Biglietto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBiglietto;

	private LocalDate dataEmissione = LocalDate.now();

	@Enumerated(EnumType.STRING)
	private Vidima vidimato = Vidima.FALSE;
	
	private LocalDate dataVidimazione;

	@ManyToOne
	@JoinColumn(name="id")
	private Rivenditore rivenditore;
	
	@ManyToMany(mappedBy = "biglietti")
	@ToString.Exclude
	private Set<Mezzo> mezzo;
	
}
