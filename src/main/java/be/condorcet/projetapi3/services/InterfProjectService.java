package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Project;

public interface InterfProjectService extends InterfService<Project> {
    public Project read(String nom);
}
