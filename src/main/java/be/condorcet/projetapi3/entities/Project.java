package be.condorcet.projetapi3.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString @Entity
@Table(name = "APIPROJET", schema = "ORA20", catalog = "ORCL")

public class Project {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "projet_generator")
    @SequenceGenerator(name = "projet_generator",
            sequenceName = "APIProjet_SEQ", allocationSize = 1)
    private int IDPROJET;
    @NonNull
    private String NOM;
    @NonNull
    private Date DATEDEBUT;
    @NonNull
    private Date DATEFIN;
    @NonNull
    private  float COUT;

    @ManyToOne @JoinColumn(name="IDDISCIPLINE")
    private  Discipline discipline;
}
