package be.condorcet.projetapi3.entities;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString @Entity
@Table(name = "APIDISCIPLINES", schema = "ORA20", catalog = "ORCL")

public class Discipline {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "discipline_generator")
    @SequenceGenerator(name = "discipline_generator",
            sequenceName = "APIDISCIPLINES_SEQ", allocationSize = 1)
    private int IDDISCIPLINE;
    @NonNull
    private String NOM;
    @NonNull
    private String DESCRIPTION;
    @NonNull
    private  int NIVEAU;

}