package be.condorcet.projetapi3;

import be.condorcet.projetapi3.entities.Discipline;
import be.condorcet.projetapi3.entities.Project;
import be.condorcet.projetapi3.services.DisciplineServiceImpl;
import be.condorcet.projetapi3.services.ProjetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/projet")
public class GestProjet {

    @Autowired
    private ProjetServiceImpl projetServiceImpl;

    @RequestMapping("/create")
    public String create(@RequestParam String nom, @RequestParam String datedebut, @RequestParam String datefin,
                         @RequestParam float cout, Model model) {
        System.out.println("Ajout d'un projet");

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDateDebut = dateFormat.parse(datedebut + " 00:00:00");
            Date parsedDateFin = dateFormat.parse(datefin + " 00:00:00");

            Project proj = new Project(nom, parsedDateDebut, parsedDateFin, cout);

            projetServiceImpl.create(proj);

            Collection<Project> lproj = projetServiceImpl.all();
            model.addAttribute("nouvproj", proj);
            model.addAttribute("mesproj", lproj);
        } catch (Exception e) {
            System.out.println("----------erreur lors de l'ajout------- - " + e);
            model.addAttribute("error", e.getMessage());
            return "error";
        }

        return "newProject";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Integer id, Map<String, Object> model) {
        System.out.println("Supprimer un projet");
        try {
            Project projet = projetServiceImpl.read(id);
            projetServiceImpl.delete(projet);
            Collection<Project> lproj = projetServiceImpl.all();
            model.put("mesProjets", lproj);

        } catch (Exception e) {
            System.out.println("----------erreur lors de la suppression------- - " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "deleteProject";
    }

    @RequestMapping("/modify")
    public String modify(@RequestParam Integer id, @RequestParam String nom,
                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date datedebut,
                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date datefin,
                         @RequestParam float cout, Map<String, Object> model) {
        System.out.println("Modification d'un projet");
        try {
            Project projet = projetServiceImpl.read(id);
            projet.setNOM(nom);
            projet.setDATEDEBUT(datedebut);
            projet.setDATEFIN(datefin);
            projet.setCOUT(cout);
            projetServiceImpl.update(projet);
            model.put("nouvproj", projet);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la modification------- - " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "modifyProject";
    }

    @RequestMapping("/rechercheParNom")
    public String affNom(@RequestParam String nom, Map<String,Object> model) {
        System.out.println("Recherche par nom");
        try {
            Project lproj = projetServiceImpl.read(nom);
            model.put("mesProjets",lproj);
        }catch (Exception e) {
            System.out.println("----------erreur lors de l'affichage------ -- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "searchProject";
    }

    @RequestMapping("/all")
    public String affListe(Map<String, Object> model) {
        System.out.println("Afficher tous les projets");
        try {
            Collection<Project> lproj = projetServiceImpl.all();
            model.put("mesProjets", lproj);
        } catch (Exception e) {
            System.out.println("----------erreur lors de l'affichage------ -- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "allProject";
    }
}
