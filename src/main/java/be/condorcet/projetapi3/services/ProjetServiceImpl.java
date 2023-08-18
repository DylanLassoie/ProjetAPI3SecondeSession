package be.condorcet.projetapi3.services;

import java.util.List;

import be.condorcet.projetapi3.entities.Project;
import be.condorcet.projetapi3.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class ProjetServiceImpl implements InterfProjectService{
    @Autowired
    private ProjectRepository ProjectRepository;


    //-------------------Créer un projet--------------------------------------------------------
    @Override
    public Project create(Project project) throws Exception {
        ProjectRepository.save(project);
        return project;
    }

    //-------------------Lire un projet par son id--------------------------------------------------------
    @Override
    public Project read(Integer id) throws Exception {
        return ProjectRepository.findById(id).get();
    }

    //-------------------Mettre à jour un projet--------------------------------------------------------
    @Override
    public Project update(Project project) throws Exception {
        read(project.getIDPROJET()) ;
        ProjectRepository.save(project);
        return project;
    }

    //-------------------Supprimer un projet--------------------------------------------------------
    @Override
    public void delete(Project projet) throws Exception {
        ProjectRepository.deleteById(projet.getIDPROJET());
    }

    //-------------------Afficher tous les projets--------------------------------------------------------
    @Override
    public List<Project> all() throws Exception {
        return ProjectRepository.findAll();
    }

    //-------------------Lire un projet par son nom--------------------------------------------------------
    @Override
    public Project read(String nom) {
        return ProjectRepository.findProjetByNOMLike(nom);
    }
}
