package models.classes;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("Bus")
public class Bus extends Mezzo {
	@Column(name = "numero_posti")
	private int numeroPosti = 30;
}