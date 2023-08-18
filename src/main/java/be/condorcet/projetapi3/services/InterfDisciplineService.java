package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Discipline;

import java.util.List;

public interface InterfDisciplineService extends InterfService<Discipline> {
    public Discipline read(String nom);

    public List<Discipline> read(int niveau);
}
