package be.condorcet.projetapi3.webservices;

import be.condorcet.projetapi3.entities.Project;
import be.condorcet.projetapi3.services.InterfProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/projet")
public class RestProjet {

    @Autowired
    private InterfProjectService projectServiceImpl;

    //-------------------Créer un projet--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Project> createProjet(@RequestBody Project projet) throws Exception {
        System.out.println("Création d'un projet " + projet.getNOM());
        projectServiceImpl.create(projet);
        return new ResponseEntity<>(projet, HttpStatus.OK);
    }

    //-------------------Effacer un projet avec id --------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProjet(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("Effacement d'un projet id: " + id);
        Project projet = projectServiceImpl.read(id);
        projectServiceImpl.delete(projet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Mettre à jour un projet avec id--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Project> majProjet(@PathVariable(value = "id") int id,@RequestBody Project nouvproj) throws Exception{
        System.out.println("Maj d'un projet id :  " + id);
        nouvproj.setIDPROJET(id);
        Project projact = projectServiceImpl.update(nouvproj);
        return new ResponseEntity<>(projact, HttpStatus.OK);
    }

    //-------------------Retrouver le projet correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Project> getProjet(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("Recherche du projet id " + id);
        Project projet = projectServiceImpl.read(id);
        return new ResponseEntity<>(projet, HttpStatus.OK);
    }

    @GetMapping("/rechercheParNom")
    public ResponseEntity<Project> getProjetByNom(@RequestParam String nom) {
        try {
            System.out.println("Recherche du projet avec le nom : " + nom);
            Project projet = projectServiceImpl.read(nom);
            if (projet != null) {
                return new ResponseEntity<>(projet, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //-------------------Retrouver tous les projets --------------------------------------------------------
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Project>> listProjet() throws Exception{
        System.out.println("Recherche de tous les projets");
        return new ResponseEntity<>(projectServiceImpl.all(), HttpStatus.OK);
    }

    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }

}
