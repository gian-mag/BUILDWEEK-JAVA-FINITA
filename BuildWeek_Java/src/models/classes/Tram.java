package models.classes;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("Tram")
public class Tram extends Mezzo {
	@Column(name = "numero_posti")
	private int numeroPosti = 50;
}