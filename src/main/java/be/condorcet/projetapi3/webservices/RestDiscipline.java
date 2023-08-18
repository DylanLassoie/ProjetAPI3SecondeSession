package be.condorcet.projetapi3.webservices;

import be.condorcet.projetapi3.entities.Discipline;
import be.condorcet.projetapi3.services.InterfDisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/discipline")
public class RestDiscipline {

    @Autowired
    private InterfDisciplineService disciplineServiceImpl;

    //-------------------Créer une discipline--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Discipline> createDiscipline(@RequestBody Discipline discipline) throws Exception {
        System.out.println("Création d'une discipline " + discipline.getNOM());
        disciplineServiceImpl.create(discipline);
        return new ResponseEntity<>(discipline, HttpStatus.OK);
    }

    //-------------------Effacer une discipline avec id --------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteDiscipline(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("Effacement d'une discipline id: " + id);
        Discipline discipline = (Discipline) disciplineServiceImpl.read(id);
        disciplineServiceImpl.delete(discipline);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Mettre à jour une discipline avec id--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Discipline> majDiscipline(@PathVariable(value = "id") int id,@RequestBody Discipline nouvdis) throws Exception{
        System.out.println("Maj d'une discipline id :  " + id);
        nouvdis.setIDDISCIPLINE(id);
        Discipline disact = disciplineServiceImpl.update(nouvdis);
        return new ResponseEntity<>(disact, HttpStatus.OK);
    }

    //-------------------Retrouver toute les disciplines --------------------------------------------------------
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Discipline>> listDiscipline() throws Exception{
        System.out.println("Recherche de toute les disciplines");
        return new ResponseEntity<>(disciplineServiceImpl.all(), HttpStatus.OK);
    }

    @GetMapping("/rechercheDisciplineParNom")
    public ResponseEntity<Discipline> getDisciplineByNom(@RequestParam String nom) {
        try {
            System.out.println("Recherche de la discipline avec le nom : " + nom);
            Discipline discipline = disciplineServiceImpl.read(nom);
            if (discipline != null) {
                return new ResponseEntity<>(discipline, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }

}
