package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    public Project findProjetByNOMLike(String nom);
}
