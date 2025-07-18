
package tn.esprit.spring.kaddem;

import java.util.List;
import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.repositories.*;

@Component
public class TestDataSetup implements CommandLineRunner {
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private UniversiteRepository universiteRepository;
    @Autowired
    private EquipeRepository equipeRepository;
    @Autowired
    private ContratRepository contratRepository;

    @Override
    public void run(String... args) throws Exception {
        // Création de 10 départements
        List<Departement> deps = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Departement dep = new Departement();
            dep.setNomDepart("Departement " + i);
            deps.add(departementRepository.save(dep));
        }

        // Création de 10 universités
        List<Universite> univs = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Universite univ = new Universite();
            univ.setNomUniv("Universite " + i);
            univs.add(universiteRepository.save(univ));
        }

        // Création de 10 équipes
        List<Equipe> equipes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Equipe equipe = new Equipe();
            equipe.setNomEquipe("Equipe " + i);
            equipes.add(equipeRepository.save(equipe));
        }

        // Création de 10 étudiants, chacun dans un département différent et dans une équipe différente
        List<Etudiant> etudiants = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Etudiant etu = new Etudiant();
            etu.setPrenomE("Prenom" + i);
            etu.setNomE("Nom" + i);
            etu.setDepartement(deps.get((i-1)%deps.size()));
            etudiants.add(etudiantRepository.save(etu));
        }

        // Création de 10 contrats, chacun lié à un étudiant
        for (int i = 0; i < 10; i++) {
            Contrat contrat = new Contrat();
            contrat.setEtudiant(etudiants.get(i));
            contratRepository.save(contrat);
        }

        // Lier chaque université à un département (exemple simple)
        for (int i = 0; i < 10; i++) {
            Universite univ = univs.get(i);
            java.util.Set<Departement> univDeps = new java.util.HashSet<>();
            univDeps.add(deps.get(i));
            univ.setDepartements(univDeps);
            universiteRepository.save(univ);
        }

        // Lier chaque équipe à un étudiant (exemple simple)
        for (int i = 0; i < 10; i++) {
            Equipe equipe = equipes.get(i);
            java.util.Set<Etudiant> equipeEtudiants = new java.util.HashSet<>();
            equipeEtudiants.add(etudiants.get(i));
            equipe.setEtudiants(equipeEtudiants);
            equipeRepository.save(equipe);
        }
    }
}
