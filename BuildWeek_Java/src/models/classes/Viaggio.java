package models.classes;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Viaggio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idViaggio;

	@ManyToOne
	@JoinColumn(name = "mezzo_id")
	private Mezzo mezzo;

	@ManyToOne
	@JoinColumn(name = "tratta_id")
	private Tratta tratta;

	@Column(name = "orario_partenza")
	private LocalDateTime orarioPartenza = LocalDateTime.now();

	@Column(name = "orario_arrivo")
	private LocalDateTime orarioArrivo;

	public void arrivato() {
		orarioArrivo = LocalDateTime.now();
	}

//	public void tempoDiViaggio() {
//		if(orarioArrivo != null) {
//			System.out.println(orarioArrivo.to - orarioPartenza);
//		}
//	}
	
}
