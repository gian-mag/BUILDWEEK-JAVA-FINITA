package models.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import models.enums.Zona;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Tratta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Enumerated(EnumType.STRING)
	private Zona partenza;
	@Enumerated(EnumType.STRING)
	private Zona capolinea;

	@Column(name = "tempo_stimato")
	private int tempoStimato;

//    @OneToMany(mappedBy = "tratta")
//    private Set<Viaggio> viaggio;

//	public void impostaTempoStimato() {
//		int t = (capolinea.ordinal() - partenza.ordinal()) * 2;
//		if (t < 0) {
//			t = t * (-1);
//		}
//		tempoStimato = t;
//	}

	public void impostaTempoStimato() {
		Zona p = partenza;
		Zona a = capolinea; 
		int minutiPerKm = 2;

        int x1 = p.getX();
        int y1 = p.getY();

        int x2 = a.getX();
        int y2 = a.getY();

       tempoStimato = (int) ((Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)) ) * minutiPerKm);

    }

}