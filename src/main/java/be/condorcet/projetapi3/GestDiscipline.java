package be.condorcet.projetapi3;

import be.condorcet.projetapi3.entities.Discipline;
import be.condorcet.projetapi3.services.DisciplineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("/discipline")
public class GestDiscipline {

    @Autowired
    private DisciplineServiceImpl disciplineServiceImpl;

    @RequestMapping("/create")
    public String create(@RequestParam String nom, @RequestParam String description, @RequestParam Integer
            niveau, Map<String, Object> model) {
        System.out.println("Créer une discipline");
        Discipline dis = new Discipline(nom, description, niveau);
        try {
           disciplineServiceImpl.create(dis);
            Collection<Discipline> ldis = disciplineServiceImpl.all();
            model.put("nouvdis", dis);
            model.put("mesdis",ldis);
        } catch (Exception e) {
            System.out.println("----------erreur lors de l'ajout------- - " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "newDiscipline";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Integer id, Map<String, Object> model) {
        System.out.println("Supprimer une discipline");
        try {
            Discipline discipline = disciplineServiceImpl.read(id);
            disciplineServiceImpl.delete(discipline);
            Collection<Discipline> ldis = disciplineServiceImpl.all();
            model.put("mesDisciplines", ldis);

        } catch (Exception e) {
            System.out.println("----------erreur lors de la suppression------- - " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "deleteDiscipline";
    }

    @RequestMapping("/modify")
    public String modify(@RequestParam Integer id, @RequestParam String nom, @RequestParam String
            description, @RequestParam Integer niveau, Map<String, Object> model) {
        System.out.println("Modifier une discipline");
        try {
            Discipline discipline = disciplineServiceImpl.read(id);
            discipline.setNOM(nom);
            discipline.setDESCRIPTION(description);
            discipline.setNIVEAU(niveau);
            disciplineServiceImpl.update(discipline);
            model.put("nouvdis",discipline);

        } catch (Exception e) {
            System.out.println("----------erreur lors de la modification------- - " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "modifyDiscipline";
    }

    @RequestMapping("/all")
    public String affListe(Map<String, Object> model) {
        System.out.println("Liste Discipline");
        try {
            Collection<Discipline> ldis = disciplineServiceImpl.all();
            model.put("mesDisciplines", ldis);
        } catch (Exception e) {
            System.out.println("----------erreur lors de l'affichage------ -- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "allDiscipline";
    }
}
