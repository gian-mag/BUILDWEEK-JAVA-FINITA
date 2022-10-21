package models.classes;

import lombok.*;
import models.enums.Status;

import java.util.Set;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_mezzo", discriminatorType = DiscriminatorType.STRING )

public abstract class Mezzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mezzo", nullable = false)
    private int idMezzo;
    
	@Enumerated(EnumType.STRING)
    private Status status = Status.SERVIZIO;
    
    @OneToMany
    private Set<Viaggio> viaggi;
    
    @ManyToMany
    private Set<Biglietto> biglietti;
    
    public void toggleStatus() {
    	if(status == Status.SERVIZIO) {
    		status = Status.MANUTENZIONE;
    	} else {
    		status = Status.SERVIZIO;
    	}
    }
    
}
