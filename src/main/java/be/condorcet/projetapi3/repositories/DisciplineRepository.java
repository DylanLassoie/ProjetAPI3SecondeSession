package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {

    public Discipline findDiscplineByNOMLike(String nom);

    public List<Discipline> findDisciplineByNIVEAULike(int niveau);
}
