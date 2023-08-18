package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Discipline;
import be.condorcet.projetapi3.repositories.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class DisciplineServiceImpl implements InterfDisciplineService{
    @Autowired
    private DisciplineRepository DisciplineRepository;

    //-------------------Créer une discipline--------------------------------------------------------
    @Override
    public Discipline create(Discipline discipline) throws Exception {
        DisciplineRepository.save(discipline);
        return discipline;
    }

    //-------------------Lire une discipline par son id--------------------------------------------------------
    @Override
    public Discipline read(Integer id) throws Exception {
        Optional<Discipline> odis= DisciplineRepository.findById(id);
        return odis.get();
    }

    //-------------------Mettre à jour une discipline--------------------------------------------------------
    @Override
    public Discipline update(Discipline discipline) throws Exception {
        read(discipline.getIDDISCIPLINE());
        DisciplineRepository.save(discipline);
        return discipline;
    }

    //-------------------Supprimer une discipline--------------------------------------------------------
    @Override
    public void delete(Discipline discipline) throws Exception {
        DisciplineRepository.deleteById(discipline.getIDDISCIPLINE());
    }

    //-------------------Afficher toutes les disciplines--------------------------------------------------------
    @Override
    public List<Discipline> all() throws Exception {
        return DisciplineRepository.findAll();
    }

    //-------------------Lire une discipline par son nom--------------------------------------------------------
    @Override
    public Discipline read(String nom) {
        return DisciplineRepository.findDiscplineByNOMLike(nom+"%");
    }

    //-------------------Lire toutes les disciplines d'un certain niveau--------------------------------------------------------
    @Override
    public List<Discipline> read(int niveau) {
       return DisciplineRepository.findDisciplineByNIVEAULike(niveau);
    }
}
